package Domain.ServicioMedicion;

import java.io.IOException;

public abstract class ServicioMediciones {
  public abstract void cargarMediciones(String fileName) throws IOException;
}
