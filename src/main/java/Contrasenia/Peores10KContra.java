package Contrasenia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Peores10KContra implements CriterioValidacion {
  //todo
  public boolean validarContrasenia(String contrasenia) {



    ArrayList<String> peoresContras = new ArrayList<>();
    String st;


    try {
      File file = new File("10kPasswords.txt");
      Scanner myReader = new Scanner(file);
      while (myReader.hasNextLine()) {
        st = myReader.nextLine();
        peoresContras.add(st);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      //todo poner una mejor excepcion
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    //todo testear 
    return peoresContras.stream().anyMatch(contrasenia::equals);
  }
}
