package Domain.Organizacion;

public class Actividad {
  private TipoDeActividad nombre;
  private TipoDeConsumo tipoDeConsumo;
  private UnidadDeConsumo unidadDeConsumo;
  private Double consumo;

  //CONSTRUCTOR
  public Actividad(TipoDeActividad _tipoActividad,TipoDeConsumo _tipoDeConsumo,UnidadDeConsumo _unidadConsumo){
    this.nombre=_tipoActividad;
    this.tipoDeConsumo=_tipoDeConsumo;
    this.unidadDeConsumo=_unidadConsumo;
  }

  //getters
  public TipoDeActividad getNombre() {return nombre;}

  public TipoDeConsumo getTipoDeConsumo() {return tipoDeConsumo;}

  public UnidadDeConsumo getUnidadDeConsumo() {return unidadDeConsumo;}

  public Double getConsumo() {return consumo;}

  //setters
  public void setConsumo(Double consumo) {this.consumo = consumo;}

  public void setUnidadDeConsumo(UnidadDeConsumo unidadDeConsumo) {this.unidadDeConsumo = unidadDeConsumo;}

  public void setTipoDeConsumo(TipoDeConsumo tipoDeConsumo) {this.tipoDeConsumo = tipoDeConsumo;}

  public void setNombre(TipoDeActividad nombre) {this.nombre = nombre;}
}
