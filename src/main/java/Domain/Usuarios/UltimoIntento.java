package Domain.Usuarios;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "ultimoIntento")
public class UltimoIntento {
  //////////////////////////////////  VARIABLES
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_ultimointento;

  @Column
  private LocalDateTime ultimoAcceso;
  @Column
  private int intento;
  @Column
  private Boolean ultIntentoCorrecto;


  public static String toString(UltimoIntento ultimoIntento) {
    if(ultimoIntento == null) return "El ultimo intento es nulo";

    return "UltimoIntento{" +
        "id_ultimointento=" + ultimoIntento.id_ultimointento +
        ", ultimoAcceso=" + ultimoIntento.ultimoAcceso +
        ", intento=" + ultimoIntento.intento +
        '}';
  }

  //////////////////////////////////  CONSTRUCTORES
  public UltimoIntento() {
    this.intento = 1;
    this.ultimoAcceso = LocalDateTime.now();
    this.ultIntentoCorrecto = true;
  }

  //////////////////////////////////  GETTERS

  //////////////////////////////////  SETTERS


  public Boolean getUltIntentoCorrecto() {
    return ultIntentoCorrecto;
  }

  public void setUltIntentoCorrecto(Boolean ultIntentoCorrecto) {
    this.ultIntentoCorrecto = ultIntentoCorrecto;
  }

  //////////////////////////////////  INTERFACE
  private int formulaDeUltimaSesion(){
    return this.intento * 3;
  }

  public boolean validar_acceso() {

    long diferenciaEnSegundos = Duration.between(this.ultimoAcceso,LocalDateTime.now()).getSeconds();

    if (diferenciaEnSegundos > formulaDeUltimaSesion() || this.ultIntentoCorrecto){
      setUltimoAcceso(LocalDateTime.now());
      //this.intento = 1;
      setIntento(1);
      return true;
    }
    setUltimoAcceso(LocalDateTime.now());
    //this.ultimoAcceso = LocalDateTime.now();
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
