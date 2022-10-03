package Domain.Repositorios;

import Domain.Organizacion.AgenteSectorial;

public class RepositorioAgentesDB extends Repositorio<AgenteSectorial>{

  public RepositorioAgentesDB() {
    super(new DBHibernate<AgenteSectorial>(AgenteSectorial.class));
  }
}
