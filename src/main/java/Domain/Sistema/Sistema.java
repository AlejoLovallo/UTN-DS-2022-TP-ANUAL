package Domain.Sistema;

public class Sistema {
  // GestorDeUsuarios gestorDeUsuarios = GestorDeUsuarios.GetInstance();

  private static Sistema instance = null;

  private Sistema() {
  }

  public static Sistema getInstance() {
    if (instance == null)
      instance = new Sistema();
    return instance;
  }

  public void iniciar(){
    //gestorDeUsuarios.consolaUsuario();
  }
}
