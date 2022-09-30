package BaseDeDatos.Eliminar;

import Domain.Organizacion.Sector;
import Domain.Repositorios.RepositorioSectoresDB;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Usuario;

public class EliminarUsuariosTestDB {

  public static void main(String[] args) {
    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

    Usuario usuario = repositorioUsuariosDB.buscar(5);

    repositorioUsuariosDB.eliminar(usuario);

    System.out.println("---------------------");

  }
}
