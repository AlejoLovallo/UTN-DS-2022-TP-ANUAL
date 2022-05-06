package Domain.MediosDeTransporte;

import java.util.List;

public class TransportePublico extends MedioDeTransporte {
  private TipoTransportePublico tipoTransportePublico;
  private String linea;
  private List<Estacion> paradas;
}
