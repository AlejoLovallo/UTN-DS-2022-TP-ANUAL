package Domain.CalculadorDistancia.Endpoints;

public class Localidad {
  private String id;
  private String nombre;

  //Constructor

  public Localidad(String _id, String _nombre) {
    this.id = _id;
    this.nombre=_nombre;
  }


  // GETTERS

  public String getId() {
    return id;
  }
  public String getNombre() {
    return nombre;
  }

  // SETTERS
  public void setId(String id) {
    this.id = id;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
