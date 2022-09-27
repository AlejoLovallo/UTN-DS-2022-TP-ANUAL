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
import java.text.ParseException;
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

        for(Actividad actividad : organizacion.getActividades()){
            cantidadHC += cacluarHcActividad(actividad, mes, anio);
        }

        for (Sector sector : organizacion.getSectores()){
            for(Miembro miembro : sector.getMiembros()){
                cantidadHC += calcularHC(miembro, mes, anio);
            }
        }

        return cantidadHC;
    }


    public Double cacluarHcActividad(Actividad actividad, Integer mes, Integer anio){
        
        Double factorDeEmision = this.factoresDeEmision.getFactorDeEmisionSegunActividad(actividad.getNombre()).getNumero();
        Double cantidadHC = actividad.encontrarConsumo(mes, anio) * factorDeEmision;
        return cantidadHC;
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
            cantidadHC *= ((trayecto.diasDelMesActivo(mes, anio)/7.0) * (trayecto.getFrecuenciaSemanal()) * (miembro.getSector().getOrganizacion().getNumDiasPorSemana()));
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
