package Domain.Trayecto;

import Domain.Espacios.Espacio;
import Domain.Espacios.Direccion;
import Domain.Espacios.Estacion;
import Domain.CalculadorDistancia.ServicioDistancia;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.TransportePublico;
import Domain.MediosDeTransporte.VehiculoParticular;

import java.io.IOException;
import java.util.List;

public class Tramo {

  private ServicioDistancia estrategia;
  private Espacio puntoPartida;
  private Espacio puntoLLegada;
  private MedioDeTransporte medio;

  //////////////////////////////////  CONSTRUCTOR

  public Tramo(Espacio puntoPartida, Espacio puntoLLegada, MedioDeTransporte medio) {
    this.puntoPartida = puntoPartida;
    this.puntoLLegada = puntoLLegada;
    this.medio = medio;
  }

  //////////////////////////////////  GETTERS
  public Espacio getPuntoPartida(){
    return this.puntoPartida;
  }

  public Espacio getPuntoLlegada(){
    return this.puntoLLegada;
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

  public Double determinarDistancia(){
    Double distanciaTotal = 0.0;
    if (medio instanceof TransportePublico){
      TransportePublico transportePublico = (TransportePublico) medio;
      Estacion est = (Estacion) puntoPartida;
      Estacion est2 = (Estacion) puntoLLegada;
      Integer estacionInicial = est.getNumeroDeEstacion();
      Integer estacionFinal = est2.getNumeroDeEstacion();
      for (int i = 0; i < transportePublico.getParadas().size(); i ++){
        if(transportePublico.getParadas().get(i).getNumeroDeEstacion() > estacionInicial
                && transportePublico.getParadas().get(i).getNumeroDeEstacion() < estacionFinal){
          distanciaTotal += transportePublico.getParadas().get(i).getDistEstPos();
        }
      }
    }else if (medio instanceof VehiculoParticular){
      estrategia.calcularDistancia()
    }
    return distanciaTotal;
  }

}
