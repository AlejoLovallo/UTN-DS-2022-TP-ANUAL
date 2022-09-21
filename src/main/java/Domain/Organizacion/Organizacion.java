package Domain.Organizacion;
import Domain.CalculadorHC.CalculadorHC;
import Domain.Miembro.Miembro;
import Domain.ServicioMedicion.Actividad;
import Domain.ServicioMedicion.ServicioMediciones;
import Domain.Usuarios.Contacto;

import java.io.IOException;
import java.util.*;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class Organizacion {
  private String razonSocial;
  private TipoOrganizacion tipo;
  private ClasificacionOrganizacion clasificacion;
  private ArrayList<Sector> sectores= new ArrayList<>();
  private Contacto contacto;
  private AgenteSectorial agenteSectorial;
  private ServicioMediciones servicioMediciones;
  private String archivoMediciones;
  private ArrayList<Actividad> actividades;

  private CalculadorHC calculadorHC = CalculadorHC.getInstance();
  private Integer numDiasPorSemana;


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

  public Integer getNumDiasPorSemana() {
    return numDiasPorSemana;
  }

  public ArrayList <Actividad> getActividades() {
    return actividades;
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

  public void setSectores(ArrayList<Sector> sectores) {
    this.sectores = sectores;
  }

  public void setContacto(Contacto contacto) {
    this.contacto = contacto;
  }
  public void setAgenteSectorial(AgenteSectorial agenteSectorial) {
    this.agenteSectorial = agenteSectorial;
  }

  public void setServicioMediciones(ServicioMediciones servicioMediciones) {this.servicioMediciones = servicioMediciones;}

  public void setArchivoMediciones(String archivoMediciones) { this.archivoMediciones = archivoMediciones; }

  public void setNumDiasPorSemana(Integer numDiasPorSemana) {
    this.numDiasPorSemana = numDiasPorSemana;
  }

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

  public void cargarMedicionesInternas(String fileName) throws IOException {
    ArrayList <Actividad> act = servicioMediciones.cargarMediciones(fileName, this);
    for (Actividad actividad : act){
      this.actividades.add(actividad);
    }
  }

  public Double calcularHC(Integer mes, Integer anio) throws IOException {
    return calculadorHC.calcularHC(this, mes, anio);
  }

}
