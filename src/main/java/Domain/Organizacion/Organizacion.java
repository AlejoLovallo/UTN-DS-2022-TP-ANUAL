package Domain.Organizacion;
import Domain.Miembro.Miembro;

import java.util.List;
import Domain.DatosActividades.LeerExcel;

public class Organizacion {
  private String razonSocial;
  private TipoOrganizacion tipo;
  private ClasificacionOrganizacion clasificacion;
  private List<Sector>  sectores;
  private List<Miembro> miembros;

  public static void main(String[] args) {
    LeerExcel l = new LeerExcel();
    l.cargarExcel("C:\\Users\\admin\\Desktop\\UTN\\DDS\\ej2.csv");
    System.out.print(l.getActividad() + "\n");
    System.out.print(l.getPeriodicidad() + "\n");
    System.out.print(l.getTipoConsumo() + "\n");
    System.out.print(l.getPeriodoDeImputacion().toString());
  }

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
