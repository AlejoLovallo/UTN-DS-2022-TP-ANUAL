package Domain.Trayecto;

import Domain.CalculadorDistancia.ServicioAPI;
import Domain.CalculadorDistancia.ServicioManual;
import Domain.Espacios.Espacio;
import Domain.CalculadorDistancia.ServicioDistancia;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.TransportePublico;
import Domain.MediosDeTransporte.VehiculoParticular;

import java.io.IOException;


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
    if (medio instanceof TransportePublico){
      this.estrategia = new ServicioManual();
    } else if(medio instanceof VehiculoParticular){
      this.estrategia = ServicioAPI.getInstance();
    }

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

  public Double determinarDistancia() throws IOException {
    return estrategia.calcularDistancia(medio, puntoPartida, puntoLLegada);
  }
}
