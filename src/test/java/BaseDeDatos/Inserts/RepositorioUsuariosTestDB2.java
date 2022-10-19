package BaseDeDatos.Inserts;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Usuario;

public class RepositorioUsuariosTestDB2 {
  public static void main(String[] args) {

    //Usuario user = new Usuario("insertUsuario","insertUsuario@gmail.com","insertUsuario54+",true);


    RepositorioUsuariosDB repositorioUsuarios = new RepositorioUsuariosDB();

    repositorioUsuarios.crearUsuario("insertUsuario","insertUsuario@gmail.com","insertUsuario54+",false);

  }
}