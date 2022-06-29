package Domain.Miembro.Excepciones;

public class MiembroNoPerteneceAOrganizacionException extends RuntimeException {
  public MiembroNoPerteneceAOrganizacionException(String organizacion){
    super("Miembro no pertence a " + organizacion);
  }
}
