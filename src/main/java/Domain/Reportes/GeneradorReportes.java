package Domain.Reportes;

import Domain.CalculadorHC.CalculadorHC;
import Domain.Organizacion.*;
import Domain.Organizacion.Actividad;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GeneradorReportes {

    private RepositorioOrganizaciones repositorioOrganizaciones = RepositorioOrganizaciones.GetInstance();
    private RepositorioAgentes repositorioAgentes = RepositorioAgentes.GetInstance();
    private CalculadorHC calculadorHC = CalculadorHC.getInstance();
    private static GeneradorReportes instance;

    public GeneradorReportes getInstance(){
        if(instance == null){
            instance = new GeneradorReportes();
        }
        return instance;
    }

    public Reporte reporteHC_Org(TipoOrganizacion tipo, LocalDate fechaDesde, LocalDate fechaHasta) throws IOException {

        Double cantidadHC = 0.0;

        for (Organizacion organizacion : repositorioOrganizaciones.getOrganizaciones()){
            if (organizacion.getTipo() == tipo){
                calculadorHC.calcularHcPeriodo(organizacion, fechaDesde, fechaHasta);
            }
        }

        ReporteTotal reporte = new ReporteTotal(fechaDesde, fechaHasta, cantidadHC);

        return reporte;

    }

    public Reporte reporteHC_SecTer(AgenteSectorial agenteSectorial, LocalDate fechaDesde, LocalDate fechaHasta) throws IOException {
        
        Double cantidadHC = 0.0;

        for (Organizacion organizacion : agenteSectorial.getOrganizaciones()){
            cantidadHC += calculadorHC.calcularHcPeriodo(organizacion, fechaDesde, fechaHasta);
        }

        ReporteTotal reporte = new ReporteTotal(fechaDesde, fechaHasta, cantidadHC);

        return reporte;
    }

    public Reporte reporteCompHC_Org(Organizacion organizacion, LocalDate fechaDesde, LocalDate fechaHasta){

        ArrayList <Actividad> listaActividades = new ArrayList<>();
        ReporteComposicion reporte = new ReporteComposicion(fechaDesde, fechaHasta, listaActividades);

        for(Actividad actividad : organizacion.getActividades()){
            
            Double cantidadHC = 0.0;
            
            cantidadHC += calculadorHC.cacluarHcActividadPeriodo(actividad, fechaDesde, fechaHasta);

            Actividad act = new Actividad(actividad.getNombre(), actividad.getTipoDeConsumo());
            Consumo con = new Consumo(1, 1, cantidadHC);
            ArrayList <Consumo> lista = new ArrayList<>();
            lista.add(con);
            act.setConsumos(lista);

            reporte.getActividades().add(act);
        }

        return reporte;
    }

    // Me quede acá. Despues sigo
    public void reporteCompHC_SecTer(AgenteSectorial agenteSectorial, LocalDate fechaDesde, LocalDate fechaHasta){

        ArrayList <Actividad> listaActividades = new ArrayList<>();
        
        for( Organizacion organizacion : agenteSectorial.getOrganizaciones()){
            for(Actividad actividad : organizacion.getActividades()){
                
                if()
                Double cantidadHC = 0.0;
                
                cantidadHC += calculadorHC.cacluarHcActividadPeriodo(actividad, fechaDesde, fechaHasta);
    
                Actividad act = new Actividad(actividad.getNombre(), actividad.getTipoDeConsumo());
                Consumo con = new Consumo(1, 1, cantidadHC);
                ArrayList <Consumo> lista = new ArrayList<>();
                lista.add(con);
                act.setConsumos(lista);

                listaActividades.add(act);
            }
        }

        return reporte;
    }

    public void reporteCompHC_Pais(String pais){
        /*RepositorioOrganizaciones repositorioOrganizaciones = RepositorioOrganizaciones.GetInstance();
        Double p

        for(Organizacion organizacion : repositorioOrganizaciones.getOrganizaciones()){
            for(Sector sector : organizacion.getSectores()){
                if(((Direccion)sector.getEspacioDeTrabajo()).getPais().equals(pais))
                    cantidadHC +=
            }
        }*/
    }

    public void reporteEvolucionHC_Org(Organizacion organizacion) throws IOException {

        Integer anioIngreso = organizacion.getFechaIngreso().getYear();
        Integer mesIngreso = organizacion.getFechaIngreso().getMonthValue();

        Integer anioActual = LocalDate.now().getYear();
        Integer mesActual = LocalDate.now().getMonthValue();

        if(anioIngreso == anioActual){
            for (int mes = mesIngreso; mes <= mesActual; mes++)
                System.out.println(organizacion.calcularHC(mes, anioIngreso));
        }
        else{
            for (int mes = mesIngreso; mes <= 12; mes++)
                System.out.println(organizacion.calcularHC(mes, anioIngreso));
            for (int anio = anioIngreso + 1; anio < anioActual; anio++)
                for (int mes = 1; mes <= 12; mes++)
                    System.out.println(organizacion.calcularHC(mes, anio));
            for (int mes = 1; mes <= mesActual; mes++)
                System.out.println(organizacion.calcularHC(mes, anioActual));
        }
    }

    public void reporteEvolucionHC_SecTer(AgenteSectorial agenteSectorial) throws IOException {
        System.out.println(agenteSectorial.getNombre());
        LocalDate fechaIngreso = agenteSectorial.getFechaMasTemprana();
        Integer anioIngreso = fechaIngreso.getYear();
        Integer mesIngreso = fechaIngreso.getMonthValue();

        Integer anioActual = LocalDate.now().getYear();
        Integer mesActual = LocalDate.now().getMonthValue();

        if (anioIngreso == anioActual) {
            for (int mes = mesIngreso; mes <= mesActual; mes++)
                System.out.println(agenteSectorial.calcularHC(mes, anioIngreso));
        } else {
            for (int mes = mesIngreso; mes <= 12; mes++)
                System.out.println(agenteSectorial.calcularHC(mes, anioIngreso));
            for (int anio = anioIngreso + 1; anio < anioActual; anio++)
                for (int mes = 1; mes <= 12; mes++)
                    System.out.println(agenteSectorial.calcularHC(mes, anio));
            for (int mes = 1; mes <= mesActual; mes++)
                System.out.println(agenteSectorial.calcularHC(mes, anioActual));
        }
    }
}
