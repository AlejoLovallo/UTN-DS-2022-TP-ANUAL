package Domain.ServicioMedicion;

import Domain.Organizacion.Excepciones.ImposibilidadDeCrearWorkbookException;
import Domain.Organizacion.Excepciones.ImposiblidadDeCerrarWorkbookException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class ServicioExcel extends ServicioMediciones{
  private static ServicioExcel instance = null;

  public static ServicioExcel getInstance(){
    if (instance == null){
      instance = new ServicioExcel();
    }
    return instance;
  }

  @Override
  public ArrayList<Actividad> cargarMediciones(String fileName) throws IOException, IOError, ParseException {
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
        //System.out.print(valor + "\t");

        cell = cellIterator.next();
        String periodicidad = dataFormatter.formatCellValue(cell);
        //System.out.print(periodicidad + "\t");

        cell = cellIterator.next();
        String periodo_imputacion = dataFormatter.formatCellValue(cell);
        //System.out.print(periodo_imputacion + "\t");

        actividades.add(leerActividad(tipoActividad, tipoConsumo, valor, periodicidad, periodo_imputacion));
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

  public Actividad leerActividad(String tipoActividad,
                                 String tipoConsumo,
                                 String valor,
                                 String periodicidad,
                                 String periodo_inputacion) throws ParseException {
    TipoDeActividad tipoDeActividad = TipoDeActividad.valueOf(tipoActividad);
    TipoDeConsumo tipoDeConsumo = TipoDeConsumo.valueOf(tipoConsumo);
    Double valorConsumo = Double.parseDouble(valor);

    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("M/d/y");
    java.util.Date fecha = formatoDelTexto.parse(periodo_inputacion);

    //System.out.print("La fecha transformada es: " + fecha);

    Actividad actividad = new Actividad(
            tipoDeActividad,
            tipoDeConsumo,
            FrecuenciaServicio.valueOf(periodicidad),
            fecha,
            null
    );

    actividad.setConsumo(valorConsumo);

    return actividad;
  }
/*
  public static void main(String[] args) throws IOException, IOError {
    ServicioExcel lector = ServicioExcel.getInstance();
    lector.cargarMediciones("example.xls");
  }*/
}
