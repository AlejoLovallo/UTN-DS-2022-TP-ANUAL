package Domain.Contrasenia.Excepciones;

public class ArchivoInaccesibleException extends RuntimeException {
  public ArchivoInaccesibleException() {
    super("El archivo es inaccesible");
  }

}