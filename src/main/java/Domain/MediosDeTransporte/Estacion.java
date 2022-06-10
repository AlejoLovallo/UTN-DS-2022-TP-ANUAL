package Domain.MediosDeTransporte;

public class Estacion {
  private String nombre;
  private Integer numeroDeEstacion;

  //////////////////////////////////  CONSTRUCTORES
  public Estacion(String _nombre, Integer _numeroDeEstacion){
    this.nombre = _nombre;
    this.numeroDeEstacion = _numeroDeEstacion;
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
