package Domain.Miembro;

import Domain.Usuarios.Usuario;

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
  @Column
  private String documento;
  @OneToMany(mappedBy = "persona")
 // @JoinColumn(name="id_miembro",referencedColumnName = "id_miembro")
  private List<Miembro> listaMiembros;
  @OneToOne
  @JoinColumn(name="id_usuario",referencedColumnName = "id_usuario")
  private Usuario usuario;

  //////////////////////////////////  CONSTRUCTOR
  public Persona(){

  }

  public Persona(String _nombre, String _apellido, TipoDocumento _tipoDocumento, String _documento){
    this.setNombre(_nombre);
    this.setApellido(_apellido);
    this.setTipoDocumento(_tipoDocumento);
    this.setDocumento(_documento);
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

  public String getDocumento() {
    return documento;
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

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public void agregarMiembro(Miembro miembro){
    this.listaMiembros.add(miembro);
  }

  //////////////////////////////////  INTERFACE

}
