package Domain.Usuarios;

/*
+cambiarValoresdeFE()
-configurarParametrosGenerales()


+validarOrganizacion(Organizacion,boolean)
+darDeAltaMedioTransporte(MedioTransporte,boolean)
+validarUsuario(Usuario,boolean)
 */


public class Admin extends Usuario {
  //////////////////////////////////  VARIABLES

  //////////////////////////////////  CONSTRUCTORES
  public Admin(String username, String email, String contra, boolean validado) {
    super(username, email, contra, validado);
  }


  //////////////////////////////////  GETTERS


  //////////////////////////////////  SETTERS


  //////////////////////////////////  INTERFACE
  public boolean validarUsuario(Usuario userDeAlta, boolean validacion){
    return RepositorioUsuarios.getInstance().validarUsuario(userDeAlta,validacion);
  }

  public boolean validarUsuario(String username, boolean validacion){
    return RepositorioUsuarios.getInstance().validarUsuario(username,validacion);
  }

  //TODO faltan las validaciones de Organizacion y MedioTransporte

}
