package Domain.Usuarios.Excepciones;

public class ArchivoInaccesibleException extends RuntimeException {
  public ArchivoInaccesibleException() {
    super("El archivo es inaccesible");
  }

}