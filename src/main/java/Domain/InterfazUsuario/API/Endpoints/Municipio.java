package Domain.InterfazUsuario.API.Endpoints;

public class Municipio {
    private String id;
    private String Nombre;

  public String id() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNombre() {
    return Nombre;
  }

  public void setNombre(String nombre) {
    Nombre = nombre;
  }
}
