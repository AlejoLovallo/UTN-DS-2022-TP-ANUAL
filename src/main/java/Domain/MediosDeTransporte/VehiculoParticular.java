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
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("ds");
    System.out.println("----------------LUEGO DE Crear Entity Manager-------------------");
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      System.out.println("----------------LUEGO DE BEGIN TRAN-------------------");
      em.merge(this);
      System.out.println("----------------LUEGO DE UPDATE TRAN-------------------");
      em.getTransaction().commit();
      System.out.println("----------------LUEGO DE COMMIT-------------------");
    } catch (Exception e) {
      e.getCause();
      e.printStackTrace();
    } finally {
      em.close();
      System.out.println("----------------LUEGO DE CLOSE CON-------------------");
    }
  }

  public VehiculoParticular getVehiculoParticular(int vehiculoParticularid) {
    EntityManager em = EntityManagerHelper.getEntityManager();
    VehiculoParticular vehiculoParticular = em.find(VehiculoParticular.class, new Long(vehiculoParticularid));
    em.detach(vehiculoParticular);
    return vehiculoParticular;
  }
}
