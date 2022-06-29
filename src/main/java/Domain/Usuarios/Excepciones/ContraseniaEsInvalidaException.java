package Domain.Usuarios.Excepciones;

public class ContraseniaEsInvalidaException extends RuntimeException {
  public ContraseniaEsInvalidaException(String causa) {
    super("La contraseña es inválida porque " + causa);
  }

}