package Domain.Organizacion;
import Domain.Miembro.Miembro;
import Domain.Ubicacion;

import java.util.List;

public class Organizacion {
  private String razonSocial;
  private TipoOrganizacion tipo;
  private ClasificacionOrganizacion clasificacion;
  private Ubicacion ubicacion;
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

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
  }

  public List<Sector> getSectores() {
    return sectores;
  }

  public void setSectores(List<Sector> sectores) {
    this.sectores = sectores;
  }

  public List<Miembro> getMiembros() {
    return miembros;
  }

  public void setMiembros(List<Miembro> miembros) {
    this.miembros = miembros;
  }
}
