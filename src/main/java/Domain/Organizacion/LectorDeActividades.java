package Domain.Organizacion;
import Domain.Organizacion.Excepciones.ImposibilidadDeCrearWorkbookException;
import Domain.Organizacion.Excepciones.ImposiblidadDeCerrarWorkbookException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Iterator;

public class LectorDeActividades {
  private static LectorDeActividades instance = null;

  public static LectorDeActividades getInstance(){
    if (instance == null){
      instance = new LectorDeActividades();
    }
    return instance;
  }

  public void leerActividades(String fileName) throws  IOException, IOError{
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

  public static void main(String[] args) throws IOException, IOError {
    LectorDeActividades lector = LectorDeActividades.getInstance();
    lector.leerActividades("example.xls");
  }
}