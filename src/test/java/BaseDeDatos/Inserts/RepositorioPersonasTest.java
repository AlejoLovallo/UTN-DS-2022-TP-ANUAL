package BaseDeDatos.Inserts;

import Domain.Miembro.Persona;
import Domain.Miembro.TipoDocumento;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Usuarios.Usuario;

public class RepositorioPersonasTest {

  public static void main(String[] args) {

    Usuario user = new Usuario("pepitoSandNuevo","pepitoGamerSand@gmail.com","contraSegura54+",true);

    Persona persona = new Persona("pepeSand", "Prueba", TipoDocumento.DNI, "101469451");

    //persona.setUsuario(user);

    //Miembro miembro = new Miembro();

    RepositorioPersonasDB repositorioPersonas = new RepositorioPersonasDB();

    repositorioPersonas.agregar(persona);

  }
}
