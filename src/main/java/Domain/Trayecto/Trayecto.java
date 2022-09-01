package Domain.Trayecto;

import Domain.Espacios.Espacio;
import Domain.Miembro.Miembro;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Trayecto {
  private ArrayList<Tramo> tramos;
  private Integer frecuenciaSemanal;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private Miembro miembro;


  //////////////////////////////////  CONSTRUCTOR
  public Trayecto(){}

  public Trayecto(ArrayList<Tramo> _tramos,
                  Miembro _miembro,
                  Integer _frecuencia,
                  LocalDate _fechaInicio,
                  LocalDate _fechaFin,
                  Boolean _active){
    this.tramos = _tramos;
    this.miembro = _miembro;
    this.setFrecuenciaSemanal(_frecuencia);
    this.setFechaInicio(_fechaInicio);
    this.setFechaFin(_fechaFin);
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

  public LocalDate getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(LocalDate fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public LocalDate getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(LocalDate fechaFin) {
    this.fechaFin = fechaFin;
  }

  public Integer diasDelMesActivo(Integer mes, Integer año) {

    if (año >= this.fechaInicio.getYear() &&  año <= this.fechaFin.getYear()){

      if(año == this.fechaInicio.getYear()){

        if(año == this.fechaFin.getYear()){
  
          if(mes > this.fechaInicio.getMonthValue() &&  mes < this.fechaFin.getMonthValue())
            return numeroDeDiasMes(mes, año);
          
          if(mes == this.fechaInicio.getMonthValue()){
  
            if(mes == this.fechaFin.getMonthValue())
              return numeroDeDiasMes(mes, año) - this.fechaInicio.getDayOfMonth() - this.fechaFin.getDayOfMonth();
          
            return numeroDeDiasMes(mes, año) - this.fechaInicio.getDayOfMonth();
          }else {
            return numeroDeDiasMes(mes, año) - this.fechaFin.getDayOfMonth();
          }
        }
      }else if(año == this.fechaFin.getYear()){
        
        if(mes < this.fechaFin.getMonthValue())
          return numeroDeDiasMes(mes, año);
        else if(mes == this.fechaFin.getMonthValue())
          return numeroDeDiasMes(mes, año) - this.fechaFin.getDayOfMonth();
        else
          return 0;  
      } else{
        return numeroDeDiasMes(mes, año);
      }
    } else {
      return 0;
    }
    return 0;
  }

  /**
   * Devuelve el número de dias del mes (número) pasado por parámetro
   * Si es Febrero tiene en cuenta si este año es bisiesto o no
   * Empieza por 1
   */
  public static Integer numeroDeDiasMes(Integer mes, Integer año){
  
    Integer numeroDias=-1;

        switch(mes){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numeroDias=31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numeroDias=30;
                break;
            case 2:
                if(esBisiesto(año)){
                    numeroDias=29;
                }else{
                    numeroDias=28;
                }
                break;

        }

    return numeroDias;
  }

  /**
  * Indica si un año es bisiesto o no
  */
  public static boolean esBisiesto(int año) {

    GregorianCalendar calendar = new GregorianCalendar();
    boolean esBisiesto = false;
    if (calendar.isLeapYear(año)) {
        esBisiesto = true;
    }
    return esBisiesto;
  }

}