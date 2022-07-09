package Domain.CalculadorDistancia.Endpoints;

public class Provincia {
  private String id;
  private String nombre;

  //constructor

  public Provincia(String _id, String _name) {
    this.id = _id;
    this.nombre=_name;
  }

  // GETTERS
  public String getId() {
    return id;
  }
  public String getNombre() {
    return nombre;
  }

  // SETTER
  public void setId(String id) {
    this.id = id;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

}
