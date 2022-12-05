package BaseDeDatos.Buscar;

import Domain.Miembro.Persona;
import Domain.Miembro.TipoDocumento;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Usuarios.Usuario;

public class BuscarPersonaTestDB {
  public static void main(String[] args) {
    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
    repositorioPersonasDB.crearPersona("Pepito","Gomez", TipoDocumento.DNI,"" +
            45154545,new Usuario("pepitoSandCompleto","mail@hotmail.com","Juan1345",true));
    Persona persona = repositorioPersonasDB.buscarPersonaPorUsername("pepitoSandCompleto");
    Persona persona2 = repositorioPersonasDB.buscarPersonaPorNroDocumento("101469451");


    System.out.println("----------------------------------------------------------------------------------------------------");
    System.out.println(Persona.toString(persona2));
    System.out.println("----------------------------------------------------------------------------------------------------");

    System.out.println("----------------------------------------------------------------------------------------------------");
    System.out.println(Persona.toString(persona));
    System.out.println("----------------------------------------------------------------------------------------------------");

  }
}
