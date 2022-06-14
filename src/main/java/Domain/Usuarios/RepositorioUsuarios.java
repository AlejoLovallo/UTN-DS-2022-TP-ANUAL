package Domain.Usuarios;
/*
-------Variables-------

-usuarios: Usuaurios--------
-instance: RepositorioUsuarios----------

---------Metodos--------
+iniciarSesion(String, String)----------
+crearUsuario(String,String,String)
-validarUsuario()
-RepositorioUsuarios()---------------
+getInstance()-----------
*/


import Domain.Usuarios.Excepciones.ContraseniaEsInvalidaException;
import Domain.Usuarios.Excepciones.UsuarioException;

import java.util.ArrayList;

public class RepositorioUsuarios {
  //////////////////////////////////  VARIABLES
  private static RepositorioUsuarios instance = null;
  private ArrayList<Usuario> usuarios;


  //////////////////////////////////  CONSTRUCTORES
  private RepositorioUsuarios(){
    //todo traer todos los usuarios de la DB
    this.usuarios = new ArrayList<>();
  }

  public static RepositorioUsuarios getInstance(){
    if(instance == null){
      instance = new RepositorioUsuarios();
    }
    return instance;
  }

  //////////////////////////////////  GETTERS
  public ArrayList<Usuario> getUsuarios() {
    return usuarios;
  }

  //////////////////////////////////  SETTERS

  //////////////////////////////////  INTERFACE
  public boolean validarUsuario(Usuario usuario, Boolean validacion){
    Usuario usuarioAValidar = this.usuarios.stream().filter(u -> u.equals(usuario)).findFirst().orElse(null);
    if(usuarioAValidar == null){
      return false;
    }
    usuarioAValidar.setValidado(validacion);
    return true;
  }

  public boolean validarUsuario(String username, Boolean validacion){
    Usuario usuarioAValidar = this.usuarios.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    if(usuarioAValidar == null){
      return false;
    }
    usuarioAValidar.setValidado(validacion);
    return true;
  }

  public Usuario iniciarSesion(String username, String contra){
    Usuario usuarioConUsername = this.usuarios.stream().filter(u-> u.getUsername().equals(username)).findFirst().orElse(null);
    //Si no encuentra el usuario por username
    if(usuarioConUsername == null){
      return null;
    }
    //Si el usuario esta validado
    if(!usuarioConUsername.isValido()){
      return null;
    }
    //Si el intento de contrasenia es fallido se devolvera nulo
    // lo pongo en los dos lados por si la contrasenia es correcta pero no paso el tiempo del ultimo acceso
    if(usuarioConUsername.isColision(contra)){
      if(usuarioConUsername.intentoAcceso()){
        return usuarioConUsername;
      }
      throw new ContraseniaEsInvalidaException("no paso el tiempo de inicio de sesion");
    }
    //Y esto esta por la contrasenia es incorrecta se actualizara el intento de acceso
    usuarioConUsername.intentoAcceso();
    throw new ContraseniaEsInvalidaException("la contrasenia no es la misma a la del usuario");
  }

  public Usuario crearUsuario(String username, String email, String contra, boolean validado){
    try{
      Usuario usuarioNuevo = new Usuario(username,email,contra,validado);
      this.usuarios.add(usuarioNuevo);
      return usuarioNuevo;
    }catch (UsuarioException e){
      return null;
    }
  }

  public Admin crearAdmin(String username, String email, String contra){
    try{
      Admin adminNuevo = new Admin(username,email,contra,true);
      this.usuarios.add(adminNuevo);
      return adminNuevo;
    }catch (UsuarioException e){
      return null;
    }
  }


}
