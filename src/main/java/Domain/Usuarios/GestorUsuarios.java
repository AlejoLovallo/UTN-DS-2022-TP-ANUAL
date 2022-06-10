package Domain.Usuarios;

import Domain.InterfazUsuario.InterfazUsuarios;
import Domain.Main;

import java.util.ArrayList;


public class GestorUsuarios {
  private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

  InterfazUsuarios interfazUsuarios = InterfazUsuarios.getInstance();

  private static GestorUsuarios instance = null;

  private GestorUsuarios() {
  }

  public static GestorUsuarios getInstance() {
    if (instance == null)
      instance = new GestorUsuarios();
    return instance;
  }

  public void consolaUsuario(){
    interfazUsuarios.menuUsuario();
    int opcion = Main.pedirPorPantallaInt();

    while (opcion != 11) {
      switch (opcion) {
        case 1:
          // this.registrarUsuario();
          break;
        case 2:
          //  this.loguearse();
          break;
        case 3:
          Usuario unUsuario = this.elegirUsuario();
        case 4:
          this.mostrarUsuarios();
          break;

        default:
          interfazUsuarios.mostrarError("La opcion ingresada no es valida.");
      }
      interfazUsuarios.menuUsuario();

      opcion = Main.pedirPorPantallaInt();
    }
  }

  private void mostrarUsuarios(){
    interfazUsuarios.mostrarInformacion("Usuarios Existentes :");
  }

  public boolean nombreDeUsuarioVacio(String nombreDeUsuario){
    boolean esVacio = false;
    if(nombreDeUsuario.isEmpty()){
      interfazUsuarios.mostrarError("El nombre de usuario no puede estar vacio");
      esVacio = true;
    }
    return esVacio;
  }

  public boolean laListaDeUsuariosEstaVacia() {
    return usuarios.isEmpty();
  }

  public ArrayList<Usuario> getUsuarios() {
    return usuarios;
  }

  public boolean usuarioYaExiste(String nombreDeUsuario) {
    return false;
  }

  public Usuario buscarUsuario(String nombreDeUsuario) {
    return null;
  }

  /*
  public void loguearse(String nombreUsuario, String password_hasheada, RepositorioDeUsuarios repoUsuarios) {
    int contador = 0;

    if( ! repoUsuarios.existe(nombreUsuario,password_hasheada) && contador < 10) {

      Usuario usuario = repoUsuarios.buscarUsuario(nombreUsuario,password_hasheada);
      interfazUsuarios.mostrarError("Password invalida.");
      interfazUsuarios.mostrarAdvertencia("Intentos Restantes: " + (10 - contador));
      contador += 1;
      interfazUsuarios.mostrarAdvertencia("Por favor espere " + contador * 10 + " segundos.");
      repoUsuarios.buscar(usuario.getId());
      usuario.setIntentos(contador);
      repoUsuarios.modificar(usuario);

      try {
        TimeUnit.SECONDS.sleep(contador * 10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    if (contador == 10) {
      interfazUsuarios.mostrarAdvertencia("Has gastado todos los intentos. Vuelve a intentarlo mas tarde.");
    }
  }
   */

  public void agregarUsuario(String usuario, String passwordHasheada) {

  }

  public Usuario elegirUsuario() {
    return null;
  }

  public void registrarUsuario(String usuario) {
  }
}
