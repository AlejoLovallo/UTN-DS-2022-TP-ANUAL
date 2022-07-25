package Domain.Organizacion;
import java.util.ArrayList;

/**
 * Singleton pattern
 */

public class RepositorioAgentes {
  private static RepositorioAgentes instance = null;

  private ArrayList<AgenteSectorial> agenteSectoriales = new ArrayList<>();

  private RepositorioAgentes(){}

  public static RepositorioAgentes GetInstance(){
    if(instance == null){
      instance = new RepositorioAgentes();
    }
    return instance;
  }


  public AgenteSectorial crearAgente(String nombre, String territorio, TipoSectorTerritorial tipoSectorTerritorial){
    AgenteSectorial agenteNuevo = new AgenteSectorial(nombre,territorio,tipoSectorTerritorial);
    this.agenteSectoriales.add(agenteNuevo);
    return agenteNuevo;
  }

  public AgenteSectorial buscarAgente(String nombre){
    return this.agenteSectoriales.stream().filter(a-> a.getNombre().equals(nombre)).findFirst().orElse(null);
  }

  public AgenteSectorial agregarOrganizacionAAgente(String nombre , Organizacion organizacion){
    AgenteSectorial aAsociar = buscarAgente(nombre);
    if(aAsociar!=null){
      aAsociar.agregarOrganizacion(organizacion);
      return aAsociar;
    }
    return null;
  }


}
