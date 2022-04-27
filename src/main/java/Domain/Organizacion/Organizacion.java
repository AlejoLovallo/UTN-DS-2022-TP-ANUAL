package Domain.Organizacion;

import Domain.Ubicacion;

public class Organizacion {
  private TipoOrganizacion tipo;
  private ClasificacionOrganizacion clasificacion;
  private String razonSocial;
  private Ubicacion ubicacion;

  public TipoOrganizacion getTipo() {
    return tipo;
  }

  public void setTipo(TipoOrganizacion tipo) {
    this.tipo = tipo;
  }

  public ClasificacionOrganizacion getClasificacion() {
    return clasificacion;
  }

  public void setClasificacion(ClasificacionOrganizacion clasificacion) {
    this.clasificacion = clasificacion;
  }

  public String getRazonSocial() {
    return razonSocial;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
  }
}
