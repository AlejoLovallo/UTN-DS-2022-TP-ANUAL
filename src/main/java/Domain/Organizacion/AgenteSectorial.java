package Domain.Organizacion;
import Domain.Usuarios.Excepciones.UsuarioException;

import java.util.Set;

public class AgenteSectorial {

  private String nombre;
  private String territorio;
  private TipoSectorTerritorial tipoSectorTerritorial;
  private Set<Organizacion> organizaciones;

  public AgenteSectorial(String nombre, String territorio, TipoSectorTerritorial tipoSectorTerritorial) {
    this.nombre = nombre;
    this.territorio = territorio;
    this.tipoSectorTerritorial = tipoSectorTerritorial;
  }

  public void agregarOrganizaccion(Organizacion organizacion){
    this.organizaciones.add(organizacion);
  }

  public String getNombre() {
    return nombre;
  }

  public String getTerritorio() {
    return territorio;
  }

  public TipoSectorTerritorial getTipoSectorTerritorial() {
    return tipoSectorTerritorial;
  }

  //TODO revisar resultado, por el tema de Unidades
  public int calculoHCTotalOrganizaciones(){
    int total = 0 ;
    //sumatoria de todos las HC de cada Organizacion
    for(Organizacion organizacion : this.organizaciones){
      total += organizacion.calculoHCTotalOrganizacion();
    }
    return total;
  }



}
