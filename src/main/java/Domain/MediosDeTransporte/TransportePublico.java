package Domain.MediosDeTransporte;

import java.util.ArrayList;

public class TransportePublico extends MedioDeTransporte {
  private TipoTransportePublico tipoTransportePublico;
  private String linea;
  private ArrayList<Estacion> paradas;
}
