package Domain.CalculadorHC;

import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.Miembro.Miembro;
import Domain.Organizacion.*;
import Domain.Repositorios.RepositorioFactoresEmisionDB;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
//import org.graalvm.compiler.nodes.virtual.CommitAllocationNode;

import java.io.IOException;
import java.time.LocalDate;

// import org.apache.poi.ss.formula.PlainCellCache.Loc;

public class CalculadorHC {

    private RepositorioFactoresEmisionDB factoresDeEmision = new RepositorioFactoresEmisionDB();

    private static CalculadorHC instance = null;

    // CONSTRUCTOR
    public static CalculadorHC getInstance(){
        if(instance == null){
            instance = new CalculadorHC();
        }
        return instance;
    }
    
    // GETTERS
    public RepositorioFactoresEmisionDB getFactoresDeEmision() {
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
            cantidadHC += calcularHC(sector, mes, anio);
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
            if(
                    trayecto.getFechaInicio().getYear() <= anio
                    && trayecto.getFechaInicio().getMonthValue() <= mes
                    && trayecto.getFechaFin().getYear() >= anio
                    && trayecto.getFechaFin().getMonthValue() >= mes
            ){
                for(Tramo tramo : trayecto.getTramos()){
                    if(tramo.getMedioTransporte() instanceof VehiculoParticular){
                        if(! (tramo.getMedioTransporte().getTipoMedio() == TipoVehiculo.BiciPie )){
                            Double unidadesConsumidas = tramo.determinarDistancia() * tramo.getMedioTransporte().getConsumoPorKm();
                            cantidadHC += unidadesConsumidas * factorDeEmision / ((VehiculoParticular) tramo.getMedioTransporte()).getCantPasajeros();
                        }
                    }else{
                        Double unidadesConsumidas = tramo.determinarDistancia() * tramo.getMedioTransporte().getConsumoPorKm();
                        cantidadHC += unidadesConsumidas * factorDeEmision;
                    }
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

    public Double calcularHC(Sector sector, Integer mes, Integer anio) throws IOException {
        Double cantidadHC = 0.0;

        for(Miembro miembro : sector.getMiembros()){
            cantidadHC += calcularHC(miembro, mes, anio);
        }
        return cantidadHC;
    }

    // HC TOTALES
    public Double calcularHcTipoOrganizacion(TipoOrganizacion tipoOrganizacion, Integer mes, Integer anio) throws IOException{

        RepositorioOrganizacionesDB repositorioOrganizaciones = new RepositorioOrganizacionesDB();
        Double cantidadHC = 0.0;

        for (Organizacion organizacion : repositorioOrganizaciones.getOrganizaciones()){
            if (organizacion.getTipo() == tipoOrganizacion){
                cantidadHC += organizacion.calcularHC(mes, anio);
            }
        }
        return cantidadHC;
    }
/*
    public Double calcularHcTotal(Organizacion organizacion) throws IOException {
        Double cantidadHC = 0.0;

        //Integer anioIngreso = organizacion.getFechaIngreso().getYear();
        //Integer mesIngreso = organizacion.getFechaIngreso().getMonthValue();

        Integer anioActual = LocalDate.now().getYear();
        Integer mesActual = LocalDate.now().getMonthValue();

        if(anioActual == anioIngreso){
            for (int mes = mesIngreso; mes <= mesActual; mes++)
                cantidadHC += organizacion.calcularHC(mes, anioIngreso);
        }
        else{
            for (int mes = mesIngreso; mes <= 12; mes++)
                cantidadHC += organizacion.calcularHC(mes, anioIngreso);
            for (int mes = 1; mes <= mesActual; mes++)
                cantidadHC += organizacion.calcularHC(mes, anioActual);
            for (int anio = anioIngreso + 1; anio < anioActual; anio++)
                for (int mes = 1; mes <= 12; mes++)
                    cantidadHC += organizacion.calcularHC(mes, anio);
        }

        return cantidadHC;
    }*/

    // Calcular HC de un periodo 

    public Double calcularHcPeriodo(Organizacion organizacion, LocalDate fechaDesde, LocalDate fechaHasta) throws IOException {
        
        Double cantidadHC = 0.0;

        Integer mesDesde = fechaDesde.getMonthValue();
        Integer anioDesde = fechaDesde.getYear();
        Integer mesHasta = fechaHasta.getMonthValue();
        Integer anioHasta = fechaHasta.getYear();

        if(anioDesde == anioHasta){
            for (int mes = mesDesde; mes <= mesHasta; mes++)
                cantidadHC += calcularHC(organizacion, mes, anioDesde);
        }
        else{
            for (int mes = mesDesde; mes <= 12; mes++)
                cantidadHC += calcularHC(organizacion, mes, anioDesde);
            for (int mes = 1; mes <= mesHasta; mes++)
                cantidadHC += calcularHC(organizacion, mes, anioHasta);
            for (int anio = anioDesde + 1; anio < anioHasta; anio++)
                for (int mes = 1; mes <= 12; mes++)
                    cantidadHC += calcularHC(organizacion, mes, anio);
        }

        return cantidadHC;
    }

    public Double cacluarHcActividadPeriodo(Actividad actividad, LocalDate fechaDesde, LocalDate fechaHasta){
        
        Double cantidadHC = 0.0;

        Integer mesDesde = fechaDesde.getMonthValue();
        Integer anioDesde = fechaDesde.getYear();
        Integer mesHasta = fechaHasta.getMonthValue();
        Integer anioHasta = fechaHasta.getYear();

        if(anioDesde == anioHasta){
            for (int mes = mesDesde; mes <= mesHasta; mes++)
                cantidadHC += cacluarHcActividad(actividad, mes, anioDesde);
        }
        else{
            for (int mes = mesDesde; mes <= 12; mes++)
                cantidadHC += cacluarHcActividad(actividad, mes, anioHasta);
            for (int mes = 1; mes <= mesHasta; mes++)
                cantidadHC += cacluarHcActividad(actividad, mes, anioHasta);
            for (int anio = anioDesde + 1; anio < anioHasta; anio++)
                for (int mes = 1; mes <= 12; mes++)
                    cantidadHC += cacluarHcActividad(actividad, mes, anio);
        }

        return cantidadHC;
    }
}
