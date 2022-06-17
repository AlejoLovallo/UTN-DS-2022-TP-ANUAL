package Domain.Trayecto;

import Domain.CalculadorDistancia.ServicioDistancia;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.Espacios.Espacio;

public class Tramo {
  private ServicioDistancia estrategia;
  private Espacio puntoPartida;
  private Espacio puntoLLegada;
  private MedioDeTransporte medio;

  private Integer cantPasajeros;

  //////////////////////////////////  CONSTRUCTOR

  public Tramo(Espacio _puntoPartida, Espacio _puntoLLegada, MedioDeTransporte _medio, Integer _cantPasajeros){
    this.puntoPartida = _puntoPartida;
    this.puntoLLegada = _puntoLLegada;
    this.medio = _medio;
    this.cantPasajeros = _cantPasajeros;
  }

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
