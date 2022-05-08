package Contrasenia;

import java.time.Duration;
import java.time.LocalDateTime;

public class CriterioUltimoIntento implements CriterioValidacion{

  private LocalDateTime ultimoAcceso;
  private int intento;


  private int formulaDeUltimaSesion(){
    return this.intento * 3;
  }

  public CriterioUltimoIntento() {
    this.intento = 0;
    this.ultimoAcceso = LocalDateTime.now();
  }

  //todo testearlo
  public boolean validarContrasenia(String contrasenia) {
    if(Duration.between(this.ultimoAcceso,LocalDateTime.now()).toMillis()>formulaDeUltimaSesion()){
      this.intento = 0;
      return true;
    }
    this.ultimoAcceso = LocalDateTime.now();
    this.intento++;
    return false;
  }
}
