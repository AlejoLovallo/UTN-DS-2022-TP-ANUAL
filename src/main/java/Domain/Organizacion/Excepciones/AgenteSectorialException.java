package Domain.Organizacion.Excepciones;

public class AgenteSectorialException extends RuntimeException {
  public AgenteSectorialException(String causa) {
    super("No se pudo crear el Agente Sectorial porque "+ causa);
  }
}
