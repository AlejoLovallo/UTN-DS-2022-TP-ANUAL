package Domain.CalculadorDistancia;

import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.MediosDeTransporte.MedioDeTransporte;

import java.io.IOException;
import java.util.List;

public abstract class ServicioDistancia {

  public Double calcularDistancia(MedioDeTransporte _medioDeTransporte, Espacio _puntoPartida, Espacio _puntoLlegada) throws IOException {
    return 0.0;
  }
}
