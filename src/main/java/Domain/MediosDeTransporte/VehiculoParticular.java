package Domain.MediosDeTransporte;


import Domain.BaseDeDatos.EntityManagerHelper;
import javax.persistence.*;

@Entity
@Table(name="vehiculoparticular")
public class VehiculoParticular extends MedioDeTransporte{

  @Enumerated(EnumType.STRING)
  private TipoVehiculo tipoVehiculo;
  @Enumerated(EnumType.STRING)
  private TipoCombustible tipoCombustible;
  @Column
  private Integer cantPasajeros;

  //////////////////////////////////  CONSTRUCTOR
  private VehiculoParticular(){

  }

  public VehiculoParticular(TipoVehiculo tipoVehiculo, TipoCombustible tipoCombustible, Integer cantPasajeros) {
    this.tipoVehiculo = tipoVehiculo;
    this.tipoCombustible = tipoCombustible;
    this.cantPasajeros = cantPasajeros;
  }


  //////////////////////////////////  GETTERS

  public TipoVehiculo getTipoVehiculo() {
    return this.tipoVehiculo;
  }
  public TipoCombustible getTipoCombustible() {
    return this.tipoCombustible;
  }

  public Integer getCantPasajeros() {
    return cantPasajeros;
  }

  //////////////////////////////////  SETTERS

  public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
    this.tipoVehiculo = tipoVehiculo;
    updateVehiculoParticular();
  }

  public void setTipoCombustible(TipoCombustible tipoCombustible) {
    this.tipoCombustible = tipoCombustible;
    updateVehiculoParticular();
  }

  public void setCantPasajeros(Integer cantPasajeros) {
    this.cantPasajeros = cantPasajeros;
    updateVehiculoParticular();
  }

  //////////////////////////////////  INTERFACE
  //INTERFACE

  public void updateVehiculoParticular(){
    try {
      EntityManagerHelper.beginTransaction();
      System.out.println("----------------LUEGO DE BEGIN TRAN-------------------");
      EntityManagerHelper.getEntityManager().persist(this);
      System.out.println("----------------LUEGO DE INSERT TRAN-------------------");
      EntityManagerHelper.commit();
      System.out.println("----------------LUEGO DE COMMIT-------------------");
    } catch (Exception e) {
      e.getCause();
      e.printStackTrace();
    } finally {
      EntityManagerHelper.closeEntityManager();
      System.out.println("----------------LUEGO DE CLOSE CON-------------------");
    }
  }

  public VehiculoParticular getVehiculoParticular(int vehiculoParticularid) {
    EntityManager em = EntityManagerHelper.getEntityManager();
    VehiculoParticular vehiculoParticular = em.find(VehiculoParticular.class, vehiculoParticularid);
    em.detach(vehiculoParticular);
    return vehiculoParticular;
  }
}
