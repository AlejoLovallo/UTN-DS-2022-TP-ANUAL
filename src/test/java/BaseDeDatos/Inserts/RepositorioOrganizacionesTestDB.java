package BaseDeDatos.Inserts;

import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.TipoOrganizacion;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Repositorios.RepositorioSectoresDB;
import Domain.Usuarios.Contacto;

public class RepositorioOrganizacionesTestDB {
  public static void main(String[] args) {
    RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();

    Contacto contacto = new Contacto("contactoPrueba", "ContactoPrueba", 515151541, "tomas.casa123@gmail.com");

    Organizacion organizacion = new Organizacion( "RazonSocial", TipoOrganizacion.Empresa, ClasificacionOrganizacion.Ministerio, contacto, 5);

    //contacto.setOrganizacion(organizacion);

    repositorioOrganizacionesDB.agregar(organizacion);

  }
}
