package Domain.Organizacion.Excepciones;

public class ImposiblidadDeCerrarWorkbookException extends RuntimeException{
  public ImposiblidadDeCerrarWorkbookException(String causa){super("No se pudo cerrar Workbook: " + causa);}
}
