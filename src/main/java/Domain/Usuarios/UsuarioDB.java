package Domain.Usuarios;

import Domain.BaseDeDatos.EntidadPersistente;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="usuario")
public class UsuarioDB extends EntidadPersistente {

  @Column
  private String usuario;

  @Column
  private String password;

  @Column
  private String mail;

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "direccionPostal_id", referencedColumnName = "id")
  private DireccionPostal direccionPostal;

  @ManyToMany(cascade =
      {
          CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.PERSIST
      }, fetch = FetchType.LAZY)
  @JoinTable(name = "usuario_x_mensaje",joinColumns = @JoinColumn(name = "usuario_id"),inverseJoinColumns = @JoinColumn (name = "mensaje_id"))
  private List<Mensaje> bandejaDeMensajes = new ArrayList<Mensaje>();

  @Transient
  private InterfazUsuarios interfazUsuarios = InterfazUsuarios.GetInstance();

  @Column
  private boolean habilitado;

  @Column
  private int intentos;

  public int getIntentos() {
    return intentos;
  }

  public void setDireccionPostal(DireccionPostal direccionPostal) {
    this.direccionPostal = direccionPostal;
  }

  public DireccionPostal getDireccionPostal() {
    return direccionPostal;
  }

  public void setBandejaDeMensajes(List<Mensaje> bandejaDeMensajes) {
    this.bandejaDeMensajes = bandejaDeMensajes;
  }

  public void setHabilitado(boolean habilitado) {
    this.habilitado = habilitado;
  }

  public boolean isHabilitado() {
    return habilitado;
  }

  public void setIntentos(int intentos) {
    this.intentos = intentos;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Mensaje> getBandejaDeMensajes() {
    return bandejaDeMensajes;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public String getMail() {
    return mail;
  }

  public Usuario() {

  }

  public boolean laPasswordCoincide(String unaPassword){
    return password.equals(unaPassword);
  }

  public boolean elNombreCoincide(String nombreDeUsuario) { return usuario.equals(nombreDeUsuario);}


  public void serNotificado(String mensaje) {
    Mensaje nuevoMensaje = new Mensaje();

    nuevoMensaje.setContenido(mensaje);
    nuevoMensaje.setFechaDelMensaje(new Date());
    bandejaDeMensajes.add(nuevoMensaje);
    interfazUsuarios.mostrarInformacion(mensaje);
  }

  @Transient
  GestorDeEgresos gestorDeEgresos = GestorDeEgresos.GetInstance();

  public void darseDeAltaComoRevisor(int compraID){
    Compra unaCompra = gestorDeEgresos.buscarCompraPorID(compraID);
    unaCompra.suscribirUsuario(this);
  }
}



