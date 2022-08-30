package Domain.ServicioMedicion;

import java.util.Date;

public class ServicioHCExcel {
  private Integer calculoHC;
  private FrecuenciaServicio periodicidad;
  private Date fechaCarga;

  public ServicioHCExcel(Integer _hc, FrecuenciaServicio _periodicidad, Date _fecha){
    this.setCalculoHC(_hc);
    this.setPeriodicidad(_periodicidad);
    this.setFechaCarga(_fecha);
  }


  public Integer getCalculoHC() {
    return calculoHC;
  }

  public void setCalculoHC(Integer calculoHC) {
    this.calculoHC = calculoHC;
  }

  public FrecuenciaServicio getPeriodicidad() {
    return periodicidad;
  }

  public void setPeriodicidad(FrecuenciaServicio periodicidad) {
    this.periodicidad = periodicidad;
  }

  public Date getFechaCarga() {
    return fechaCarga;
  }

  public void setFechaCarga(Date fechaCarga) {
    this.fechaCarga = fechaCarga;
  }
}
