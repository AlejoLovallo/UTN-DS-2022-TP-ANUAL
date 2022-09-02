package Domain.ServicioMedicion;

import java.util.Date;

public class Actividad {
  private TipoDeActividad nombre;
  private TipoDeConsumo tipoDeConsumo;
  private UnidadDeConsumo unidadDeConsumo;
  private FrecuenciaServicio frecuenciaServicio;
  private Date fechaCarga;
  private Double consumo;

  //CONSTRUCTOR
  public Actividad(TipoDeActividad _tipoActividad,TipoDeConsumo _tipoDeConsumo,FrecuenciaServicio _frecuenciaServicio, Date _fechaCarga,UnidadDeConsumo _unidadConsumo){
    this.nombre = _tipoActividad;
    this.tipoDeConsumo = _tipoDeConsumo;
    this.unidadDeConsumo = _unidadConsumo;
    this.frecuenciaServicio = _frecuenciaServicio;
    this.fechaCarga = _fechaCarga;
  }

  //getters
  public TipoDeActividad getNombre() {return nombre;}

  public TipoDeConsumo getTipoDeConsumo() {return tipoDeConsumo;}

  public UnidadDeConsumo getUnidadDeConsumo() {return unidadDeConsumo;}

  public Double getConsumo() {return consumo;}

  public FrecuenciaServicio getFrecuenciaServicio() { return frecuenciaServicio; }

  public Date getFechaCarga() { return fechaCarga; }

  //setters
  public void setConsumo(Double consumo) {this.consumo = consumo;}

  public void setUnidadDeConsumo(UnidadDeConsumo unidadDeConsumo) {this.unidadDeConsumo = unidadDeConsumo;}

  public void setTipoDeConsumo(TipoDeConsumo tipoDeConsumo) {this.tipoDeConsumo = tipoDeConsumo;}

  public void setNombre(TipoDeActividad nombre) {this.nombre = nombre;}
}
