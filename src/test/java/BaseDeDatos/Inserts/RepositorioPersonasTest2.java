package BaseDeDatos.Inserts;

import Domain.Miembro.Persona;
import Domain.Miembro.TipoDocumento;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Usuarios.Usuario;

public class RepositorioPersonasTest2 {

  public static void main(String[] args) {

    Usuario user = new Usuario("pepitoSand23","pepitoGamer@gmail.com","contraSegura54+",true);

    //Persona persona = new Persona("pepeSand", "Prueba", TipoDocumento.DNI, "101469451");

    //persona.setUsuario(user);

    //Miembro miembro = new Miembro();

    RepositorioPersonasDB repositorioPersonas = new RepositorioPersonasDB();

    Persona persona = repositorioPersonas.crearPersona("pepeSandPrueba", "pepeSandPrueba", TipoDocumento.DNI, "1013924513",user);


    System.out.println("-----------------------------------------------------------------------------------------------------");
    System.out.println(Persona.toString(persona));
    System.out.println("-----------------------------------------------------------------------------------------------------");

    //repositorioPersonas.agregar(persona);

  }
}
