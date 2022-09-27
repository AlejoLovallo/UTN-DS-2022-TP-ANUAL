package Domain.Organizacion;
import Domain.CalculadorHC.CalculadorHC;
import Domain.Usuarios.Excepciones.UsuarioException;
import jdk.vm.ci.meta.Local;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name="agentesectorial")
public class AgenteSectorial {
  //////////////////////////////////  VARIABLES
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_agente;
  @Column
  private String razonSocial;
  @Column
  private String territorio;
  @Enumerated(EnumType.STRING)
  private TipoSectorTerritorial tipoSectorTerritorial;

  @Transient
  private ArrayList<Organizacion> organizaciones = new ArrayList<>();
  @Transient
  private CalculadorHC calculadorHC = CalculadorHC.getInstance();

  //////////////////////////////////  CONSTRUCTORES
  public AgenteSectorial(){

  }


  public AgenteSectorial(String nombre, String territorio, TipoSectorTerritorial tipoSectorTerritorial) {
    this.razonSocial = nombre;
    this.territorio = territorio;
    this.tipoSectorTerritorial = tipoSectorTerritorial;
  }

  //////////////////////////////////  GETTERS
  public String getNombre() {
    return razonSocial;
  }

  public String getTerritorio() {
    return territorio;
  }

  public TipoSectorTerritorial getTipoSectorTerritorial() {
    return tipoSectorTerritorial;
  }

  public ArrayList<Organizacion> getOrganizaciones() {
    return organizaciones;
  }

  //////////////////////////////////  SETTERS
  public void setNombre(String nombre) {
    this.razonSocial = nombre;
  }

  public void setTerritorio(String territorio) {
    this.territorio = territorio;
  }

  public void setTipoSectorTerritorial(TipoSectorTerritorial tipoSectorTerritorial) {
    this.tipoSectorTerritorial = tipoSectorTerritorial;
  }

  //////////////////////////////////  INTERFACE
  public void agregarOrganizacion(Organizacion organizacion){
    this.organizaciones.add(organizacion);
  }


  //TODO revisar resultado, por el tema de Unidades
  public Double calcularHC(Integer mes, Integer anio) throws IOException {
    return calculadorHC.calcularHC(this, mes, anio);
  }

  public LocalDate getFechaMasTemprana(){
    LocalDate fechaMasTemprana = this.getOrganizaciones().get(0).getFechaIngreso();
    for(Organizacion organizacion : this.getOrganizaciones())
      if(LocalDate.MIN.isAfter(organizacion.getFechaIngreso()))
        fechaMasTemprana = organizacion.getFechaIngreso();

    return fechaMasTemprana;
  }

}
