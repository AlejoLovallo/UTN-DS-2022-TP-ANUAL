package Domain.Repositorios;

import Domain.Miembro.Persona;
import Domain.Organizacion.Sector;

public class RepositorioSectoresDB extends  Repositorio<Sector> {

  public RepositorioSectoresDB() {
    super(new DBHibernate<Sector>(Sector.class));
  }
}
