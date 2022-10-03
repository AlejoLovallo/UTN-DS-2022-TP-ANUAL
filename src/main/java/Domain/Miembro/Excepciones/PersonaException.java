package Domain.Miembro.Excepciones;

public class PersonaException extends RuntimeException {
  public PersonaException(String causa){
    super("La persona no se pudo crear porque  " + causa);
  }
}
