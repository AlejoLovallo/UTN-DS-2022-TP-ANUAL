package BaseDeDatos.Eliminar;


import Domain.Miembro.Persona;
import Domain.Repositorios.RepositorioPersonasDB;

public class EliminarPersonasTestDB {
  public static void main(String[] args) {
    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();

    Persona persona = repositorioPersonasDB.buscar(7);

    repositorioPersonasDB.eliminar(persona);

    System.out.println("---------------------");

  }
}
