package Domain.MediosDeTransporte;

import Domain.Espacios.Espacio;
import Domain.Espacios.TipoEspacio;

public class Estacion extends Espacio {
  private String nombre;
  private Integer numeroDeEstacion;

  public Estacion(String _nombre, Integer _numEstacion, String _calle, Integer _altura, TipoEspacio _tipoEspacio ){
    super(_calle,_altura,_tipoEspacio);
    this.nombre = _nombre;
    this.numeroDeEstacion = _numEstacion;
  }


  //////////////////////////////////  GETTERS

  public String getNombre() {
    return nombre;
  }

  public Integer getNumeroDeEstacion() {
    return numeroDeEstacion;
  }

  //////////////////////////////////  SETTERS

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setNumeroDeEstacion(Integer numeroDeEstacion) {
    this.numeroDeEstacion = numeroDeEstacion;
  }

  //////////////////////////////////  INTERFACE

}
