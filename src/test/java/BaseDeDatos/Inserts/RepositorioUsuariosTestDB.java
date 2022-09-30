package BaseDeDatos.Inserts;
import Domain.Repositorios.DBHibernate;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Admin;
import Domain.Usuarios.Usuario;

public class RepositorioUsuariosTestDB {
  public static void main(String[] args) {

    Usuario user = new Usuario("insertUsuario","insertUsuario@gmail.com","insertUsuario54+",true);

    //Miembro miembro = new Miembro();

    RepositorioUsuariosDB repositorioUsuarios = new RepositorioUsuariosDB();
    repositorioUsuarios.agregar(user);

  }
}