package Domain.API.Endpoints;

import java.util.List;

public class ListadoProvincias {
    private static ListadoProvincias instance = null;
    public List<Provincia> provincias;

    public static ListadoProvincias getInstance(){
      if (instance == null){
        instance = new ListadoProvincias();
      }
      return instance;
    }


}
