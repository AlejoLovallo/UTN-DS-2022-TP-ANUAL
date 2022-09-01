package Domain.Miembro;
import Domain.CalculadorHC.CalculadorHC;
import Domain.Miembro.Excepciones.MiembroNoPerteneceAOrganizacionException;
import Domain.Miembro.Excepciones.UnicoSectorPorOrganizacionException;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Trayecto.Trayecto;

import java.io.IOException;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class Miembro {
  private String id;
  private Sector sector;
  private ArrayList<Trayecto> trayectos;

  private CalculadorHC calculadorHC = CalculadorHC.getInstance();


  //////////////////////////////////  CONSTRUCTOR
  public Miembro(String _id, Sector _sector){
    this.id = _id;
    this.sector = _sector;
  }

  //////////////////////////////////  GETTERS

  public ArrayList<Trayecto> getTrayectos() {
    return trayectos;
  }

  public Sector getSector() { return sector; }
  //////////////////////////////////  SETTERS
  public void setTrayectos(ArrayList<Trayecto> trayectos) {
    this.trayectos = trayectos;
  }

  //////////////////////////////////  INTERFACE

  public void vincularSector(Sector _sector){
    if (this.sector != null)
      throw new UnicoSectorPorOrganizacionException();
    _sector.getOrganizacion().aceptarVinculacion(this, _sector);
    this.sector = _sector;
  }

  public void registrarTrayectos(Organizacion organizacion, ArrayList<Trayecto> trayectos){
    if (!this.sector.getOrganizacion().equals(organizacion))
       throw new MiembroNoPerteneceAOrganizacionException(organizacion.getRazonSocial());
    this.setTrayectos(trayectos);
  }

  //TODO hacer el calculo de un miembro
  public Double calcularHC(Integer mes, Integer anio) throws IOException {
    return calculadorHC.calcularHC(this, mes, anio);
  }
}
