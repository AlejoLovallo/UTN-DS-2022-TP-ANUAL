package BaseDeDatos.Eliminar;

import Domain.Organizacion.Sector;
import Domain.Repositorios.RepositorioSectoresDB;

public class EliminarSectorTestDB {


  public static void main(String[] args) {
    RepositorioSectoresDB repositorioSectoresDB = new RepositorioSectoresDB();

    Sector sectorAEliminar = repositorioSectoresDB.buscar(5);

    repositorioSectoresDB.eliminar(sectorAEliminar);

    System.out.println("---------------------");

  }
}
