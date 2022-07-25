package Domain.ServicioMedicion;

import java.io.IOException;
import java.util.ArrayList;

public abstract class ServicioMediciones {
  public abstract ArrayList<Actividad> cargarMediciones(String fileName) throws IOException;
}
