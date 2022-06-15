package Domain.InterfazUsuario.API.Endpoints;

import java.util.List;
import java.util.Optional;

public class ListadoProvincias {
    //private static ListadoProvincias instance = null;
    public List<Provincia> provincias;

    //public static ListadoProvincias getInstance(){
    //  if (instance == null){
    //    instance = new ListadoProvincias();
    //  }
    //  return instance;
    //}

    public Optional<Provincia> getProvinciaById(String id){
        return this.provincias.stream()
                .filter(unaProvincia -> unaProvincia.id().equals(id))
                .findFirst();
    }

    public Optional<Provincia> getProvinciaByName(String name){
        return this.provincias.stream()
                .filter(unaProvincia -> unaProvincia.getNombre().equals(name))
                .findFirst();
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }
}
