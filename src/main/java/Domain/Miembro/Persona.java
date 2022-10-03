package Domain.Miembro;

import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.Usuarios.Usuario;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="persona")
public class Persona {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_persona;

  @Column
  private String nombre;

  @Column
  private String apellido;

  @Enumerated(EnumType.STRING)
  private TipoDocumento tipoDocumento;

  @Column(name="nroDocumento")
  private String nroDocumento;

  @OneToMany(mappedBy = "persona" , cascade = CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
 // @JoinColumn(name="id_miembro",referencedColumnName = "id_miembro")
  private List<Miembro> listaMiembros;

  @OneToOne(cascade = CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name="id_usuario",referencedColumnName = "id_usuario")
  private Usuario usuario;

  //////////////////////////////////  CONSTRUCTOR
  private Persona(){

  }

  public Persona(String _nombre, String _apellido, TipoDocumento _tipoDocumento, String _documento){
    this.setNombre(_nombre);
    this.setApellido(_apellido);
    this.setTipoDocumento(_tipoDocumento);
    this.setNroDocumento(_documento);
    this.listaMiembros = new ArrayList<Miembro>();
  }

  //////////////////////////////////  GETTERS

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public TipoDocumento getTipoDocumento() {
    return tipoDocumento;
  }

  public String getNroDocumento() {
    return nroDocumento;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public List<Miembro> getMiembros(){
    return listaMiembros;
  }

  //////////////////////////////////  SETTERS

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setTipoDocumento(TipoDocumento tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  public void setNroDocumento(String nroDocumento) {
    this.nroDocumento = nroDocumento;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public void agregarMiembro(Miembro miembro){
    this.listaMiembros.add(miembro);
  }

  //////////////////////////////////  INTERFACE

  public void updateUsuario(){
    EntityManagerHelper.tranUpdate(this);
  }

  public static Usuario getUsuario(int usuarioID) {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityManagerHelper.beginTransaction();
    Usuario usuario = em.find(Usuario.class, usuarioID);
    em.detach(usuario);
    EntityManagerHelper.commit();
    EntityManagerHelper.closeEntityManager();
    return usuario;
  }

  public void insertUsuario(){
    EntityManagerHelper.tranPersist(this);
  }


  public static String toString(Persona persona) {
    if(persona == null) return "la persona es nula";

    return "Persona{" +
        "id_persona=" + persona.id_persona +
        ", nombre='" + persona.nombre + '\'' +'\n'+
        ", apellido='" + persona.apellido + '\'' +'\n'+
        ", tipoDocumento=" + persona.tipoDocumento +'\n'+
        ", nroDocumento='" + persona.nroDocumento + '\'' + '\n'+
        ", listaMiembros=" + Miembro.toString(persona.listaMiembros) + '\n'+
        ", usuario=" + Usuario.toString(persona.usuario) +
        '}';
  }
}
