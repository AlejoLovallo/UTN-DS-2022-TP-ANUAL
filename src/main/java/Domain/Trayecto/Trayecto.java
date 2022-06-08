package Domain.Trayecto;

import Domain.Organizacion.Organizacion;
import Domain.Trayecto.Tramo;

import java.util.List;

public class Trayecto {
  private List<Tramo> tramos;
  private Organizacion organizacion;

  //////////////////////////////////  GETTERS


  public List<Tramo> getTramos() {
    return tramos;
  }

  public Organizacion getOrganizacion() {
    return organizacion;
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

  public void setTramos(List<Tramo> tramos) {
    this.tramos = tramos;
  }

  public void setOrganizacion(Organizacion organizacion) {
    this.organizacion = organizacion;
  }

  //////////////////////////////////  INTERFACE

  public Float determinarDistanciaTotal(){

    Float distanciaTotal = 0;

    for(Tramo tramo : getTramos()){
      distanciaTotal += tramo.determinarDistancia();
    }

    return distanciaTotal;
  }
}
