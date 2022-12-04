package Domain.Organizacion;
import Domain.CalculadorHC.CalculadorHC;
import Domain.Reportes.GeneradorReportes;
import Domain.Reportes.Reporte;
import Domain.Reportes.TipoDeReporte;
import Domain.Usuarios.Excepciones.UsuarioException;
//import jdk.vm.ci.meta.Local;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="agentesectorial")
public class AgenteSectorial {
  //////////////////////////////////  VARIABLES
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_agente;

  @Column(name = "razonSocial")
  private String razonSocial;
  @Column
  private String territorio;

  @Column(name = "tipo")
  @Enumerated(EnumType.STRING)
  private TipoSectorTerritorial tipoSectorTerritorial;

  @OneToMany(mappedBy = "agenteSectorial", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Organizacion> organizaciones = new ArrayList<>();

  @OneToMany(mappedBy = "agenteSectorial", cascade = CascadeType.ALL)
  private List<Reporte> reportes;

  @Transient
  private GeneradorReportes generadorReportes = GeneradorReportes.getInstance();
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

  public List<Organizacion> getOrganizaciones() {
    return organizaciones;
  }

  public List <Reporte> getReportes (){
    return reportes;
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


  public Reporte conseguirReporte(TipoDeReporte tipoDeReporte, LocalDate fechaDesde, LocalDate fechaHasta) throws IOException{
    switch(tipoDeReporte){
      case TOTAL:
      return generadorReportes.reporteHC_SecTer(this, fechaDesde, fechaHasta);
      case COMPOSICION:
        return generadorReportes.reporteCompHC_SecTer(this, fechaDesde, fechaHasta);
      case EVOLUCION:
        return generadorReportes.reporteEvolucionHC_SecTer(this, fechaDesde, fechaHasta);
    }
    return null;
  }

}
