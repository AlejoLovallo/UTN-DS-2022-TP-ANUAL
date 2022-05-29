package Domain.Miembro.Excepciones;

public class UnicoSectorPorOrganizacionException extends RuntimeException{
  public UnicoSectorPorOrganizacionException() {
    super("Un miembro solo puede trabajar en un sector por organizacion");
  }
}
