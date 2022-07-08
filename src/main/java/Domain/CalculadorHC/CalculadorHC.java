package Domain.CalculadorHC;

import Domain.Miembro.Miembro;
import Domain.Organizacion.AgenteSectorial;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import org.graalvm.compiler.nodes.virtual.CommitAllocationNode;

import java.io.IOException;
import java.util.ArrayList;

public class CalculadorHC {

    private ArrayList<FactorEmision> factoresDeEmision =new ArrayList<>();

    // CONSTRUCTOR

    // GETTERS

    // SETTERS

    //METHODS

    public Double calcularHC(Miembro miembro) throws IOException {
        Double cantidadHC = 0.0;

        for(Trayecto trayecto : miembro.getTrayectos()){
            for(Tramo tramo : trayecto.getTramos()){
                Double unidadesConsumidas = tramo.determinarDistancia()/tramo.getMedioTransporte().getConsumoPorKm();
                // Multiplicarlo por el factor de emision y sumarselo a la cantidad HC
            }
        }

        return cantidadHC;
    }

    public Double calcularHC(Organizacion organizacion) throws IOException {
        Double cantidadHC = 0.0;

        for (Sector sector : organizacion.getSectores()){
            for(Miembro miembro : sector.getMiembros()){
                cantidadHC += calcularHC(miembro);
            }
        }

        return cantidadHC;
    }

    public Double calcularHC(AgenteSectorial agenteSectorial) throws IOException {
        Double cantidadHC = 0.0;

        for(Organizacion organizacion : agenteSectorial.getOrganizaciones()){
            cantidadHC += calcularHC(organizacion);
        }

    }

    public Double calcularHC(Sector sector) throws IOException {
        Double cantidadHC = 0.0;

        for(Miembro miembro : sector.getMiembros()){
            cantidadHC += calcularHC(miembro);
        }


    }
}
