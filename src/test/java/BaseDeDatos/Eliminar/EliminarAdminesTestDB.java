package BaseDeDatos.Eliminar;


import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Admin;
import Domain.Usuarios.Usuario;

public class EliminarAdminesTestDB {

  public static void main(String[] args) {
    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
    repositorioUsuariosDB.crearAdmin("alfonsodiaz","mail_@yahoo.com","Ernesto2344");
    Usuario usuario = repositorioUsuariosDB.buscar(4);

    repositorioUsuariosDB.eliminar(usuario);

    System.out.println("---------------------" +  usuario.getClass());

  }
}
