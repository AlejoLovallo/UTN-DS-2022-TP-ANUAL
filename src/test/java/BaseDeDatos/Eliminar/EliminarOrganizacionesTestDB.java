package BaseDeDatos.Eliminar;

import Domain.Organizacion.Organizacion;
import Domain.Repositorios.RepositorioOrganizacionesDB;

public class EliminarOrganizacionesTestDB {

  public static void main(String[] args) {
    RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();

    Organizacion organizacion = repositorioOrganizacionesDB.buscar(5);

    repositorioOrganizacionesDB.eliminar(organizacion);

    System.out.println("---------------------");

  }
}
