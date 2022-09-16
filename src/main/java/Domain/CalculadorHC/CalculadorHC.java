package Domain.CalculadorHC;

import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.Miembro.Miembro;
import Domain.Organizacion.AgenteSectorial;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.ServicioMedicion.*;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
//import org.graalvm.compiler.nodes.virtual.CommitAllocationNode;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    public Double calcularHC(Organizacion organizacion, Integer mes, Integer anio) throws IOException {
    
        Double cantidadHC = 0.0;
        ArrayList <Actividad> act = new ArrayList<Actividad>();

        for(Actividad actividad : organizacion.getActividades()){
            if (actividad.perteneceAlPeriodo(mes, anio)){
                act.add(actividad);
            }
        }

        for(Actividad actividad : act){
            cantidadHC += procesarActividad(actividad);
        }

        for (Sector sector : organizacion.getSectores()){
            for(Miembro miembro : sector.getMiembros()){
                cantidadHC += calcularHC(miembro, mes, anio);
            }
        }

        return cantidadHC;
    }


    public Double procesarActividad(Actividad actividad){
        
        Double factorDeEmision = this.factoresDeEmision.getFactorDeEmisionSegunActividad(actividad.getNombre()).getNumero();
        Double cantidadHC = actividad.getConsumo() * factorDeEmision * this.conseguirValorFrecuenciaActividad(actividad);
        return cantidadHC;
    }

    public Double conseguirValorFrecuenciaActividad(Actividad actividad){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actividad.getFechaCarga());
        int mesActividad = calendar.get(Calendar.MONTH);

        if(actividad.getFrecuenciaServicio() == FrecuenciaServicio.MENSUAL){
            return 1.0;
        }
        else{
          return 1.0 / (mesActividad - 1);
        }
        
    }

    public Double calcularHC(Miembro miembro, Integer mes, Integer anio) throws IOException {

        if (mes < 1 || mes > 12){
            return 0.0;
        }

        Double cantidadHC = 0.0;

        Double factorDeEmision = this.factoresDeEmision.getFactorDeEmisionSegunActividad(TipoDeActividad.COMBUSTION_MOVIL).getNumero();

        for(Trayecto trayecto : miembro.getTrayectos()){
            for(Tramo tramo : trayecto.getTramos()){
                if(tramo.getMedioTransporte() instanceof VehiculoParticular){
                    Double unidadesConsumidas = tramo.determinarDistancia() * tramo.getMedioTransporte().getConsumoPorKm();
                    cantidadHC += unidadesConsumidas * factorDeEmision / ((VehiculoParticular) tramo.getMedioTransporte()).getCantPasajeros();
                }else{
                    Double unidadesConsumidas = tramo.determinarDistancia() * tramo.getMedioTransporte().getConsumoPorKm();
                    cantidadHC += unidadesConsumidas * factorDeEmision;
                }
            }
            cantidadHC *= ((trayecto.diasDelMesActivo(mes, anio)/7) * (trayecto.getFrecuenciaSemanal()) * (miembro.getSector().getOrganizacion().getNumDiasPorSemana()));
        } 
        return cantidadHC;
    }


    public Double calcularHC(AgenteSectorial agenteSectorial, Integer mes, Integer anio) throws IOException {
        Double cantidadHC = 0.0;

        for(Organizacion organizacion : agenteSectorial.getOrganizaciones()){
            cantidadHC += calcularHC(organizacion, mes, anio);
        }
        return cantidadHC;
    }

    public Double calcularHC(Sector sector) throws IOException {
        Double cantidadHC = 0.0;
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        int mesActual = calendar.get(Calendar.MONTH);
        int anioActual = calendar.get(Calendar.YEAR);

        for(Miembro miembro : sector.getMiembros()){
            cantidadHC += calcularHC(miembro, mesActual, anioActual);
        }
        return cantidadHC;
    }

    // HC TOTALES
    
}
