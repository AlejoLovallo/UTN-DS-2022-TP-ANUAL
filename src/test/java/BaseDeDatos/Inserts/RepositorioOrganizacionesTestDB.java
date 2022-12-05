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

    Contacto contacto = new Contacto("contactoPrueba2", "ContactoPrueba2", 515151541, "otromail@gmail.com");

    Organizacion organizacion = new Organizacion( "OrganizacionPrueba2", TipoOrganizacion.Empresa, ClasificacionOrganizacion.Ministerio, contacto);

    Usuario userOrg = repositorioUsuariosDB.crearUsuario("usuarioOrg2","usuarioOrg2@gmail.com","usuarioOrg12",true);

    organizacion.setUsuario(userOrg);

    repositorioOrganizacionesDB.agregar(organizacion);

  }
}
