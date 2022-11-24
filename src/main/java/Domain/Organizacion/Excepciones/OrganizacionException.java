package Domain.Organizacion.Excepciones;

public class OrganizacionException extends RuntimeException {
  public OrganizacionException(String causa){
    super("La organizacion no se pudo crear porque  " + causa);
  }
}
