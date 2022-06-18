package Domain.CalculadorDistancia.Endpoints;

public class Pais {
  private String id;
  private String nombre;

  // CONSTRUCTOR

  public Pais(String id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  // GETTERS

  public String getId(){
    return this.id;
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

// TOKEN BERARER: 8MaZ8WdbaW+IhLyWfzV/T7oK54b6ILRxUDpW8+9Ba0c=