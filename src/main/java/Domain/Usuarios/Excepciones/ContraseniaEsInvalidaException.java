<<<<<<< HEAD:src/main/java/Domain/Usuarios/Excepciones/ContraseniaEsInvalidaException.java
package Domain.Contrasenia.Excepciones;
=======
package Domain.Usuarios;
>>>>>>> contraseniaTomi:src/main/java/Domain/Usuarios/ContraseniaEsInvalidaException.java

public class ContraseniaEsInvalidaException extends RuntimeException {
  public ContraseniaEsInvalidaException(String causa) {
    super("La contraseña es inválida porque " + causa);
  }

}