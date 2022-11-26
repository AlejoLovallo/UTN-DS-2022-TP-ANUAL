package Domain.Usuarios;
import Domain.CalculadorHC.FactorEmision;
import Domain.Repositorios.RepositorioFactoresEmisionDB;
import Domain.Repositorios.RepositorioUsuariosDB;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

/*
+cambiarValoresdeFE()
-configurarParametrosGenerales()


+validarOrganizacion(Organizacion,boolean)
+darDeAltaMedioTransporte(MedioTransporte,boolean)
+validarUsuario(Usuario,boolean)
 */

/*
@Entity
@Table(name="usuario")*/
@Entity
@PrimaryKeyJoinColumn(name="id_usuario")
public class Admin extends Usuario {
  //////////////////////////////////  VARIABLES
  @Transient
  private RepositorioFactoresEmisionDB repoFactoresDeEmision = new RepositorioFactoresEmisionDB();

  //////////////////////////////////  CONSTRUCTORES
  public Admin(String username, String email, String contra, boolean validado) {
    super(username, email, contra, validado);
    //super.setIsAdmin(true);
  }

  public Admin(){
    super();
  }






  //////////////////////////////////  GETTERS


  //////////////////////////////////  SETTERS


  //////////////////////////////////  INTERFACE
  public boolean validarUsuario(Usuario userDeAlta, boolean validacion){
    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

    return repositorioUsuariosDB.validarUsuario(userDeAlta,validacion);
  }

  public boolean validarUsuario(String username, boolean validacion){
    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

    return repositorioUsuariosDB.validarUsuario(username,validacion);
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
