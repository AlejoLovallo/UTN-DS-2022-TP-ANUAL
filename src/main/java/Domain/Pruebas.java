package Domain;

import Domain.Espacios.Espacio;
import Domain.Espacios.TipoEspacio;

public class Pruebas {
  public static void main(String[] args){
    TipoEspacio tipoEspacio = TipoEspacio.Trabajo;
    TipoEspacio t2 = TipoEspacio.Vivienda;
    TipoEspacio t3 = TipoEspacio.Otro;

    Espacio e = new Espacio("PPP",123,TipoEspacio.Trabajo);
    System.out.println("-------- INICIO DE PRUEBAS --------");
    System.out.println(tipoEspacio);
    System.out.println(t2);
    System.out.println(t3);
    System.out.println(e.toString());
  }
}
