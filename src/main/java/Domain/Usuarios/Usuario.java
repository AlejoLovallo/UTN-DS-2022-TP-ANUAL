package Domain.Usuarios;
import Domain.Usuarios.Excepciones.ContraseniaEsInvalidaException;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.*;

@Entity
@Table(name = "usuario"/*, catalog = "curso"*/)
public class Usuario{
  //////////////////////////////////  VARIABLES
  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id_usuario;
  @Column
  private String mail;
  @Column
  private String username;
  @Column
  private String contraHasheada;
  @Column
  private Boolean validado;

  @Transient
  private String contraSinHash;

  //@Transient
  //@Column
  //private Boolean isAdmin;


  //@Transient
  @OneToOne(cascade = CascadeType.ALL)//(mappedBy = "usuario")
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name = "id_ultimointento" , referencedColumnName = "id_ultimointento")
  private UltimoIntento ultimointento;

  @Transient
  private ArrayList<CriterioValidacion> validadoresContrasenia;

  @Transient
  private final String salt = "+@351";

  //////////////////////////////////  CONSTRUCTORES

  public Usuario(){

  }

  public Usuario(String username, String mail, String contra, boolean validado) {
    if(!isContraseniaValida(contra)){
      throw new ContraseniaEsInvalidaException("no pasa por alguna de las validaciones de seguridad");
    }
    this.validado = validado;
    this.contraHasheada = generateHash(contra);
    this.username = username;
    try {
      InternetAddress emailAddr = new InternetAddress(mail);
      emailAddr.validate();
    }
    catch (AddressException ex) {
      throw new RuntimeException("Debe ingresar una direccion de email valida");
    }
    this.ultimointento = new UltimoIntento();
    //this.isAdmin = false;
    this.mail = mail;
    //this.contacto = contacto;
  }


  //////////////////////////////////  GETTERS
  public boolean isValido(){
    return this.validado;
  }

  public String getUsername() {
    return this.username;
  }
/*
  public Boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Boolean admin) {
    isAdmin = admin;
    //update();
  }*/

  public String getMail() {
    return this.mail;
  }

  public void setUsername(String username) {
    this.username = username;
    //update();
  }

  public void setUltIntentoCorrecto(Boolean ultIntentoCorrecto){
    this.ultimointento.setUltIntentoCorrecto(ultIntentoCorrecto);
  }

  public void setContraHasheada(String contraHasheada) {
    this.contraHasheada = contraHasheada;
    //update();
  }

  public void setContraSinHash(String contra) {
    this.contraSinHash = contra;
    //update();
  }

  public UltimoIntento getUltimointento() {
    return ultimointento;
  }

  public String getContraSinHash() {
    return contraSinHash;
  }

  //////////////////////////////////  SETTERS
  public void setValidado(Boolean validado) {
    this.validado = validado;
    //update();
  }

  public void setMail(String mail){
    try {
      InternetAddress emailAddr = new InternetAddress(mail);
      emailAddr.validate();
      this.mail = mail;
      //update();
    }
    catch (AddressException ex) {
      throw new RuntimeException("Debe ingresar una direccion de email valida");
    }
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
    return this.ultimointento.validar_acceso();
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


  public static String toString(Usuario usuario) {
    if(usuario == null) return "el usuario es nulo";

    return "Usuario{" +
        "id_usuario=" + usuario.id_usuario +
        ", mail='" + usuario.mail + '\'' +'\n'+
        ", username='" + usuario.username + '\'' +'\n'+
        ", contraHasheada='" + usuario.contraHasheada + '\'' +'\n'+
        ", validado=" + usuario.validado +'\n'+
        ", ultimointento=" + UltimoIntento.toString(usuario.ultimointento) +
        '}';
  }

  /*
  public void update(){
    EntityManagerHelper.tranUpdate(this);
  }

  public static Usuario get(int usuarioID) {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityManagerHelper.beginTransaction();
    Usuario usuario = em.find(Usuario.class, usuarioID);
    em.detach(usuario);
    EntityManagerHelper.commit();
    EntityManagerHelper.closeEntityManager();
    return usuario;
  }

  public void insert(){
    EntityManagerHelper.tranPersist(this);
  }*/
}
