package Domain.Organizacion;
import Domain.Miembro.Miembro;

import java.util.List;

public class Organizacion {
  private String razonSocial;
  private TipoOrganizacion tipo;
  private ClasificacionOrganizacion clasificacion;
  private List<Sector>  sectores;
  private List<Miembro> miembros;

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
}
