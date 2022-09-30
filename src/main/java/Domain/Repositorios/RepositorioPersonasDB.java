package Domain.Repositorios;

import Domain.Miembro.Persona;
import Domain.Usuarios.Usuario;

public class RepositorioPersonasDB extends Repositorio<Persona>{

  public RepositorioPersonasDB() {
    super(new DBHibernate<Persona>(Persona.class));
  }
}
