package Domain.Administracion;

import Domain.Contrasenia.Contrasenia;

public class Usuario {
  private String mail;
  private String nombre;
  private Contrasenia contrasenia;

  public Usuario(String _nombre, String _mail, String _contrasenia){
    this.nombre = _nombre;
    this.mail = _mail;
    this.contrasenia = new Contrasenia(_contrasenia);
  }


}
