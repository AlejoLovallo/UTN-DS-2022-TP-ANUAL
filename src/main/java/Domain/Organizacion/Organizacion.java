package Domain.Organizacion;
import Domain.Miembro.Miembro;

import java.util.List;

public class Organizacion {
  private String razonSocial;
  private TipoOrganizacion tipo;
  private ClasificacionOrganizacion clasificacion;
  private List<Sector>  sectores;
  private List<Miembro> miembros;

  //////////////////////////////////  CONSTRUCTORES
  public Organizacion(String _razonSocial, TipoOrganizacion _tipo, ClasificacionOrganizacion _clasificacion){
    this.razonSocial = _razonSocial;
    this.tipo = _tipo;
    this.clasificacion = _clasificacion;
  }

  //////////////////////////////////  GETTERS
  public TipoOrganizacion getTipo() {
    return tipo;
  }

  public ClasificacionOrganizacion getClasificacion() {
    return clasificacion;
  }

  public String getRazonSocial() {
    return razonSocial;
  }

  public List<Sector> getSectores(){
    return this.sectores;
  }

  public List<Miembro> getMiembros(){
    return  this.miembros;
  }

  //////////////////////////////////  SETTERS
  public void setTipo(TipoOrganizacion tipo) {
    this.tipo = tipo;
  }

  public void setClasificacion(ClasificacionOrganizacion clasificacion) {
    this.clasificacion = clasificacion;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  //////////////////////////////////  INTERFACE

  public void registrarSector(Sector sector){
    this.sectores.add(sector);
  }

  public Boolean aceptarVinculacion(Miembro miembro){
    return true;
  }



}
