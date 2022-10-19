package Domain.ServicioMedicion;

import Domain.Organizacion.FrecuenciaServicio;

import java.util.Calendar;
import java.util.Date;

public class ServicioHCExcel {
  private Double calculoHC;
  private FrecuenciaServicio periodicidad;
  private Date fechaCarga;
  private  int mes;


  public ServicioHCExcel(Double _hc, FrecuenciaServicio _periodicidad, Date _fecha){
    this.setCalculoHC(_hc);
    this.setPeriodicidad(_periodicidad);
    this.setFechaCarga(_fecha);
  }


  public Double getCalculoHC() {
    return calculoHC;
  }

  public void setCalculoHC(Double calculoHC) {
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

  public void setMes(int mes) {
    this.mes = mes;
  }

  public int getMes() {
    return mes;
  }

  public void setFechaCarga(Date fechaCarga) {
    this.fechaCarga = fechaCarga;
  }

  public boolean estaActivo(){
    Date fechaActual = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fechaActual);
    int mesActual = calendar.get(Calendar.MONTH);
    int anioActual = calendar.get(Calendar.YEAR);

    calendar.setTime(this.getFechaCarga());
    int mesCarga = calendar.get(Calendar.MONTH);
    int anioCarga = calendar.get(Calendar.YEAR);

    if(anioCarga < anioActual && mesCarga < mesActual){
        return true;
    }
    else
      return false;
  }
}
