package Domain.Contrasenia;

import java.time.Duration;
import java.time.LocalDateTime;

public class UltimoIntento {
  //////////////////////////////////  VARIABLES
  private LocalDateTime ultimoAcceso;
  private int intento;
  private int formulaDeUltimaSesion(){
    return this.intento * 3;
  }


  //////////////////////////////////  CONSTRUCTORES
  public UltimoIntento() {
    this.intento = 1;
    this.ultimoAcceso = LocalDateTime.now();
  }

  //////////////////////////////////  GETTERS

  //////////////////////////////////  SETTERS

  //////////////////////////////////  INTERFACE
  public boolean validar_acceso() {
    if(Duration.between(this.ultimoAcceso,LocalDateTime.now()).toMillis()>formulaDeUltimaSesion()){
      this.intento = 1;
      return true;
    }
    this.ultimoAcceso = LocalDateTime.now();
    this.intento++;
    return false;
  }
}
