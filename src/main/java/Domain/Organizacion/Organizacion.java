package Domain.Organizacion;
import Domain.CalculadorHC.CalculadorHC;
import Domain.Miembro.Miembro;
import Domain.Reportes.GeneradorReportes;
import Domain.Reportes.Reporte;
import Domain.Reportes.TipoDeReporte;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.ServicioMedicion.ServicioExcel;
import Domain.ServicioMedicion.ServicioMediciones;
import Domain.Usuarios.Contacto;
import Domain.Usuarios.Usuario;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDate;
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

  @ManyToOne(cascade = CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name="id_Agente",referencedColumnName = "id_agente")
  private AgenteSectorial agenteSectorial;

  @OneToMany(mappedBy = "organizacion",cascade = CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  private List<Sector> sectores = new ArrayList<>();

  @OneToOne(cascade = CascadeType.ALL)
  //@NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name="id_contacto",referencedColumnName = "id_contacto")
  private Contacto contacto;

  @Transient
  private String archivoMediciones;

  //@Transient /// PERO A CHEQUEAR
  @OneToMany(mappedBy = "organizacion",cascade = CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  private List<Actividad> actividades = new ArrayList<>();

  //@Transient
  @OneToMany(mappedBy = "organizacion",cascade = CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  private List<Reporte> reportes = new ArrayList<>();

  @OneToOne(cascade = CascadeType.DETACH)
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name="id_usuario",referencedColumnName = "id_usuario")
  private Usuario usuario;

  @Transient
  //TODO
  private LocalDate fechaIngreso;

  @Column(name = "pais")
  private String paisOrigen;

  @Transient
  private ServicioMediciones servicioMediciones;
  @Transient
  private CalculadorHC calculadorHC = CalculadorHC.getInstance();

  @Column(name = "numdiasxsemana")
  private Integer numDiasPorSemana;


  @Transient
  private GeneradorReportes generadorReportes = GeneradorReportes.getInstance();


  //////////////////////////////////  CONSTRUCTORES
  private Organizacion(){

  }

  public Organizacion(String _razonSocial, TipoOrganizacion _tipo, ClasificacionOrganizacion _clasificacion, Contacto contacto, Integer _numDiasSemana){
    this.razonSocial = _razonSocial;
    this.tipo = _tipo;
    this.clasificacion = _clasificacion;
    this.contacto = contacto;
    this.numDiasPorSemana = _numDiasSemana;
    /*if(contacto != null)
      contacto.setOrganizacion(this);*/
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
  public Integer getNumDiasPorSemana() {
    return numDiasPorSemana;
  }

  public ServicioMediciones getServicioMediciones() { return servicioMediciones;  }

  public String getArchivoMediciones() { return archivoMediciones; }

  public int getId() {
    return id_organizacion;
  }

  public List <Actividad> getActividades() {
    return actividades;
  }

  public LocalDate getFechaIngreso() { return fechaIngreso; }

  public String getPaisOrigen() { return paisOrigen; }

  public List<Reporte> getReportes() {
    return reportes;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
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

  public void setNumDiasPorSemana(Integer numDiasPorSemana) { this.numDiasPorSemana = numDiasPorSemana; }

  public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

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
    if(servicioMediciones == null)
      servicioMediciones = ServicioExcel.getInstance();

    ArrayList <Actividad> act = servicioMediciones.cargarMediciones(fileName, this);
    for (Actividad actividad : act){
      this.actividades.add(actividad);
    }

    RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
    repositorioOrganizacionesDB.modificar(this);
  }

  public Double calcularHC(Integer mes, Integer anio) throws IOException {
    return calculadorHC.calcularHC(this, mes, anio);
  }

  public Reporte conseguirReporte(TipoDeReporte tipoDeReporte, LocalDate fechaDesde, LocalDate fechaHasta) throws IOException{
    switch(tipoDeReporte){
      case COMPOSICION:
        return generadorReportes.reporteCompHC_Org(this, fechaDesde, fechaHasta);
      case EVOLUCION:
        return generadorReportes.reporteEvolucionHC_Org(this, fechaDesde, fechaHasta);
    }
    return null;
  }


}
