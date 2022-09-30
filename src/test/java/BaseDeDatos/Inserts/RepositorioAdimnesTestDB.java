package BaseDeDatos.Inserts;

import Domain.Miembro.Persona;
import Domain.Miembro.TipoDocumento;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Admin;
import Domain.Usuarios.Usuario;

public class RepositorioAdimnesTestDB {
  public static void main(String[] args) {

    Usuario user = new Admin("insertUsuario","insertUsuario@gmail.com","insertUsuario54+",true);

    //Miembro miembro = new Miembro();

    RepositorioUsuariosDB repositorioUsuarios = new RepositorioUsuariosDB();
    repositorioUsuarios.agregar(user);

  }
}
