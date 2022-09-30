package Domain.Repositorios;

import Domain.Organizacion.Organizacion;

public class RepositorioOrganizacionesDB extends Repositorio<Organizacion>{

  public RepositorioOrganizacionesDB() {
    super(new DBHibernate<Organizacion>(Organizacion.class));
  }
}
