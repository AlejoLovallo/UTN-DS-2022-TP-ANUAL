package Domain.CalculadorHC;

import Domain.Miembro.Miembro;
import Domain.Organizacion.AgenteSectorial;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import org.graalvm.compiler.nodes.virtual.CommitAllocationNode;

import java.util.ArrayList;

public class CalculadorHC {

    private ArrayList<FactorEmision> factoresDeEmision =new ArrayList<>();

    // CONSTRUCTOR

    // GETTERS

    // SETTERS

    //METHODS

    public Double calcularHC(Miembro miembro){
        Double cantidadHC = 0.0;

        return cantidadHC;
    }

    public Double calcularHC(Organizacion organizacion){
        Double cantidadHC = 0.0;

        for(Miembro miembro : organizacion.getMiembros()){
            cantidadHC += calcularHC(miembro);
        }
        return cantidadHC;
    }

    public Double calcularHC(AgenteSectorial agenteSectorial){
        Double cantidadHC = 0.0;

        for(Organizacion organizacion : agenteSectorial.getOrganizaciones()){
            cantidadHC += calcularHC(organizacion);
        }

    }
}
