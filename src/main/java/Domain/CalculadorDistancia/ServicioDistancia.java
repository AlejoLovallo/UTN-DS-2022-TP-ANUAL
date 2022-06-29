package Domain.CalculadorDistancia;

import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.TransportePublico;

import java.io.IOException;
import java.util.List;

public abstract class ServicioDistancia {
  public abstract Double calcularDistancia(MedioDeTransporte _medioDeTransporte, Espacio _puntoPartida, Espacio _puntoLlegada) throws IOException;
}
