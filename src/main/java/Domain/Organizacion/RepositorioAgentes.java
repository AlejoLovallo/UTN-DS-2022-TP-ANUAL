package Domain.Organizacion;

import java.util.ArrayList;

/**
 * Singleton pattern
 */

public class RepositorioAgentes {
  private static RepositorioAgentes instance = null;

  private ArrayList<AgenteSectorial> agenteSectoriales;

  private RepositorioAgentes(){}

  public static RepositorioAgentes GetInstance(){
    if(instance == null){
      instance = new RepositorioAgentes();
    }
    return instance;
  }



}
