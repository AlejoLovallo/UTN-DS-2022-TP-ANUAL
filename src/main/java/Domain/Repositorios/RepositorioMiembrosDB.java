package Domain.Repositorios;

import Domain.Miembro.Miembro;

public class RepositorioMiembrosDB extends Repositorio{

    public RepositorioMiembrosDB() {
        super(new DBHibernate<Miembro>(Miembro.class));
    }
}
