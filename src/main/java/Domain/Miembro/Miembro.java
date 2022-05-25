package Domain.Miembro;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Trayecto.Trayecto;

import java.util.HashMap;
import java.util.List;

public class Miembro {
  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private String documento;
  private List<Trayecto> trayectos;
  HashMap<Organizacion, Sector> sectorPorOrganizacion;

  //////////////////////////////////  GETTERS


  //////////////////////////////////  SETTERS


  //////////////////////////////////  INTERFACE


}
