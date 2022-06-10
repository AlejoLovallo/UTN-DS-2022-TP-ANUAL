package Domain.MediosDeTransporte;

import java.util.List;
import Domain.MediosDeTransporte.TipoTransportePublico;
import Domain.MediosDeTransporte.Estacion;

public class TransportePublico extends MedioDeTransporte {

  private TipoTransportePublico tipoTransportePublico;
  private String linea;
  private List<Estacion> paradas;

  //////////////////////////////////  CONSTRUCTOR

  public TransportePublico(TipoTransportePublico _tipoTransportePublico, String _linea,  List<Estacion> _paradas){
    this.tipoTransportePublico = _tipoTransportePublico;
    this.linea = _linea;
    this.paradas = _paradas;

  }

  //////////////////////////////////  GETTERS

  public TipoTransportePublico getTipoTransportePublico() {
    return tipoTransportePublico;
  }

  public String getLinea() {
    return linea;
  }

  public List<Estacion> getParadas() {
    return paradas;
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
}
