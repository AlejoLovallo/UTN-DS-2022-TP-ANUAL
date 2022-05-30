package Domain.Miembro;
import Domain.Miembro.Excepciones.MiembroNoPerteneceAOrganizacionException;
import Domain.Miembro.Excepciones.UnicoSectorPorOrganizacionException;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Trayecto.Trayecto;

import java.util.ArrayList;
import java.util.HashMap;

public class Miembro {
  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private String documento;
  private ArrayList<Trayecto> trayectos;
  private HashMap<Organizacion, Sector> sectorPorOrganizacion;

  //////////////////////////////////  CONSTRUCTOR
  public Miembro(){

  }

  //////////////////////////////////  GETTERS
  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public TipoDocumento getTipoDocumento() {
    return tipoDocumento;
  }

  public String getDocumento() {
    return documento;
  }

  public ArrayList<Trayecto> getTrayectos() {
    return trayectos;
  }

  public HashMap<Organizacion, Sector> getSectorPorOrganizacion() {
    return sectorPorOrganizacion;
  }

  //////////////////////////////////  SETTERS
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setTipoDocumento(TipoDocumento tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public void setTrayectos(ArrayList<Trayecto> trayectos) {
    this.trayectos = trayectos;
  }

  //////////////////////////////////  INTERFACE

  public void vincularseAOrganizacion(Organizacion organizacion, Sector sector){
    if (this.miembroPerteneceAOrganizacion(organizacion))
      throw new UnicoSectorPorOrganizacionException();
    organizacion.aceptarVinculacion(this);
    sectorPorOrganizacion.put(organizacion,sector);
  }

  public void registrarTrayecto(Organizacion organizacion, Trayecto trayecto){
    if (!this.miembroPerteneceAOrganizacion(organizacion))
      throw new MiembroNoPerteneceAOrganizacionException(organizacion.getRazonSocial());
    trayectos.add(trayecto);
  }

  public Boolean miembroPerteneceAOrganizacion(Organizacion organizacion){
    return sectorPorOrganizacion.containsKey(organizacion);
  }

}
