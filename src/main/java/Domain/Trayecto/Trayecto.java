package Domain.Trayecto;

import Domain.Espacios.Espacio;
import Domain.Miembro.Miembro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class Trayecto {
  private ArrayList<Tramo> tramos;
  private Integer frecuenciaSemanal;
  private Date fechaInicio;
  private Date fechaFin;
  private Miembro miembro;
  private Boolean active;

  //////////////////////////////////  CONSTRUCTOR
  public Trayecto(){}

  public Trayecto(ArrayList<Tramo> _tramos,
                  Miembro _miembro,
                  Integer _frecuencia,
                  Date _fechaInicio,
                  Date _fechaFin,
                  Boolean _active){
    this.tramos = _tramos;
    this.miembro = _miembro;
    this.setFrecuenciaSemanal(_frecuencia);
    this.setFechaInicio(_fechaInicio);
    this.setFechaFin(_fechaFin);
    this.setActive(_active);
  }

  //////////////////////////////////  GETTERS

  public ArrayList<Tramo> getTramos(){
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

  public void setTramos(ArrayList<Tramo> tramos) {
    this.tramos = tramos;
  }

  public Boolean setMiembro(Miembro _miembro){
    this.miembro = _miembro;
    return true;
  }

  //////////////////////////////////  INTERFACE
  public Boolean addTramo(Tramo _tramo){
    this.tramos.add(_tramo);
    return true;
  }


  public double determinarDistanciaTotal() throws IOException {

    double distanciaTotal = 0.0;

    for(Tramo tramo : this.getTramos()){
      distanciaTotal += tramo.determinarDistancia();
    }

    return distanciaTotal;
  }

  public Integer getFrecuenciaSemanal() {
    return frecuenciaSemanal;
  }

  public void setFrecuenciaSemanal(Integer frecuenciaSemanal) {
    this.frecuenciaSemanal = frecuenciaSemanal;
  }

  public Date getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(Date fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public Date getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(Date fechaFin) {
    this.fechaFin = fechaFin;
  }

  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
}
