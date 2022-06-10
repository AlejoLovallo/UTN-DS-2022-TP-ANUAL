package Domain.Miembro;
import Domain.Miembro.Excepciones.MiembroNoPerteneceAOrganizacionException;
import Domain.Miembro.Excepciones.UnicoSectorPorOrganizacionException;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Trayecto.Trayecto;

import java.util.ArrayList;
import java.util.HashMap;

public class Miembro {
  private String id;
  private Organizacion organizacion;
  private Sector sector;
  private ArrayList<Trayecto> trayectos;


  //////////////////////////////////  CONSTRUCTOR
  public Miembro(String _id, Organizacion _organizacion, Sector _sector){
    this.id = _id;
    this.organizacion = _organizacion;
    this.sector = _sector;
  }

  //////////////////////////////////  GETTERS

  public ArrayList<Trayecto> getTrayectos() {
    return trayectos;
  }

  public Organizacion getOrganizacion(){
    return  this.organizacion;
  }

  //////////////////////////////////  SETTERS

  public void setTrayectos(ArrayList<Trayecto> trayectos) {
    this.trayectos = trayectos;
  }

  //////////////////////////////////  INTERFACE

  public void vincularSector(Sector _sector){
    if (this.sector != null)
      throw new UnicoSectorPorOrganizacionException();
    organizacion.aceptarVinculacion(this);
    this.sector = _sector;
  }

  public void registrarTrayecto(Organizacion organizacion, Trayecto trayecto){
    assert this.getOrganizacion()
    trayectos.add(trayecto);
  }

}
