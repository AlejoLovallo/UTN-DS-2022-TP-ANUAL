package Domain.ServicioMedicion;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import Domain.Organizacion.Organizacion;

public abstract class ServicioMediciones {
  public abstract ArrayList<Actividad> cargarMediciones(String fileName, Organizacion org) throws IOException;
}
