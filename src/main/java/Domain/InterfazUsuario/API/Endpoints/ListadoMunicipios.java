package Domain.InterfazUsuario.API.Endpoints;

import java.util.List;
import java.util.Optional;

public class ListadoMunicipios {

    private static ListadoMunicipios instance = null;
    public List<Municipio> municipios;

    public static ListadoMunicipios getInstance(){
        if (instance == null){
            instance = new ListadoMunicipios();
        }
        return instance;
    }

    public Optional<Municipio> getMunicipioById(String id){
        return this.municipios.stream()
                .filter(unMunicipio -> unMunicipio.id().equals(id))
                .findFirst();
    }

    public Optional<Municipio> getMunicipioByName(String name){
        return this.municipios.stream()
                .filter(unMunicipio -> unMunicipio.getNombre().equals(name))
                .findFirst();
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public List<Municipio> getMunicipios(){
        return municipios;
    }

}
