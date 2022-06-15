package Domain.Trayecto;

import Domain.MediosDeTransporte.Estacion;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.Espacios.Espacio;
import Domain.MediosDeTransporte.TransportePublico;
import Domain.MediosDeTransporte.VehiculoParticular;

public class Tramo {
  private Espacio puntoPartida;
  private Espacio puntoLLegada;
  private MedioDeTransporte medio;

  private Integer cantPasajeros;

  public Tramo(MedioDeTransporte _medio, Espacio _puntoPartida, Espacio _puntoLlegada){
    this.medio = _medio;
    this.puntoPartida = _puntoPartida;
    this.puntoLLegada = _puntoLlegada;
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
  public Double determinarDistancia(){
    Double distanciaTotal = 0;
    if (medio instanceof TransportePublico){
      Estacion estPartida = (Estacion) this.puntoPartida;
      Estacion estLlegada = (Estacion) this.puntoLLegada;
      Integer estacionInicial = estPartida.getNumeroDeEstacion();
      Integer estacionFinal = estLlegada.getNumeroDeEstacion();
      for (int i = estacionInicial; i <= estacionFinal; i++){
        distanciaTotal += medio.paradas(i).distEstPost;
      }
    }
    // else if medio instanceOf VehiculoParticular
    else {
      return 0.0;
    }
  }

}
