package Domain.ServicioMedicion;

import Domain.CalculadorHC.FactorEmision;
import Domain.Organizacion.*;
import Domain.Organizacion.Excepciones.ImposibilidadDeCrearWorkbookException;
import Domain.Organizacion.Excepciones.ImposiblidadDeCerrarWorkbookException;

import Domain.Repositorios.RepositorioFactoresEmisionDB;
import Domain.Repositorios.RepositorioPersonasDB;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ServicioExcel extends ServicioMediciones{
  private static ServicioExcel instance = null;

  public static ServicioExcel getInstance(){
    if (instance == null){
      instance = new ServicioExcel();
    }
    return instance;
  }

  @Override
  public ArrayList<Actividad> cargarMediciones(String fileName, Organizacion org) throws IOException, IOError {
    Workbook workbook = null;
    try{
      File file = new File("src/main/java/Domain/Utils/" + fileName);
      workbook = WorkbookFactory.create(file);
    } catch (IOException e){
      throw new ImposibilidadDeCrearWorkbookException(e.toString());
      // e.printStackTrace();
    }

    // Getting the Sheet at index zero
    Sheet sheet = workbook.getSheetAt(0);

    // Create a DataFormatter to format and get each cell's value as String
    DataFormatter dataFormatter = new DataFormatter();

    // 1. You can obtain a rowIterator and columnIterator and iterate over them
    System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
    Iterator<Row> rowIterator = sheet.rowIterator();

    //NOTA: Primeras dos filas son titulos, no valores
    rowIterator.next();
    rowIterator.next();

    ArrayList<Actividad> actividades = new ArrayList<Actividad>();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();

      // Now let's iterate over the columns of the current row
      Iterator<Cell> cellIterator = row.cellIterator();

      while (cellIterator.hasNext()) {
        Cell cell = cellIterator.next();
        String tipoActividad = dataFormatter.formatCellValue(cell);
        //System.out.print(tipoActividad + "\t");

        cell = cellIterator.next();
        String tipoConsumo = dataFormatter.formatCellValue(cell);
        //System.out.print(tipoConsumo + "\t");

        cell = cellIterator.next();
        String valor = dataFormatter.formatCellValue(cell);
        System.out.print(valor + "\t");
        Double valorConsumo = Double.parseDouble(valor);


        cell = cellIterator.next();
        String periodicidad = dataFormatter.formatCellValue(cell);
        //System.out.print(periodicidad + "\t");

        cell = cellIterator.next();
        String periodo_imputacion = dataFormatter.formatCellValue(cell);
        //System.out.print(periodo_imputacion + "\t");

        LocalDate fechaPeriodoImputacion = LocalDate.parse(periodo_imputacion, DateTimeFormatter.ofPattern("M/d/yy"));
        Integer mes = fechaPeriodoImputacion.getMonthValue();
        Integer anio = fechaPeriodoImputacion.getYear();

        Optional<Actividad> act = org.getActividades().stream().filter(
                unaActividad ->
                        unaActividad.getNombre().toString().equals(tipoActividad)
                        && unaActividad.getTipoConsumo().toString().equals(tipoConsumo)
                        && unaActividad.getPeriodicidad().toString().equals(periodicidad)
                )
                .findAny();

        try{
          Actividad actividad = act.get();
          actividad.cargarConsumos(mes, anio, valorConsumo);
          actualizarHCActividad(org, periodicidad, mes, anio);

        }
        catch (NoSuchElementException e)
        {
          try
          {
            Optional<Actividad> act2 = actividades.stream().filter(
                    unaActividad ->
                            unaActividad.getNombre().toString().equals(tipoActividad)
                    && unaActividad.getTipoConsumo().toString().equals(tipoConsumo)
                    && unaActividad.getPeriodicidad().toString().equals(periodicidad)
            ).findAny();

            act2.get().cargarConsumos(mes, anio, valorConsumo);

            actualizarHCActividad(org, periodicidad, mes, anio);

          }
          catch(NoSuchElementException e2){
            actividades.add(crearActividad(tipoActividad, tipoConsumo, periodicidad, mes, anio, valorConsumo, org));
            actualizarHCActividad(org, periodicidad, mes, anio);
          }
        }

      }
      System.out.println();
    }

    // Closing the workbook
    try {
      workbook.close();
    } catch (IOException e) {
      throw new ImposiblidadDeCerrarWorkbookException(e.toString());
      // e.printStackTrace();
    }
    return actividades;
  }

  public void actualizarHCActividad(Organizacion organizacion, String periodicidad, Integer mes, Integer anio) throws IOException {
    if(periodicidad.equals("MENSUAL")){
      if(mes.equals(1))
        organizacion.recalcularHC(12, anio -1, 12, anio -1);
      else
        organizacion.recalcularHC(mes-1, anio, mes-1, anio);
    }
    else{
      if(mes.equals(1)){
        organizacion.recalcularHC(1, anio-1, 12, anio-1);
      }
      else{
        organizacion.recalcularHC(1, anio, mes-1, anio);
      }
    }
  }

  public Actividad crearActividad(String tipoActividad,
                                 String tipoConsumo,
                                 String periodicidad,
                                 Integer mes,
                                 Integer anio,
                                 Double valorConsumo,
                                  Organizacion organizacion)
  {
    TipoDeActividad tipoDeActividad = TipoDeActividad.valueOf(tipoActividad);
    TipoDeConsumo tipoDeConsumo = TipoDeConsumo.valueOf(tipoConsumo);
    TipoPeriodicidad tipoPeriodicidad = TipoPeriodicidad.valueOf(periodicidad);


    // Del String periodoImputacion se pasa a LocalDate dado que sólo maneja fechas (sin horas)
    //LocalDate fechaPeriodoImputacion = LocalDate.parse(periodo_inputacion, DateTimeFormatter.ofPattern("M/d/yy"));
    // Se pasa a Date el LocalDate
    //Date datePeriodoImputacion = Date.valueOf(fechaPeriodoImputacion);
    /*
    System.out.println("La fecha con LocalDate: " + fechaPeriodoImputacion);
    System.out.println("La fecha con Date: " + Date.from(fechaPeriodoImputacion.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    System.out.println("La fecha con Date Formateada: " + (new SimpleDateFormat("dd/MM/yyyy")).format(Date.from(fechaPeriodoImputacion.atStartOfDay(ZoneId.systemDefault()).toInstant())));
     */

    RepositorioFactoresEmisionDB repositorioFactoresEmisionDB = new RepositorioFactoresEmisionDB();
    FactorEmision factorEmision = repositorioFactoresEmisionDB.getFactorDeEmisionSegunActividad(TipoDeActividad.valueOf(tipoActividad));

    Actividad actividad = new Actividad(
            tipoDeActividad,
            tipoDeConsumo,
            tipoPeriodicidad,
            organizacion,
            factorEmision
    );

    actividad.cargarConsumos(mes, anio, valorConsumo);
    /*if(periodicidad.equals(FrecuenciaServicio.MENSUAL.toString())){
      actividad.agregarConsumo(mes, anio, valorConsumo);
    }else{
      for(int i = 1; i < mes; i++){
        actividad.agregarConsumo(i, anio, valorConsumo/(mes -1));
      }
    }*/
    return actividad;
  }

  /*
  public static void main(String[] args) throws IOException, IOError, ParseException {
    ServicioExcel lector = ServicioExcel.getInstance();
    lector.cargarMediciones("example.xls");
  }
   */
}