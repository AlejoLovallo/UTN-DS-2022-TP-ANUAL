package Domain.Contrasenia;
import Domain.Contrasenia.Excepciones.ContraseniaEsInvalidaException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Contrasenia {
  private String salt;
  private String contraHasheada;

  private ArrayList<CriterioValidacion> validadoresContrasenia;

  public Contrasenia(String _contrasenia) {
    if(isValida(_contrasenia)){
      throw new ContraseniaEsInvalidaException("no pasa por alguna de las validaciones de seguridad");
    }
    byte[] unSalt = getSaltRandom();

    this.salt = new String(unSalt, StandardCharsets.UTF_8);

    try {
      this.contraHasheada = generateHash(_contrasenia, unSalt);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  public String getSalt() {
    return salt;
  }

  private boolean isValida(String contra){

    this.validadoresContrasenia = new ArrayList<>();

    this.validadoresContrasenia.add(new Peores10KContra());
    this.validadoresContrasenia.add(new CriterioLongitud(8,80));
    this.validadoresContrasenia.add(new CriterioUltimoIntento());

    for (CriterioValidacion criterioValidacion : this.validadoresContrasenia) {
      if (!criterioValidacion.validarContrasenia(contra))
        return false;
    }
    return true;
  }

  public static byte[] getSaltRandom()  {
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[30];
    secureRandom.nextBytes(salt); // proxima semilla
    return salt;
  }


  private static String generateHash(String contrasenia, byte[] salt) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.reset();
    digest.update(salt);
    byte[] hash = digest.digest(contrasenia.getBytes(StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();

    for (byte b : hash) {
      sb.append(String.format("%02x", b));
    }

    return sb.toString();
  }


}
