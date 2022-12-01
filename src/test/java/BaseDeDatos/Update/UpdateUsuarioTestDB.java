package BaseDeDatos.Update;

import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Usuario;


public class UpdateUsuarioTestDB {
  public static void main(String[] args) {

    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

    Usuario user = repositorioUsuariosDB.buscar(5);
    user.setUsername("tomas");

    repositorioUsuariosDB.modificar(user);


  }
}