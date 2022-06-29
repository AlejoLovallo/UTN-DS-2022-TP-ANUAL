package Domain.Organizacion;
import java.util.ArrayList;

/**
 * Singleton pattern
 */

public class RepositorioOrganizaciones {
  private static RepositorioOrganizaciones instance = null;

  private ArrayList<Organizacion> organizaciones;

  private RepositorioOrganizaciones(){}

  public static RepositorioOrganizaciones GetInstance(){
      if(instance == null){
        instance = new RepositorioOrganizaciones();
      }
      return instance;
  }



}
