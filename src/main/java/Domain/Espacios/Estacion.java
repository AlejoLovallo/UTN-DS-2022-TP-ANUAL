package Domain.Espacios;

import Domain.Espacios.Espacio;
import Domain.Espacios.TipoDireccion;
import javax.persistence.*;

@Entity
@Table(name="estacion")
public class Estacion extends Espacio {
  @Column
  private String nombre;
  @Column
  public Integer numeroDeEstacion;
  @Column
  public double distEstAnt;
  @Column
  public double distEstPos;

  //////////////////////////////////  CONSTRUCTOR
  public Estacion(){

  }


  public Estacion(String nombre, Integer numeroDeEstacion, double distEstAnt, double distEstPos) {
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
