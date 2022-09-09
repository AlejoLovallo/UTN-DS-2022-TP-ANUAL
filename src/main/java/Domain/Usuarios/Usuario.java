package Domain.Usuarios;
import Domain.BaseDeDatos.EntidadPersistente;
import Domain.Usuarios.Excepciones.ContraseniaEsInvalidaException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario extends EntidadPersistente{
  //////////////////////////////////  VARIABLES
  @Column
  private String email;
  @Column
  private String username;
  @Column
  private String contraHasheada;
  @Column
  private Boolean validado;

  @Transient
  private UltimoIntento ultimoAcceso;

  @Transient
  private ArrayList<CriterioValidacion> validadoresContrasenia;

  @Transient
  private final String salt = "+@351";

  //////////////////////////////////  CONSTRUCTORES

  public Usuario(){

  }

  public Usuario(String username,String email,String contra,boolean validado) {
    if(!isContraseniaValida(contra)){
      throw new ContraseniaEsInvalidaException("no pasa por alguna de las validaciones de seguridad");
    }
    this.validado = validado;
    this.contraHasheada = generateHash(contra);
    this.username = username;
    this.email = email;
    this.ultimoAcceso = new UltimoIntento();
    //this.contacto = contacto;
  }

  //////////////////////////////////  GETTERS
  public boolean isValido(){
    return this.validado;
  }

  public String getUsername() {
    return this.username;
  }

  public String getEmail() {
    return this.email;
  }

  //////////////////////////////////  SETTERS
  public void setValidado(Boolean validado) {
    this.validado = validado;
  }

  public void setEmail(String _email){
    this.email = _email;
  }


  //////////////////////////////////  INTERFACE
  public boolean isColision(String contraAProbar){
    if(this.contraHasheada == null){
      //todo buscar contrasenia em DB si el usuario no tiene la contra

    }
    String contraHasheada = generateHash(contraAProbar);
    return this.contraHasheada.equals(contraHasheada);
  }

  public boolean intentoAcceso(){
    return this.ultimoAcceso.validar_acceso();
  }

  private boolean isContraseniaValida(String contra){

    this.validadoresContrasenia = new ArrayList<>();

    this.validadoresContrasenia.add(new Peores10KContra());
    this.validadoresContrasenia.add(new CriterioLongitud(8,80));

    for (CriterioValidacion criterioValidacion : this.validadoresContrasenia) {
      if (!criterioValidacion.validarContrasenia(contra))
        return false;
    }
    return true;
  }


  private String generateHash(String contrasenia) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance("SHA-256");
    }catch (NoSuchAlgorithmException e) {
      throw new ContraseniaEsInvalidaException("La contrasenia no pudo ser hasheada");
    }
    digest.reset();
    digest.update(this.salt.getBytes(StandardCharsets.UTF_8));
    byte[] hash = digest.digest(contrasenia.getBytes(StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();

    for (byte b : hash) {
      sb.append(String.format("%02x", b));
    }

    return sb.toString();
  }
}
