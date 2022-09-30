package Domain.Usuarios;

import Domain.BaseDeDatos.EntityManagerHelper;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

//@Entity
//@Table(name = "ultimoIntento")
public class UltimoIntento {
  //////////////////////////////////  VARIABLES
  //@Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_ultimoIntento;

  //@Column
  private LocalDateTime ultimoAcceso;
  //@Column
  private int intento;



  //////////////////////////////////  CONSTRUCTORES
  public UltimoIntento() {
    this.intento = 1;
    this.ultimoAcceso = LocalDateTime.now();
  }

  //////////////////////////////////  GETTERS

  //////////////////////////////////  SETTERS

  //////////////////////////////////  INTERFACE
  private int formulaDeUltimaSesion(){
    return this.intento * 3;
  }

  public boolean validar_acceso() {
    if(Duration.between(this.ultimoAcceso,LocalDateTime.now()).toMillis()>formulaDeUltimaSesion()){
      //this.intento = 1;
      setIntento(1);
      return true;
    }
    //this.ultimoAcceso = LocalDateTime.now();
    setUltimoAcceso(LocalDateTime.now());
    this.intento++;
    //setIntento(this.intento);
    return false;
  }

  private void setUltimoAcceso(LocalDateTime ultimoAcceso) {
    this.ultimoAcceso = ultimoAcceso;
    //update();
  }

  private void setIntento(int intento) {
    this.intento = intento;
    //update();
  }

  /*
  public void update(){
    EntityManagerHelper.tranUpdate(this);
  }

  public static UltimoIntento get(int ultimoIntentoID) {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityManagerHelper.beginTransaction();
    UltimoIntento ultimoIntento = em.find(UltimoIntento.class, ultimoIntentoID);
    em.detach(ultimoIntento);
    EntityManagerHelper.commit();
    EntityManagerHelper.closeEntityManager();
    return ultimoIntento;
  }

  public void insert(){
    EntityManagerHelper.tranPersist(this);
  }*/
}
