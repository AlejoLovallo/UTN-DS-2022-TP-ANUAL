package Domain.Usuarios;

public class UsuarioException extends RuntimeException {
  public UsuarioException(String causa) {
    super("El usuario no se creó " + causa);
  }
}
