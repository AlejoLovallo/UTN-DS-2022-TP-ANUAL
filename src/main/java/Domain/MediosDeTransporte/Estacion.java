package Domain.MediosDeTransporte;

import Domain.Espacios.Espacio;
import Domain.Espacios.TipoEspacio;

public class Estacion extends Espacio {
  private String nombre;
  public Integer numeroDeEstacion;
  public double distEstAnt;
  public double distEstPos;

  //////////////////////////////////  CONSTRUCTOR

  public Estacion(String _calle, Integer _altura, String nombre, Integer numeroDeEstacion, Float distEstAnt, Float distEstPos) {
    super("", 0, TipoEspacio.Estacion);
    this.nombre = nombre;
    this.numeroDeEstacion = numeroDeEstacion;
    this.distEstAnt = distEstAnt;
    this.distEstPos = distEstPos;
  }


  //////////////////////////////////  GETTERS

  public String getNombre() {
    return this.nombre;
  }

  public Integer getNumeroDeEstacion() {
    return this.numeroDeEstacion;
  }

  public double getDistEstAnt(){
    return this.distEstAnt;
  }

  public double getDistEstPos() {
    return this.distEstPos;
  }

  //////////////////////////////////  SETTERS

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setNumeroDeEstacion(Integer numeroDeEstacion) {
    this.numeroDeEstacion = numeroDeEstacion;
  }

  public void setDistEstAnt(Float distEstAnt) {
    this.distEstAnt = distEstAnt;
  }

  public void setDistEstPos(Float distEstPos) {
    this.distEstPos = distEstPos;
  }

  //////////////////////////////////  INTERFACE

}
