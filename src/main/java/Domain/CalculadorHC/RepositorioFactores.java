package Domain.CalculadorHC;

import Domain.ServicioMedicion.TipoDeActividad;

import java.util.ArrayList;
import java.util.Optional;

public class RepositorioFactores {
    private static RepositorioFactores instance = null;

    private ArrayList<FactorEmision> factoresDeEmision;

    //////////////////////////////////  CONSTRUCTORES
    private RepositorioFactores(){
        this.factoresDeEmision = new ArrayList<>();
    }

    public static RepositorioFactores getInstance(){
        if(instance == null){
            instance = new RepositorioFactores();
        }
        return instance;
    }

    //////////////////////////////////  GETTERS

    public ArrayList<FactorEmision> getFactoresDeEmision() {
        return factoresDeEmision;
    }

    public FactorEmision getFactorDeEmision(TipoDeActividad tipoDeActividad) {

        Optional <FactorEmision> factor = factoresDeEmision.stream()
                .filter(f -> f.getTipoDeActividad().equals(tipoDeActividad)).findAny();

        return factor.get();
    }

    //////////////////////////////////  SETTERS

    public void setFactoresDeEmision(ArrayList<FactorEmision> factoresDeEmision) {
        this.factoresDeEmision = factoresDeEmision;
    }

    //////////////////////////////////  INTERFACE

}
