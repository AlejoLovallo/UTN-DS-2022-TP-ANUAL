package Domain.MediosDeTransporte;

import java.util.List;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.TipoCombustible;
public class VehiculoParticular extends MedioDeTransporte{

  private TipoVehiculo tipoVehiculo;
  private TipoCombustible tipoCombustible;
  private Integer cantPasajeros;

  //////////////////////////////////  CONSTRUCTOR

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
