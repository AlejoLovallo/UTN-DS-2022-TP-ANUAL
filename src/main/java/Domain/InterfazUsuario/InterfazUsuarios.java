package Domain.InterfazUsuario;

public class InterfazUsuarios {
  private static InterfazUsuarios instance = null;

  public void mostrarError(String mensaje){
    System.out.println(mensaje);
  }
  public String pedirString(String mensaje){
    System.out.println(mensaje);
    return Main.pedirPorPantallaString();
  }
  public double pedirDouble(String mensaje){
    System.out.println(mensaje);
    return Main.pedirPorPantallaDouble();
  }
  public int pedirInt(String mensaje){
    System.out.println(mensaje);
    return Main.pedirPorPantallaInt();
  }

  public void mostrarAdvertencia(String mensaje){
    System.out.println(mensaje);
  }
  public void mostrarInformacion(String mensaje){
    System.out.println(mensaje);
  }

  private InterfazUsuarios() {
  }

  public static InterfazUsuarios GetInstance() {
    if (instance == null)
      instance = new InterfazUsuarios();
    return instance;
  }

  public void menuUsuario() //Menu
  {
    System.out.println("Ingrese una opcion:");
    System.out.println(" ");
    System.out.println("1- REGISTRAR USUARIO");
    System.out.println("2- INICIAR SESION");
    System.out.println("3- CAMBIAR PASSWORD");
    System.out.println("4- MOSTRAR USUARIOS");
    System.out.println("5- AGREGAR CRITERIO DE ITEM");
    System.out.println("6- AGREGAR CATEGORIA A CRITERIO");
    System.out.println("7- MOSTRAR CATEGORIAS");
    System.out.println("8- AGREGAR INGRESO");
    System.out.println("9- VINCULAR INGRESO A EGRESO");
    System.out.println("10- VALIDAR COMPRAS");
    System.out.println("11 - SALIR");
    System.out.println(" ");
  }

  public void menuDeIngresarCategoria(){
    this.mostrarInformacion("¿Quiere ingresar otra categoría?");
    this.mostrarInformacion("1- Si");
    this.mostrarInformacion("2- No");
  }
}
