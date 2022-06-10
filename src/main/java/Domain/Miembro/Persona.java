package Domain.Miembro;

import Domain.Usuarios.Usuario;

import java.util.ArrayList;

public class Persona {
  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private String documento;
  private ArrayList<Miembro> listaMiembros;
  private Usuario usuario;

  //////////////////////////////////  CONSTRUCTOR
  public Persona(String _nombre, String _apellido, TipoDocumento _tipoDocumento, String _documento){
    this.setNombre(_nombre);
    this.setApellido(_apellido);
    this.setTipoDocumento(_tipoDocumento);
    this.setDocumento(_documento);
  }

  //////////////////////////////////  GETTERS

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public TipoDocumento getTipoDocumento() {
    return tipoDocumento;
  }

  public String getDocumento() {
    return documento;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public ArrayList<Miembro> getMiembros(){
    return listaMiembros;
  }

  //////////////////////////////////  SETTERS

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setTipoDocumento(TipoDocumento tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public void agregarMiembro(Miembro miembro){
    this.listaMiembros.add(miembro);
  }

  //////////////////////////////////  INTERFACE

}
