package Contrasenia;

public class ContraseniaEsInvalidaException extends RuntimeException {
  public ContraseniaEsInvalidaException(String causa) {
    super("La contraseña es inválida porque " + causa);
  }

}