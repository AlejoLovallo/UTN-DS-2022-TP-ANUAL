package Domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String [] args){

  }

  public static String pedirPorPantallaString() {
    String res = "";
    try {
      BufferedReader entrada =
          new BufferedReader(new InputStreamReader(System.in));
      res = entrada.readLine();
    }
    catch (IOException e) {}

    return res;
  }

  public static int pedirPorPantallaInt() {
    String dato = pedirPorPantallaString();

    int res = Integer.parseInt(dato);
    return res;
  }

  public static double pedirPorPantallaDouble() {
    String dato = pedirPorPantallaString();

    double res = Double.parseDouble(dato);
    return res;
  }
}
