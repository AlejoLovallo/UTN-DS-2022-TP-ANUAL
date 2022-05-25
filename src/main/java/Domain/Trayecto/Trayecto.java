package Domain.Trayecto;

import Domain.Organizacion.Organizacion;

import java.util.List;

public class Trayecto {
  private List<Tramo> tramos;
  private Organizacion organizacion;

  //////////////////////////////////  GETTERS

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
