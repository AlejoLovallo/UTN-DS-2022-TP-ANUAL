package Domain.MediosDeTransporte;

import java.util.List;

import Domain.Espacios.Espacio;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.TipoCombustible;
public class VehiculoParticular extends MedioDeTransporte{

  private TipoVehiculo tipoVehiculo;
  private TipoCombustible tipoCombustible;

  //////////////////////////////////  CONSTRUCTOR
  public VehiculoParticular(TipoVehiculo _tipoVehiculo, TipoCombustible _tipoCombustible){
    this.tipoVehiculo = _tipoVehiculo;
    this.tipoCombustible = _tipoCombustible;
  }

  //////////////////////////////////  GETTERS

  public TipoVehiculo getTipoVehiculo() {
    return tipoVehiculo;
  }
  public TipoCombustible getTipoCombustible() {
    return tipoCombustible;
  }

  //////////////////////////////////  SETTERS

  public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
    this.tipoVehiculo = tipoVehiculo;
  }

  public void setTipoCombustible(TipoCombustible tipoCombustible) {
    this.tipoCombustible = tipoCombustible;
  }

  //////////////////////////////////  INTERFACE
}
