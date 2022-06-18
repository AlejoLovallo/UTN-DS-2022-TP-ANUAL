package Domain.CalculadorDistancia.Endpoints;

import jdk.vm.ci.meta.Local;

import java.util.List;
import java.util.Optional;

public class ListadoLocalidades {

    private static ListadoLocalidades instance = null;
    public List<Localidad> localidades;

    public static ListadoLocalidades getInstance(){
        if (instance == null){
            instance = new ListadoLocalidades();
        }
        return instance;
    }

    public Optional<Localidad> getLocalidadById(String id){
        return this.localidades.stream()
                .filter(unaLocalidad -> unaLocalidad.getId().equals(id))
                .findFirst();
    }

    public Optional<Localidad> getLocalidadByName(String name){
        return this.localidades.stream()
                .filter(unaLocalidad -> unaLocalidad.getNombre().equals(name))
                .findFirst();
    }

    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }

    public List<Localidad> getLocalidades(){
        return localidades;
    }
}
