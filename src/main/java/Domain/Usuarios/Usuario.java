package Domain.Usuarios;
import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.Usuarios.Excepciones.ContraseniaEsInvalidaException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario{
  //////////////////////////////////  VARIABLES
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  private UltimoIntento ultimoAcceso;

  @Transient
  private ArrayList<CriterioValidacion> validadoresContrasenia;

  @Transient
  private final String salt = "+@351";

  //////////////////////////////////  CONSTRUCTORES

  private Usuario(){

  }

  public Usuario(String username, String mail, String contra, boolean validado) {
    if(!isContraseniaValida(contra)){
      throw new ContraseniaEsInvalidaException("no pasa por alguna de las validaciones de seguridad");
    }
    this.validado = validado;
    this.contraHasheada = generateHash(contra);
    this.username = username;
    this.mail = mail;
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

  public String getMail() {
    return this.mail;
  }

  public void setUsername(String username) {
    this.username = username;
    updateUsuario();
  }

  public void setContraHasheada(String contraHasheada) {
    this.contraHasheada = contraHasheada;
    updateUsuario();
  }

  //////////////////////////////////  SETTERS
  public void setValidado(Boolean validado) {
    this.validado = validado;
    updateUsuario();
  }

  public void setMail(String mail){
    this.mail = mail;
    updateUsuario();
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


  public void updateUsuario(){
    try {
      EntityManagerHelper.beginTransaction();
      System.out.println("----------------LUEGO DE BEGIN TRAN-------------------");
      EntityManagerHelper.getEntityManager().persist(this);
      System.out.println("----------------LUEGO DE INSERT TRAN-------------------");
      EntityManagerHelper.commit();
      System.out.println("----------------LUEGO DE COMMIT-------------------");
    } catch (Exception e) {
      e.getCause();
      e.printStackTrace();
    } finally {
      EntityManagerHelper.closeEntityManager();
      System.out.println("----------------LUEGO DE CLOSE CON-------------------");
    }
  }

  public static Usuario getUsuario(int usuarioID) {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityManagerHelper.beginTransaction();
    Usuario usuario = em.find(Usuario.class, usuarioID);
    em.detach(usuario);
    EntityManagerHelper.commit();
    return usuario;
  }
}
