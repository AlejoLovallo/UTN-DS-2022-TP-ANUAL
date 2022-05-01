package Domain.Miembro;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;

import java.util.HashMap;

public class Miembro {
  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private String documento;
  HashMap<Organizacion, Sector> sectorPorOrganizacion = new HashMap<Organizacion, Sector>();
}
