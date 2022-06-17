package Domain.CalculadorDistancia.Endpoints;

public class Municipio {
    private String id;
    private String Nombre;

    // GETTERS
    public String getId() {
      return id;
    }
    public String getNombre() {
      return Nombre;
    }

    // SETTERS
    public void setId(String id) {
    this.id = id;
    }
    public void setNombre(String nombre) {
    Nombre = nombre;
    }
}
