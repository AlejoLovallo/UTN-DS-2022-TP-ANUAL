package Domain.Organizacion;
import Domain.Usuarios.Excepciones.UsuarioException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AgenteSectorial {
  //////////////////////////////////  VARIABLES
  private String nombre;
  private String territorio;
  private TipoSectorTerritorial tipoSectorTerritorial;
  private ArrayList<Organizacion> organizaciones = new ArrayList<>();

  //////////////////////////////////  CONSTRUCTORES
  public AgenteSectorial(String nombre, String territorio, TipoSectorTerritorial tipoSectorTerritorial) {
    this.nombre = nombre;
    this.territorio = territorio;
    this.tipoSectorTerritorial = tipoSectorTerritorial;
  }

  //////////////////////////////////  GETTERS
  public String getNombre() {
    return nombre;
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
    this.nombre = nombre;
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
  public int calculoHCTotalOrganizaciones(){
    int total = 0 ;
    //sumatoria de todos las HC de cada Organizacion
    for(Organizacion organizacion : this.organizaciones){
      total += organizacion.calculoHCTotalOrganizacion();
    }
    return total;
  }



}
