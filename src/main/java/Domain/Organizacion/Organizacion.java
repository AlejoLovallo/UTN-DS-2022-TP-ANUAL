package Domain.Organizacion;
import Domain.CalculadorHC.CalculadorHC;
import Domain.Miembro.Miembro;
import Domain.Usuarios.Contacto;
import Domain.ServicioMedicion.*;

import java.util.ArrayList;
import java.io.IOException;
import java.util.*;

public class Organizacion {
  private String razonSocial;
  private TipoOrganizacion tipo;
  private ClasificacionOrganizacion clasificacion;
  private ArrayList<Sector> sectores= new ArrayList<>();
  private Contacto contacto;
  private AgenteSectorial agenteSectorial;
  private ServicioMediciones servicioMediciones;
  private String archivoMediciones;

  private CalculadorHC calculadorHC = CalculadorHC.getInstance();


  //////////////////////////////////  CONSTRUCTORES
  public Organizacion(String _razonSocial, TipoOrganizacion _tipo, ClasificacionOrganizacion _clasificacion, Contacto contacto){
    this.razonSocial = _razonSocial;
    this.tipo = _tipo;
    this.clasificacion = _clasificacion;
    this.contacto = contacto;
  }

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

  public ArrayList<Sector> getSectores(){
    return this.sectores;
  }

  public Contacto getContacto() {
    return this.contacto;
  }
  public AgenteSectorial getAgenteSectorial() {
    return agenteSectorial;
  }

  public ServicioMediciones getServicioMediciones() { return servicioMediciones;  }

  public String getArchivoMediciones() { return archivoMediciones; }

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

  public void setContacto(Contacto contacto) {
    this.contacto = contacto;
  }
  public void setAgenteSectorial(AgenteSectorial agenteSectorial) {
    this.agenteSectorial = agenteSectorial;
  }

  public void setServicioMediciones(ServicioMediciones servicioMediciones) {this.servicioMediciones = servicioMediciones;}

  public void setArchivoMediciones(String archivoMediciones) { this.archivoMediciones = archivoMediciones; }

  //////////////////////////////////  INTERFACE

  public void registrarSector(Sector sector){
    this.sectores.add(sector);
  }

  public Boolean aceptarVinculacion(Miembro miembro, Sector sector){
    if(this.sectores.contains(sector)){
      sector.getMiembros().add(miembro);
    }
    return true;
  }

  public ArrayList<Actividad> cargarMedicionesInternas(String fileName) throws IOException {
    return servicioMediciones.cargarMediciones(fileName);
  }

  // TODO Revisar resultado por unidades y ver el calculo completo
  public Double calcularHC() throws IOException {
    return calculadorHC.calcularHC(this);
  }
}
