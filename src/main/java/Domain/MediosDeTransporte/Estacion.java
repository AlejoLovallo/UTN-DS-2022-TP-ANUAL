package Domain.MediosDeTransporte;

public class Estacion {
  private String nombre;
  private Integer numeroDeEstacion;

  private Float distEstAnt;

  private Float distEstPos;


  //////////////////////////////////  GETTERS

  public String getNombre() {
    return nombre;
  }

  public Integer getNumeroDeEstacion() {
    return numeroDeEstacion;
  }

  public Float getDistEstAnt(){
    return distEstAnt;
  }

  public Float getDistEstPos() {
    return distEstPos;
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
