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

    public double calcularHCOrganizacion(Organizacion organizacion){
        double valorHC = 0;
        for(ServicioHCExcel servicio : organizacion.getReportes()){
            valorHC += servicio.getCalculoHC();
        }

        //TODO calculo HC miembro
        return valorHC;
    }

    public void procesarActividadAnual(Organizacion organizacion){
        ArrayList<ServicioHCExcel> serviciosHCExcel = new ArrayList<>();
        ArrayList<Actividad> actividades = new ArrayList<>();

        for(Actividad actividad : actividades){
            for(int mes = 1; mes <= 12; mes++)
                serviciosHCExcel.add(this.procesarActividadMes(actividad,mes));
        }

        organizacion.setReportes(serviciosHCExcel);
    }

    public ServicioHCExcel procesarActividadMes(Actividad actividad, int mes){
        Double factorDeEmision = this.factoresDeEmision.getFactorDeEmisionSegunActividad(actividad.getNombre()).getNumero();
        double cantidadHC = actividad.getConsumo() * factorDeEmision * this.conseguirValorFrecuenciaActividad(actividad, mes);

        ServicioHCExcel servicioHCExcel = new ServicioHCExcel(
                cantidadHC,
                actividad.getFrecuenciaServicio(),
                actividad.getFechaCarga()
        );
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

    public Double calcularHC(Miembro miembro) throws IOException {

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
