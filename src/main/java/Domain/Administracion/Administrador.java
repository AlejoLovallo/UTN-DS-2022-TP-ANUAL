package Domain.Administracion;

import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.Organizacion.Organizacion;

public class Administrador extends Usuario {

  ///// CONSTRUCTOR
  public Administrador(String _nombre, String _mail, String _contrasenia) {
    super(_nombre, _mail, _contrasenia);
  }

  public void cambiarValoresDeFE(){

  }

  public void validarOrganizacion(Organizacion organizacion){

  }

  public void darDeAltaMedioDeTransporte(MedioDeTransporte medio){

  }
}
