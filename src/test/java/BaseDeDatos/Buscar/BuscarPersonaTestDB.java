package BaseDeDatos.Buscar;

import Domain.Miembro.Persona;
import Domain.Repositorios.RepositorioPersonasDB;

public class BuscarPersonaTestDB {
  public static void main(String[] args) {
    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();

    Persona persona = repositorioPersonasDB.buscarPersonaPorUsername("pepitoSandCompleto");
    Persona persona2 = repositorioPersonasDB.buscarPersonaPorNroDocumento("101469451");

    ///repositorioPersonasDB.eliminar(persona);

    System.out.println("----------------------------------------------------------------------------------------------------");
    System.out.println(Persona.toString(persona2));
    System.out.println("----------------------------------------------------------------------------------------------------");

    System.out.println("----------------------------------------------------------------------------------------------------");
    System.out.println(Persona.toString(persona));
    System.out.println("----------------------------------------------------------------------------------------------------");

  }
}
