package Domain.Reportes;

import Domain.CalculadorHC.CalculadorHC;
import Domain.Organizacion.*;
import Domain.Organizacion.Actividad;

import java.io.IOException;
import java.time.LocalDate;

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

    public void reporteHC_Org(TipoOrganizacion tipo) throws IOException {

        Double cantidadHC = 0.0;

        for (Organizacion organizacion : repositorioOrganizaciones.getOrganizaciones()){
            if (organizacion.getTipo() == tipo){
                cantidadHC += calculadorHC.calcularHcTotal(organizacion);
            }
        }
        System.out.println(cantidadHC);
    }

    public void reporteHC_SecTer(AgenteSectorial agenteSectorial) throws IOException {
        Double cantidadHC = 0.0;

        for (Organizacion organizacion : agenteSectorial.getOrganizaciones()){
            cantidadHC += calculadorHC.calcularHcTotal(organizacion);
        }
        System.out.println(cantidadHC);
    }

    public void reporteCompHC_Org(Organizacion organizacion){

        Integer anioIngreso = organizacion.getFechaIngreso().getYear();
        Integer mesIngreso = organizacion.getFechaIngreso().getMonthValue();

        Integer anioActual = LocalDate.now().getYear();
        Integer mesActual = LocalDate.now().getMonthValue();

        System.out.println(organizacion.getRazonSocial());
        for(Actividad actividad : organizacion.getActividades()){
            Double cantidadHC = 0.0;
            if(anioIngreso == anioActual){
                for (int mes = mesIngreso; mes <= mesActual; mes++)
                    cantidadHC += calculadorHC.cacluarHcActividad(actividad, mes, anioIngreso);
            }
            else{
                for (int mes = mesIngreso; mes <= 12; mes++)
                    cantidadHC += calculadorHC.cacluarHcActividad(actividad, mes, anioIngreso);
                for (int mes = 1; mes <= mesActual; mes++)
                    cantidadHC += calculadorHC.cacluarHcActividad(actividad, mes, anioActual);
                for (int anio = anioIngreso + 1; anio < anioActual; anio++)
                    for (int mes = 1; mes <= 12; mes++)
                        cantidadHC += calculadorHC.cacluarHcActividad(actividad, mes, anio);
            }

            System.out.println(actividad.getNombre() + ": " + cantidadHC.toString());
        }
    }

    public void reporteCompHC_SecTer(AgenteSectorial agenteSectorial){

        System.out.println(agenteSectorial.getNombre());
        for (Organizacion organizacion : agenteSectorial.getOrganizaciones()){
            this.reporteCompHC_Org(organizacion);
        }
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
