package Domain.Usuarios;
import Domain.CalculadorHC.FactorEmision;
import Domain.CalculadorHC.RepositorioFactores;

/*
+cambiarValoresdeFE()
-configurarParametrosGenerales()


+validarOrganizacion(Organizacion,boolean)
+darDeAltaMedioTransporte(MedioTransporte,boolean)
+validarUsuario(Usuario,boolean)
 */

public class Admin extends Usuario {
  //////////////////////////////////  VARIABLES
  private RepositorioFactores repoFactoresDeEmision;

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

  public boolean registrarFactorDeEmision(FactorEmision factor){
    repoFactoresDeEmision.agregarFactorDeEmision(factor);
    return true;
  }

  public boolean cambiarFactorDeEmision(FactorEmision factor, Double nuevoValor){
    FactorEmision _factor = this.repoFactoresDeEmision.getFactorDeEmision(factor);
    _factor.setNumero(nuevoValor);
    this.repoFactoresDeEmision.updateFactorDeEmision(_factor);
    return true;
  }

  //TODO faltan las validaciones de Organizacion y MedioTransporte

}
