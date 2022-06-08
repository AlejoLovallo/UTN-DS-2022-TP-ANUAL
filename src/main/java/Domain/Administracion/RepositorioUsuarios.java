package Domain.Administracion;

import java.util.ArrayList;

public class RepositorioUsuarios {
  private static RepositorioUsuarios instance = null;
  private ArrayList<Usuario> usuarios;

  public static RepositorioUsuarios GetInstance(){
    if (instance == null){
      instance = new RepositorioUsuarios();
    }
    return instance;
  }

  public void iniciarSesion(String mail, String contrasenia){

  }

  public void crearUsuario(String nombre, String mail, String contrasenia){
    Usuario usuario = new Usuario(nombre,mail,contrasenia);
    usuarios.add(usuario);
  }

}
