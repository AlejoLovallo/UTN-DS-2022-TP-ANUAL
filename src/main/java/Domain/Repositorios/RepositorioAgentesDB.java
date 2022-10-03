package Domain.Repositorios;

import Domain.Organizacion.AgenteSectorial;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.TipoSectorTerritorial;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioAgentesDB extends Repositorio<AgenteSectorial>{

  public RepositorioAgentesDB() {
    super(new DBHibernate<AgenteSectorial>(AgenteSectorial.class));
  }



  public AgenteSectorial crearAgente(String nombre, String territorio, TipoSectorTerritorial tipoSectorTerritorial){
    AgenteSectorial agenteNuevo = new AgenteSectorial(nombre,territorio,tipoSectorTerritorial);
    this.dbService.agregar(agenteNuevo);
    return agenteNuevo;
  }

  public AgenteSectorial buscarAgente(String razonSocial){
    return this.dbService.buscar(condicionAgentePorRazonSocial(razonSocial));
  }

  public AgenteSectorial agregarOrganizacionAAgente(AgenteSectorial agenteSectorial , Organizacion organizacion){
    //AgenteSectorial aAsociar = buscarAgente(nombre);
    if(agenteSectorial!=null){
      agenteSectorial.agregarOrganizacion(organizacion);
      organizacion.setAgenteSectorial(agenteSectorial);

      this.dbService.modificar(agenteSectorial);

      return agenteSectorial;
    }
    return null;
  }


  private BusquedaCondicional condicionAgentePorRazonSocial(String razonSocial){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<AgenteSectorial> agenteSectorialCriteriaQuery = criteriaBuilder.createQuery(AgenteSectorial.class);

    Root<AgenteSectorial> root = agenteSectorialCriteriaQuery.from(AgenteSectorial.class);

    Predicate condicionnPorNombre = criteriaBuilder.like(root.get("razonSocial"), "%"+ razonSocial +"%");

    agenteSectorialCriteriaQuery.where(condicionnPorNombre);

    return new BusquedaCondicional(null, agenteSectorialCriteriaQuery);
  }
}
