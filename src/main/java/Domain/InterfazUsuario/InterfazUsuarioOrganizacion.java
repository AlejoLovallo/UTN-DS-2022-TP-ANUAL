package Domain.InterfazUsuario;

import Domain.Organizacion.Organizacion;
import Domain.Usuarios.Usuario;

public class InterfazUsuarioOrganizacion {
  private InterfazUsuarioOrganizacion instance = null;
  private Usuario usuario;
  private Organizacion organizacion;

  public InterfazUsuarioOrganizacion getInstance(){
    if (instance == null){
      instance =  new InterfazUsuarioOrganizacion();
    }
    return instance;
  }


  public Boolean ingresarUsuario(){
    return true;
  }

  public Boolean crearUsuario(String _nombre, String _password){
    return true;
  }

  public void devolverUsuarioIncorrecto(){

  }

  public void devolverUsuarioCorrecto(){

  }
}
