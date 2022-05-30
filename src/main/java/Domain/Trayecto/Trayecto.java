package Domain.Trayecto;

import Domain.Organizacion.Organizacion;

import java.util.ArrayList;

public class Trayecto {
  private ArrayList<Tramo> tramos;
  private Organizacion organizacion;

  //////////////////////////////////  GETTERS

  public ArrayList<Tramo> getTramos(){
    return this.tramos;
  }

  public Organizacion getOrganizacion(){
    return this.organizacion;
  }

  public String getPuntoDePartida() {
    Tramo tramoInicial = tramos.get(0);
    return tramoInicial.getPuntoPartida();
  }

  public String getPuntoDeLlegada(){
    Tramo tramoFinal = tramos.get(tramos.size()-1);
    return tramoFinal.getPuntoLlegada();
  }

  //////////////////////////////////  SETTERS

  //////////////////////////////////  INTERFACE

}
