package Domain.Organizacion;
import Domain.CalculadorHC.CalculadorHC;
import Domain.Miembro.Miembro;
import Domain.ServicioMedicion.Actividad;
import Domain.ServicioMedicion.ServicioHCExcel;
import Domain.ServicioMedicion.ServicioMediciones;
import Domain.Usuarios.Contacto;

import java.text.ParseException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="organizacion")
public class Organizacion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_organizacion;
  @Column
  private String razonSocial;
  @Enumerated(EnumType.STRING)
  private TipoOrganizacion tipo;
  @Enumerated(EnumType.STRING)
  private ClasificacionOrganizacion clasificacion;

  @ManyToOne
  @JoinColumn(name="id_agente",referencedColumnName = "id_agente")
  private AgenteSectorial agenteSectorial;

  @OneToMany(mappedBy = "organizacion")
  private List<Sector> sectores = new ArrayList<>();

  /// DEFINIR ESTO!!!
  @OneToOne
  @JoinColumn(name="id_contacto",referencedColumnName = "id_contacto")
  private Contacto contacto;

  @Transient
  private String archivoMediciones;

  @Transient /// PERO A CHEQUEAR
  private ArrayList<ServicioHCExcel> reportes = new ArrayList<>();
  @Transient /// PERO A CHEQUEAR
  private ArrayList<Actividad> actividades = new ArrayList<>();

  @Transient
  private ServicioMediciones servicioMediciones;
  @Transient
  private CalculadorHC calculadorHC = CalculadorHC.getInstance();
  @Transient
  private Integer numDiasPorSemana;


  //////////////////////////////////  CONSTRUCTORES
  public Organizacion(){

  }

  public Organizacion(String _razonSocial, TipoOrganizacion _tipo, ClasificacionOrganizacion _clasificacion, Contacto contacto, Integer numDiasPorSemana){
    this.razonSocial = _razonSocial;
    this.tipo = _tipo;
    this.clasificacion = _clasificacion;
    this.contacto = contacto;
    this.numDiasPorSemana = numDiasPorSemana;
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

  public List<Sector> getSectores(){
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

  public ArrayList<ServicioHCExcel> getReportes() { return reportes; }

  public Integer getNumDiasPorSemana() {
    return numDiasPorSemana;
  }

  public ArrayList<Actividad> getActividades() {
    return actividades;
  }

  //////////////////////////////////  SETTERS

  public void setActividades(ArrayList<Actividad> actividades) {
    this.actividades = actividades;
  }

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

  public ArrayList<Actividad> cargarMedicionesInternas(String fileName) throws IOException, ParseException {
    return servicioMediciones.cargarMediciones(fileName);
  }

  // TODO Revisar resultado por unidades y ver el calculo completo
  public Double calcularHC() throws IOException {
    return calculadorHC.calcularHC(this);
  }

  public Double calcularHCAA() throws IOException {
    return calculadorHC.calcularHCAA(this);
  }

  public Double calcularHCMesAnio(int mes, int anio) throws IOException {
    return calculadorHC.calcularHCMesAnio(this, mes, anio);
  }

  public void darDeBajaActividad(Actividad actividad) {
    actividad.darDeBaja();
  }

}
