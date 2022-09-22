package Domain.MediosDeTransporte;

import java.util.List;

import Domain.Espacios.Espacio;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.TipoCombustible;
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
  public VehiculoParticular(){

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
  }

  public void setTipoCombustible(TipoCombustible tipoCombustible) {
    this.tipoCombustible = tipoCombustible;
  }

  public void setCantPasajeros(Integer cantPasajeros) {
    this.cantPasajeros = cantPasajeros;
  }

  //////////////////////////////////  INTERFACE
}
