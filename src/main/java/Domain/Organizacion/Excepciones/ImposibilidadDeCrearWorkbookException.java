package Domain.Organizacion.Excepciones;

public class ImposibilidadDeCrearWorkbookException extends RuntimeException{
  public ImposibilidadDeCrearWorkbookException(String causa) {
    super("No se pudo crear Workbook: " + causa);
  }
}
