package Domain.API.Endpoints;

import java.util.List;
import java.util.Optional;

public class ListadoPaises {
  private static ListadoPaises instance = null;
  public List<Pais> paises;

  public static ListadoPaises getInstance(){
    if (instance == null){
      instance = new ListadoPaises();
    }
    return instance;
  }

  public Optional<Pais> getPaisById(String id){
    return this.paises.stream()
        .filter(unPais -> unPais.id().equals(id))
        .findFirst();
  }

  public void setPaises(List<Pais> paises) {
    this.paises = paises;
  }

  public List<Pais> getPaises(){
    return paises;
  }

}
