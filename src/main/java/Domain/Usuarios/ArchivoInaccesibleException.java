package Domain.Usuarios;

public class ArchivoInaccesibleException extends RuntimeException {
  public ArchivoInaccesibleException() {
    super("El archivo es inaccesible");
  }

}