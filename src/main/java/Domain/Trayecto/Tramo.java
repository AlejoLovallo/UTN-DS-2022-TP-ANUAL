package Domain.Trayecto;

import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.Espacios.Espacio;

public class Tramo {
  private Espacio puntoPartida;
  private Espacio puntoLLegada;
  private MedioDeTransporte medio;

  //////////////////////////////////  GETTERS
  public String getPuntoPartida(){
    return this.puntoPartida.toString();
  }

  public String getPuntoLlegada(){
    return this.puntoLLegada.toString();
  }

  public MedioDeTransporte getMedioTransporte(){
    return this.medio;
  }

  //////////////////////////////////  SETTERS

  //////////////////////////////////  INTERFACE

}
