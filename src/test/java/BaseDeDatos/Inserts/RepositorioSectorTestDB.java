package BaseDeDatos.Inserts;

import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.Espacios.TipoDireccion;
import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;
import Domain.Miembro.TipoDocumento;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Organizacion.TipoOrganizacion;
import Domain.Repositorios.RepositorioDireccionDB;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Repositorios.RepositorioSectoresDB;
import Domain.Usuarios.Contacto;
import Domain.Usuarios.Usuario;

public class RepositorioSectorTestDB {
  public static void main(String[] args) {
    RepositorioSectoresDB repositorioSectoresDB = new RepositorioSectoresDB();

/*
    Usuario user = new Usuario("pepitoSandCompleto","pepitoSandCompleto@gmail.com","pepitoSandCompleto54+",true);

    Persona persona = new Persona("pepitoSandCompleto", "pepitoSandCompleto", TipoDocumento.DNI, "101469451");

    Contacto contacto = new Contacto("pepitoSandCompleto", "pepitoSandCompleto", 515151541, "contacto@gmail.com");

    Organizacion organizacion = new Organizacion( "Empresa Pepe Completo", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorSecundario, contacto);
*/
    RepositorioDireccionDB repositorioDireccionDB = new RepositorioDireccionDB();
    Direccion direccion = repositorioDireccionDB.crearDireccion("Argentina", "Buenos Aires", "Capital Federal", "pepitoSandCompleto", "pepitoSandCompleto Barros", 123, TipoDireccion.Trabajo);

    RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
    Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorNombre("OrganizacionPrueba2");

    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
    Persona persona = repositorioPersonasDB.buscarPersonaPorUsername("juan98");

    Miembro miembro = new Miembro(null);

    Sector sector = new Sector("Presidente Prueba",direccion,organizacion,miembro);
    //repositorioSectoresDB.agregar(sector);

    organizacion.getSectores().add(sector);
    repositorioOrganizacionesDB.modificar(organizacion);

    persona.getMiembros().add(miembro);
    miembro.setPersona(persona);
    miembro.vincularSector(sector);

    repositorioPersonasDB.modificar(persona);
  }
}
