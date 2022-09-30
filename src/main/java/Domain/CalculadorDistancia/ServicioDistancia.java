package Domain.CalculadorDistancia;

import Domain.Espacios.Espacio;
import Domain.MediosDeTransporte.MedioDeTransporte;

import java.io.IOException;

public abstract class ServicioDistancia {
  public abstract Double calcularDistancia(MedioDeTransporte _medioDeTransporte, Espacio _puntoPartida, Espacio _puntoLlegada) throws IOException;
}
