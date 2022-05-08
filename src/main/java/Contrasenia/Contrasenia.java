package Contrasenia;
import java.util.ArrayList;

public class Contrasenia {
  private String salt;
  private String contra;

  private ArrayList<CriterioValidacion> validadoresContrasenia;

  public Contrasenia(String contra) {
    if(isValida(contra)){
      //todo hacer una excepcion de contraseña no valida
    }
    this.contra = contra;
    //todo agregar salt? y crear hash
  }

  private boolean isValida(String contra){

    this.validadoresContrasenia.add(new Peores10KContra());
    this.validadoresContrasenia.add(new CriterioLongitud(8,80));
    this.validadoresContrasenia.add(new CriterioUltimoIntento());

    for (CriterioValidacion criterioValidacion : this.validadoresContrasenia) {
      if (!criterioValidacion.validarContrasenia(contra))
        return false;
    }
    return true;
  }


  public String getContra() {
    return contra;
  }

}
