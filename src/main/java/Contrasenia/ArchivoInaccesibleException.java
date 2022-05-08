package Contrasenia;

public class ArchivoInaccesibleException extends RuntimeException {
  public ArchivoInaccesibleException() {
    super("El archivo es inaccesible");
  }

}