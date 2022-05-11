package Domain.Contrasenia;

public class CriterioLongitud implements CriterioValidacion{
  private int minimo;
  private int maximo;

  public CriterioLongitud(int minimo, int maximo) {
    this.minimo = minimo;
    this.maximo = maximo;
  }

  //todo testearlo
  public boolean validarContrasenia(String contrasenia) {
      return contrasenia.length()>=this.minimo && contrasenia.length()<=this.maximo;
  }
}
