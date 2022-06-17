package Domain.MediosDeTransporte;

import java.util.List;

import Domain.Espacios.Estacion;

public class TransportePublico extends MedioDeTransporte {

  private TipoTransportePublico tipoTransportePublico;
  private String linea;
  public List<Estacion> paradas;

  //////////////////////////////////  CONSTRUCTOR

  public TransportePublico(TipoTransportePublico tipoTransportePublico, String linea, List<Estacion> paradas) {
    this.tipoTransportePublico = tipoTransportePublico;
    this.linea = linea;
    this.paradas = paradas;
  }

  //////////////////////////////////  GETTERS

  public TipoTransportePublico getTipoTransportePublico() {
    return this.tipoTransportePublico;
  }

  public String getLinea() {
    return this.linea;
  }

  public List<Estacion> getParadas() {
    return this.paradas;
  }

  //////////////////////////////////  SETTERS


  public void setTipoTransportePublico(TipoTransportePublico tipoTransportePublico) {
    this.tipoTransportePublico = tipoTransportePublico;
  }

  public void setLinea(String linea) {
    this.linea = linea;
  }

  public void setParadas(List<Estacion> paradas) {
    this.paradas = paradas;
  }

  //////////////////////////////////  INTERFACE
  public void darDeAltaParada(Estacion estacion){
    this.paradas.add(estacion.getNumeroDeEstacion(),estacion);
  }

}
