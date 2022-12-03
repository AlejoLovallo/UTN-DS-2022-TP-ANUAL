package BaseDeDatos.Inserts;

import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.TipoOrganizacion;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Repositorios.RepositorioSectoresDB;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Contacto;
import Domain.Usuarios.Usuario;

public class RepositorioOrganizacionesTestDB {
  public static void main(String[] args) {
    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
    RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();

    Contacto contacto = new Contacto("contactoPrueba", "ContactoPrueba", 515151541, "tomas.casa123@gmail.com");

    Organizacion organizacion = new Organizacion( "OrganizacionPrueba", TipoOrganizacion.Empresa, ClasificacionOrganizacion.Ministerio, contacto);

    Usuario userOrg = repositorioUsuariosDB.crearUsuario("usuarioOrg","usuarioOrg@gmail.com","usuarioOrg12",true);

    organizacion.setUsuario(userOrg);

    repositorioOrganizacionesDB.agregar(organizacion);

  }
}
