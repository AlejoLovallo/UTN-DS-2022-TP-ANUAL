package Contrasenia;

public class CriterioLongitud implements CriterioValidacion{
  private int minimo;
  private int maximo;

  public CriterioLongitud(int minimo, int maximo) {
    this.minimo = minimo;
    this.maximo = maximo;
  }

  //todo testearlo
  public boolean validarContrasenia(String contrasenia) {
      if(contrasenia.length()>=this.minimo){
        if(contrasenia.length()<=this.maximo){
          return true;
        }
        return false;
      }
      return false;
  }
}
