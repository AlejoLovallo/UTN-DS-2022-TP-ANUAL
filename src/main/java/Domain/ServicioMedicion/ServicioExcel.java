package Domain.ServicioMedicion;

import Domain.Organizacion.Excepciones.ImposibilidadDeCrearWorkbookException;
import Domain.Organizacion.Excepciones.ImposiblidadDeCerrarWorkbookException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
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
  public void cargarMediciones(String fileName) throws IOException, IOError {
    Workbook workbook = null;
    try{
      File file = new File("src/main/java/Domain/Utils/"+ fileName);
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
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();

      // Now let's iterate over the columns of the current row
      Iterator<Cell> cellIterator = row.cellIterator();

      while (cellIterator.hasNext()) {
        Cell cell = cellIterator.next();
        String cellValue = dataFormatter.formatCellValue(cell);

        System.out.print(cellValue + "\t");
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

  }

  /*public static void main(String[] args) throws IOException, IOError {
    LectorDeActividades lector = LectorDeActividades.getInstance();
    lector.leerActividades("example.xls");
  }
   */
}
