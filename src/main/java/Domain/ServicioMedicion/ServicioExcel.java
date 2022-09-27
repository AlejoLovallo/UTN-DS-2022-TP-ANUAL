package Domain.ServicioMedicion;

import Domain.Organizacion.*;
import Domain.Organizacion.Excepciones.ImposibilidadDeCrearWorkbookException;
import Domain.Organizacion.Excepciones.ImposiblidadDeCerrarWorkbookException;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
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

        Optional<Actividad> act = org.getActividades().stream().filter(unaActividad -> tipoActividad.equals(unaActividad.getNombre().toString()) && tipoConsumo.equals(unaActividad.getTipoDeConsumo().toString())).findAny();

        Actividad actividad = act.get(); // CHEQUEAR ESTO PORQUE ES UN DESASTRE

        if (actividad != null){
          if(periodicidad.equals(FrecuenciaServicio.MENSUAL.toString())){
              actividad.agregarConsumo(mes, anio, valorConsumo);
            }else{
              for(int i = 1; i < mes; i++){
                actividad.agregarConsumo(i, anio, valorConsumo/(mes -1));
              }
            }
        }else{

          actividades.add(crearActividad(tipoActividad, tipoConsumo, periodicidad, mes, anio, valorConsumo));

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

  public Actividad crearActividad(String tipoActividad,
                                 String tipoConsumo,
                                 String periodicidad,
                                 Integer mes,
                                 Integer anio,
                                 Double valorConsumo)
  {
    TipoDeActividad tipoDeActividad = TipoDeActividad.valueOf(tipoActividad);
    TipoDeConsumo tipoDeConsumo = TipoDeConsumo.valueOf(tipoConsumo);


    // Del String periodoImputacion se pasa a LocalDate dado que sólo maneja fechas (sin horas)
    //LocalDate fechaPeriodoImputacion = LocalDate.parse(periodo_inputacion, DateTimeFormatter.ofPattern("M/d/yy"));
    // Se pasa a Date el LocalDate
    //Date datePeriodoImputacion = Date.valueOf(fechaPeriodoImputacion);
    /*
    System.out.println("La fecha con LocalDate: " + fechaPeriodoImputacion);
    System.out.println("La fecha con Date: " + Date.from(fechaPeriodoImputacion.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    System.out.println("La fecha con Date Formateada: " + (new SimpleDateFormat("dd/MM/yyyy")).format(Date.from(fechaPeriodoImputacion.atStartOfDay(ZoneId.systemDefault()).toInstant())));
     */

    Actividad actividad = new Actividad(
            tipoDeActividad,
            tipoDeConsumo
    );

    if(periodicidad.equals(FrecuenciaServicio.MENSUAL.toString())){
      actividad.agregarConsumo(mes, anio, valorConsumo);
    }else{
      for(int i = 1; i < mes; i++){
        actividad.agregarConsumo(i, anio, valorConsumo/(mes -1));
      }
    }
    return actividad;
  }

  /*
  public static void main(String[] args) throws IOException, IOError, ParseException {
    ServicioExcel lector = ServicioExcel.getInstance();
    lector.cargarMediciones("example.xls");
  }
   */
}
