package Domain.CalculadorHC;

import Domain.Miembro.Miembro;
import Domain.Organizacion.AgenteSectorial;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.ServicioMedicion.Actividad;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import Domain.ServicioMedicion.TipoDeActividad;
//import org.graalvm.compiler.nodes.virtual.CommitAllocationNode;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CalculadorHC {

    private RepositorioFactores factoresDeEmision = RepositorioFactores.getInstance();

    private static CalculadorHC instance = null;

    // CONSTRUCTOR
    public static CalculadorHC getInstance(){
        if(instance == null){
            instance = new CalculadorHC();
        }
        return instance;
    }
    // GETTERS
    public RepositorioFactores getFactoresDeEmision() {
        return factoresDeEmision;
    }

    // SETTERS


    //METHODS

    public Double calcularHC(Miembro miembro) throws IOException {

        Double cantidadHC = 0.0;

        Double factorDeEmision = this.factoresDeEmision.getFactorDeEmisionSegunActividad(TipoDeActividad.COMBUSTION_MOVIL).getNumero();

        for(Trayecto trayecto : miembro.getTrayectos()){
            for(Tramo tramo : trayecto.getTramos()){
                Double unidadesConsumidas = tramo.determinarDistancia() * tramo.getMedioTransporte().getConsumoPorKm();
                cantidadHC += unidadesConsumidas * factorDeEmision;
            }
        }
        return cantidadHC;
    }

    public Double calcularHC(Organizacion organizacion) throws IOException {
        Double cantidadHC = 0.0;

        ArrayList<Actividad> actividades = organizacion.cargarMedicionesInternas(organizacion.getArchivoMediciones());

        for(int i = 0; i < actividades.size(); i++)
        {
            Actividad actividad = actividades.get(i);
            Double factorDeEmision = this.factoresDeEmision.getFactorDeEmisionSegunActividad(actividad.getNombre()).getNumero();
            cantidadHC += actividades.get(i).getConsumo() * factorDeEmision;
        }

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
        return cantidadHC;
    }

    public Double calcularHC(Sector sector) throws IOException {
        Double cantidadHC = 0.0;

        for(Miembro miembro : sector.getMiembros()){
            cantidadHC += calcularHC(miembro);
        }
        return cantidadHC;
    }
}
