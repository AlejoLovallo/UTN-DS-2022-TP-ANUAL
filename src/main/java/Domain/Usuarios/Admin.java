package Domain.Usuarios;

/*
+cambiarValoresdeFE()
-configurarParametrosGenerales()


+validarOrganizacion(Organizacion,boolean)
+darDeAltaMedioTransporte(MedioTransporte,boolean)
+validarUsuario(Usuario,boolean)
 */


import Domain.CalculoHC.FactorEmision;

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

  public void cambiarFactorCombustionFija(int emisionCombustionFija){
    FactorEmision.getInstance().setEmisionCombustionFija(emisionCombustionFija);
  }

  public void cambiarFactorCombustionMovil(int emisionCombustionMovil){
    FactorEmision.getInstance().setEmisionCombustionMovil(emisionCombustionMovil);
  }
  public void cambiarFactorElectricidad(int emisionElectricidad){
    FactorEmision.getInstance().setEmisionElectricidad(emisionElectricidad);
  }
  public void cambiarFactorLogistica(int emisionLogistica){
    FactorEmision.getInstance().setEmisionLogistica(emisionLogistica);
  }

  public void cambiarFactoresEmision(int emisionCombustionFija, int emisionCombustionMovil, int emisionElectricidad, int emisionLogistica){
    FactorEmision.getInstance().setEmisionCombustionFija(emisionCombustionFija);
    FactorEmision.getInstance().setEmisionCombustionMovil(emisionCombustionMovil);
    FactorEmision.getInstance().setEmisionElectricidad(emisionElectricidad);
    FactorEmision.getInstance().setEmisionLogistica(emisionLogistica);
  }

  //TODO faltan las validaciones de Organizacion y MedioTransporte

}
