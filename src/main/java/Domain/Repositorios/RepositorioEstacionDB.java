package Domain.Repositorios;

import Domain.Espacios.Estacion;

public class RepositorioEstacionDB extends Repositorio<Estacion>{
  public RepositorioEstacionDB() {
    super(new DBHibernate<Estacion>(Estacion.class));
  }
}
