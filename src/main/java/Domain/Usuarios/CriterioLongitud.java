package Domain.Usuarios;

public class CriterioLongitud implements CriterioValidacion{
  //////////////////////////////////  VARIABLES
  private int minimo;
  private int maximo;

  //////////////////////////////////  CONSTRUCTORES
  public CriterioLongitud(int minimo, int maximo) {
    this.minimo = minimo;
    this.maximo = maximo;
  }

  //////////////////////////////////  GETTERS
  public int getMinimo(){return minimo;}
  public int getMaximo(){return maximo;}
  //////////////////////////////////  SETTERS

  //////////////////////////////////  INTERFACE
  //todo testearlo
  public boolean validarContrasenia(String contrasenia) {
      return contrasenia.length()>=this.minimo && contrasenia.length()<=this.maximo;
  }
}
