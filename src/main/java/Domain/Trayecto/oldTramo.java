package Domain.Trayecto;

import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.Espacios.Espacio;

public class oldTramo {
  private Espacio puntoPartida;
  private Espacio puntoLLegada;
  private MedioDeTransporte medio;

  //////////////////////////////////  CONSTRUCTOR

  public oldTramo(Espacio puntoPartida, Espacio puntoLLegada, MedioDeTransporte medio) {
    this.puntoPartida = puntoPartida;
    this.puntoLLegada = puntoLLegada;
    this.medio = medio;
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


  //////////////////////////////////  INTERFACE

  public int determinarDistancia(){
    Float distanciaTotal = 0;
    if (medio == "TransportePublico"){
      int estacionInicial = puntoPartida.numeroDeEstacion;
      int estacionFinal = estacion2.numeroDeEstacion;
      for (int i = estacionInicial; i <= estacionFinal; i++){
        distanciaTotal += medio.paradas(i).distEstPost;
      }
    }
  }

}
