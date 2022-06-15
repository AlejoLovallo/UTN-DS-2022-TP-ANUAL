package Domain.InterfazUsuario.API.Endpoints;

public class Localidad {
  private String id;
  private String nombre;

  public String id(){
    return this.id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
