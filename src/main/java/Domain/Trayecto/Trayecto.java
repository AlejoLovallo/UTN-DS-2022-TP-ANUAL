package Domain.Trayecto;

import Domain.Espacios.Espacio;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.Miembro.Miembro;
import Domain.Organizacion.Organizacion;

import java.util.List;


public class Trayecto {
  private ArrayList<Tramo> tramos;
  private Miembro miembro;

  //////////////////////////////////  CONSTRUCTOR
  public Trayecto(){}

  public Trayecto(ArrayList<Tramo> _tramos, Miembro _miembro){
    this.tramos = _tramos;
    this.miembro = _miembro;
  }

  //////////////////////////////////  GETTERS

  public List<Tramo> getTramos(){
    return this.tramos;
  }

  public Miembro getMiembro(){
    return this.miembro;
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

  public Boolean setMiembro(Miembro _miembro){
    this.miembro = _miembro;
    return true;
  }

  //////////////////////////////////  INTERFACE
  public Boolean addTramo(Tramo _tramo){
    tramos.add(_tramo);
    return true;
  }


  public double determinarDistanciaTotal(){

    double distanciaTotal = 0.0;

    for(Tramo tramo : getTramos()){
      distanciaTotal += tramo.determinarDistancia();
    }

    return distanciaTotal;
  }
}
