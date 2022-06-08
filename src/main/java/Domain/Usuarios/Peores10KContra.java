package Domain.Usuarios;

import Domain.Contrasenia.Excepciones.ArchivoInaccesibleException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Peores10KContra implements CriterioValidacion {
  //////////////////////////////////  VARIABLES
  private static String rutaPeoresContra = "10kPasswords.txt";

  //////////////////////////////////  CONSTRUCTORES

  //////////////////////////////////  GETTERS

  //////////////////////////////////  SETTERS

  //////////////////////////////////  INTERFACE
  public boolean validarContrasenia(String contrasenia) {
    ArrayList<String> peoresContras = new ArrayList<>();
    String st;

    try {
      File file = new File(rutaPeoresContra);
      Scanner myReader = new Scanner(file);
      while (myReader.hasNextLine()) {
        st = myReader.nextLine();
        peoresContras.add(st);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      //todo poner una mejor excepcion
      throw new ArchivoInaccesibleException();
    }

    //todo testear 
    return peoresContras.stream().noneMatch(contrasenia::equals);
  }
}
