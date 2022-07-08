package Domain.Organizacion;
import Domain.CalculadorHC.CalculadorHC;
import Domain.Usuarios.Excepciones.UsuarioException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class AgenteSectorial {

  private String nombre;
  private String territorio;
  private TipoSectorTerritorial tipoSectorTerritorial;
  private ArrayList<Organizacion> organizaciones;
  private CalculadorHC calculadorHC;

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

  public ArrayList<Organizacion> getOrganizaciones() {
    return organizaciones;
  }

  //TODO revisar resultado, por el tema de Unidades
  public Double calcularHC() throws IOException {
    return calculadorHC.calcularHC(this);
  }



}
