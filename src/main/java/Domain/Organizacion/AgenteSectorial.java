package Domain.Organizacion;
import Domain.CalculadorHC.CalculadorHC;
import Domain.Usuarios.Excepciones.UsuarioException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class AgenteSectorial {
  //////////////////////////////////  VARIABLES
  private String nombre;
  private String territorio;
  private TipoSectorTerritorial tipoSectorTerritorial;
  private ArrayList<Organizacion> organizaciones = new ArrayList<>();
  private CalculadorHC calculadorHC;

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
  public Double calcularHC() throws IOException {
    return calculadorHC.calcularHC(this);
  }



}
