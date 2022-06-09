package Domain.Organizacion;
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
import java.util.Iterator;

public class LectorDeActividades {
  public static void main(String[] args) throws IOException, IOError {

    // Use a file
    // Creating a Workbook from an Excel file (.xls or .xlsx)
    Workbook workbook = null;
    try {
      File f = new File("src/main/java/Domain/Utils/example.xls");
      workbook = WorkbookFactory.create(f);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Retrieving the number of sheets in the Workbook
    if (workbook != null) {
      System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
    }
        /*
           ==================================================================
           Iterating over all the rows and columns in a Sheet (Multiple ways)
           ==================================================================
        */

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

    // 2. Or you can use a for-each loop to iterate over the rows and columns
    System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
    for (Row row: sheet) {
      for(Cell cell: row) {
        String cellValue = dataFormatter.formatCellValue(cell);
        System.out.print(cellValue + "\t");
      }
      System.out.println();
    }

    // 3. Or you can use Java 8 forEach loop with lambda
    System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
    sheet.forEach(row -> {
      row.forEach(cell -> {
        String cellValue = dataFormatter.formatCellValue(cell);
        System.out.print(cellValue + "\t");
      });
      System.out.println();
    });

    // Closing the workbook
    try {
      workbook.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
