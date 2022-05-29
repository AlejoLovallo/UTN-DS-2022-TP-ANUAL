package Domain.Organizacion;

import java.util.ArrayList;

public class RepositorioSectores {
  private static RepositorioSectores instance = null;

  private ArrayList<Sector> sectores;

  public static RepositorioSectores getInstance() {
    if (instance == null) {
      instance = new RepositorioSectores();
    }
    return instance;
  }
}
