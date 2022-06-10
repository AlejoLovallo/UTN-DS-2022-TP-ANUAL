package Domain.InterfazUsuario;

public class InterfazPassword {
  private static InterfazPassword instance = null;

  private InterfazPassword() {
  }

  public static InterfazPassword getInstance() {
    if (instance == null)
      instance = new InterfazPassword();
    return instance;
  }

  public void mostrarError(String mensaje){
    System.out.println(mensaje);
  }

  public void mostrarAdvertencia(String mensaje){
    System.out.println(mensaje);
  }

  public void mostrarInformacion(String mensaje){
    System.out.println(mensaje);
  }

  /*
  public String pedirString(String mensaje){
    System.out.println(mensaje);
    return Main.pedirPorPantallaString();
  }
  */
}