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

    public double calcularHC(Organizacion organizacion) throws IOException {
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        int mesActual = calendar.get(Calendar.MONTH);
        int anioActual = calendar.get(Calendar.YEAR);

        double valorHC = 0.0;
        for(ServicioHCExcel servicio : organizacion.getReportes()){
            if(servicio.estaActivo())
                valorHC += servicio.getCalculoHC();
        }

        for (Sector sector : organizacion.getSectores()){
            for(Miembro miembro : sector.getMiembros()){
                valorHC += calcularHC(miembro, mesActual, anioActual);
            }
        }

        return valorHC;
    }

    public void procesarActividadAnual(Organizacion organizacion) throws IOException, ParseException {
        ArrayList<ServicioHCExcel> serviciosHCExcel = new ArrayList<>();
        ArrayList<Actividad> actividades = organizacion.cargarMedicionesInternas(organizacion.getArchivoMediciones());

        for(int mes = 1; mes <= 12; mes++){
            for(Actividad actividad : actividades){
                serviciosHCExcel.add(this.procesarActividadMes(actividad, mes));
            }
        }

        organizacion.setReportes(serviciosHCExcel);
    }

    public ServicioHCExcel procesarActividadMes(Actividad actividad, int mes){
        Double factorDeEmision = this.factoresDeEmision.getFactorDeEmisionSegunActividad(actividad.getNombre()).getNumero();
        double cantidadHC = actividad.getConsumo() * factorDeEmision * this.conseguirValorFrecuenciaActividad(actividad, mes);

        System.out.println("EL CONSUMO ES: " + actividad.getConsumo() );
        System.out.println("EL factorDeEmision ES: " + factorDeEmision );
        System.out.println("this.conseguirValorFrecuenciaActividad(actividad, mes): " + this.conseguirValorFrecuenciaActividad(actividad, mes) );


        ServicioHCExcel servicioHCExcel = new ServicioHCExcel(
                cantidadHC,
                actividad.getFrecuenciaServicio(),
                actividad.getFechaCarga()
        );
        servicioHCExcel.setMes(mes);
        return servicioHCExcel;
    }

    public double conseguirValorFrecuenciaActividad(Actividad actividad, int mes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actividad.getFechaCarga());
        int mesDeCierre = calendar.get(Calendar.MONTH);
        if(mesDeCierre < mes){
            if(actividad.getFrecuenciaServicio() == FrecuenciaServicio.MENSUAL){
                return 1;
            }
            else{
                return 1f / mesDeCierre;
            }
        }
        else{
            return 0;
        }
    }

    /*public Double calcularHC(Miembro miembro) throws IOException {

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
        }
        return cantidadHC;
    }*/

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

    /*public Double calcularHC(Organizacion organizacion) throws IOException {
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
    }*/

    public Double calcularHC(AgenteSectorial agenteSectorial) throws IOException {
        Double cantidadHC = 0.0;

        for(Organizacion organizacion : agenteSectorial.getOrganizaciones()){
            cantidadHC += calcularHC(organizacion);
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
}
