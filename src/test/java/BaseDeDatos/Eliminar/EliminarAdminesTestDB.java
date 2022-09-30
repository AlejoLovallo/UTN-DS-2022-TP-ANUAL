package BaseDeDatos.Eliminar;


import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Admin;
import Domain.Usuarios.Usuario;

public class EliminarAdminesTestDB {

  public static void main(String[] args) {
    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

    Usuario usuario = repositorioUsuariosDB.buscar(23);

    repositorioUsuariosDB.eliminar(usuario);

    System.out.println("---------------------" +  usuario.getClass());

  }
}
