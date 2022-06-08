package Domain.Trayecto;

import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.Espacios.Espacio;

public class Tramo {
  private Espacio puntoPartida;
  private Espacio puntoLLegada;
  private MedioDeTransporte medio;

  private Integer cantPasajeros;


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

  public Integer getCantPasajeros() {
    return cantPasajeros;
  }

  //////////////////////////////////  SETTERS


  public void setPuntoPartida(Espacio puntoPartida) {
    this.puntoPartida = puntoPartida;
  }

  public void setPuntoLLegada(Espacio puntoLLegada) {
    this.puntoLLegada = puntoLLegada;
  }

  public void setMedio(MedioDeTransporte medio) {
    this.medio = medio;
  }

  public void setCantPasajeros(Integer cantPasajeros) {
    this.cantPasajeros = cantPasajeros;
  }


  //////////////////////////////////  INTERFACE

}
