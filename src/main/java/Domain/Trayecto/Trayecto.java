package Domain.Trayecto;

import Domain.Espacios.Espacio;
import Domain.Organizacion.Organizacion;

import java.util.List;


public class Trayecto {
  private List <Tramo> tramos;
  private Organizacion organizacion;

  //////////////////////////////////  GETTERS

  public List<Tramo> getTramos(){
    return this.tramos;
  }

  public Organizacion getOrganizacion(){
    return this.organizacion;
  }

  public Espacio getPuntoDePartida() {
    Tramo tramoInicial = tramos.get(0);
    return tramoInicial.getPuntoPartida();
  }

  public Espacio getPuntoDeLlegada(){
    Tramo tramoFinal = tramos.get(tramos.size()-1);
    return tramoFinal.getPuntoLlegada();
  }

  //////////////////////////////////  SETTERS

  public void setTramos(List<Tramo> tramos) {
    this.tramos = tramos;
  }

  public void setOrganizacion(Organizacion organizacion) {
    this.organizacion = organizacion;
  }

  //////////////////////////////////  INTERFACE

  public double determinarDistanciaTotal(){

    double distanciaTotal = 0.0;

    for(Tramo tramo : getTramos()){
      distanciaTotal += tramo.determinarDistancia();
    }

    return distanciaTotal;
  }
}
