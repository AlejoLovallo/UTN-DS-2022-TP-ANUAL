package Domain.CalculadorDistancia.Endpoints;

public class Municipio {
    private String id;
    private String nombre;

    // GETTERS
    public String getId() {
      return id;
    }
    public String getNombre() {
      return nombre;
    }

    // SETTERS
    public void setId(String _id) {
    this.id = _id;
    }
    public void setNombre(String _nombre) {
    nombre = _nombre;
    }
}
