package BaseDeDatos.Inserts;

import Domain.Miembro.Persona;
import Domain.Miembro.TipoDocumento;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Usuarios.Usuario;

public class RepositorioPersonasTest {

  public static void main(String[] args) {

    Usuario user = new Usuario("usuarioPersona","usuarioPersona@gmail.com","usuarioPersona12",true);

    RepositorioPersonasDB repositorioPersonas = new RepositorioPersonasDB();


    repositorioPersonas.crearPersona("persona","personaPrueba",TipoDocumento.DNI,"101469451",user);

  }
}
