package Domain.Reportes;

import Domain.CalculadorHC.CalculadorHC;
import Domain.Organizacion.*;
import Domain.Organizacion.Actividad;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class GeneradorReportes {

    private RepositorioOrganizaciones repositorioOrganizaciones = RepositorioOrganizaciones.GetInstance();
    private RepositorioReportes repositorioReportes = RepositorioReportes.GetInstance();
    private CalculadorHC calculadorHC = CalculadorHC.getInstance();
    
    private static GeneradorReportes instance = null;

    // CONSTRUCTOR
    public static GeneradorReportes getInstance(){
        if(instance == null){
            instance = new GeneradorReportes();
        }
        return instance;
    }

    // INTERFACE

    public Reporte reporteHC_Org(TipoOrganizacion tipo, LocalDate fechaDesde, LocalDate fechaHasta) throws IOException {

        if (repositorioReportes.existeReporte(tipo.toString(),TipoDeReporte.TOTAL, fechaDesde, fechaHasta)){
            Optional <Reporte> rep = repositorioReportes.conseguirReporte(tipo.toString(),TipoDeReporte.TOTAL, fechaDesde, fechaHasta);
            return rep.get();
        }else{

            Double cantidadHC = 0.0;

            for (Organizacion organizacion : repositorioOrganizaciones.getOrganizaciones()){
                if (organizacion.getTipo() == tipo){
                    calculadorHC.calcularHcPeriodo(organizacion, fechaDesde, fechaHasta);
                }
            }

            ReporteTotal reporte = new ReporteTotal(tipo.toString(), TipoDeReporte.TOTAL, fechaDesde, fechaHasta, cantidadHC);
            repositorioReportes.getReportes().add(reporte);
            return reporte;
        }

    }

    public Reporte reporteHC_SecTer(AgenteSectorial agenteSectorial, LocalDate fechaDesde, LocalDate fechaHasta) throws IOException {
        
        if (repositorioReportes.existeReporte(agenteSectorial.getNombre(), TipoDeReporte.TOTAL, fechaDesde, fechaHasta)){
            Optional <Reporte> rep = repositorioReportes.conseguirReporte(agenteSectorial.getNombre(), TipoDeReporte.TOTAL, fechaDesde, fechaHasta);
            return rep.get();
        }else{
            Double cantidadHC = 0.0;
            for (Organizacion organizacion : agenteSectorial.getOrganizaciones()){
                cantidadHC += calculadorHC.calcularHcPeriodo(organizacion, fechaDesde, fechaHasta);
            }
    
            ReporteTotal reporte = new ReporteTotal(agenteSectorial.getNombre(), TipoDeReporte.TOTAL, fechaDesde, fechaHasta, cantidadHC);
            repositorioReportes.getReportes().add(reporte);
            return reporte;
        }
        

        
    }

    public Reporte reporteCompHC_Org(Organizacion organizacion, LocalDate fechaDesde, LocalDate fechaHasta){

        if (repositorioReportes.existeReporte(organizacion.getRazonSocial(), TipoDeReporte.COMPOSICION, fechaDesde, fechaHasta)){
            Optional <Reporte> rep = repositorioReportes.conseguirReporte(organizacion.getRazonSocial(), TipoDeReporte.COMPOSICION, fechaDesde, fechaHasta);
            return rep.get();
        }else{

            ArrayList <Actividad> listaActividades = new ArrayList<>();
            ReporteComposicion reporte = new ReporteComposicion(organizacion.getRazonSocial(), TipoDeReporte.COMPOSICION, fechaDesde, fechaHasta, listaActividades);

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

            repositorioReportes.getReportes().add(reporte);

            return reporte;
        }
        
    }

    // Me quede acá. Despues sigo
    public Reporte reporteCompHC_SecTer(AgenteSectorial agenteSectorial, LocalDate fechaDesde, LocalDate fechaHasta){

        if (repositorioReportes.existeReporte(agenteSectorial.getNombre(), TipoDeReporte.COMPOSICION, fechaDesde, fechaHasta)){
            Optional <Reporte> rep = repositorioReportes.conseguirReporte(agenteSectorial.getNombre(), TipoDeReporte.COMPOSICION, fechaDesde, fechaHasta);
            return rep.get();
        }else{
            ArrayList <Actividad> listaActividades = new ArrayList<>();
            for( Organizacion organizacion : agenteSectorial.getOrganizaciones()){
                for(Actividad actividad : organizacion.getActividades()){
                    Double cantidadHC = 0.0;
                    if(listaActividades.stream().filter(unaActividad -> unaActividad.getNombre().equals(actividad.getNombre())).count() == 0){
                        Actividad act = new Actividad(actividad.getNombre(), actividad.getTipoDeConsumo());
                        cantidadHC += calculadorHC.cacluarHcActividadPeriodo(actividad, fechaDesde, fechaHasta);
                        Consumo con = new Consumo(1, 1, cantidadHC);
                        ArrayList <Consumo> lista = new ArrayList<>();
                        lista.add(con);
                        act.setConsumos(lista);
                        listaActividades.add(act);
                    }
                    else{
                        Optional<Actividad> opActividad = listaActividades.stream().filter(unaActividad -> unaActividad.getNombre().equals(actividad.getNombre())).findAny();
                        Actividad act = opActividad.get();
                        Double nuevoHC = act.getConsumos().get(0).getConsumo() + calculadorHC.cacluarHcActividadPeriodo(actividad, fechaDesde, fechaHasta);
                        act.getConsumos().get(0).setConsumo(nuevoHC);
                    }
                }
            }
            ReporteComposicion reporte = new ReporteComposicion(agenteSectorial.getNombre(), TipoDeReporte.COMPOSICION, fechaDesde, fechaHasta, listaActividades);
            repositorioReportes.getReportes().add(reporte);
            return reporte;
        }
        
    }

    public Reporte reporteCompHC_Pais(String pais, LocalDate fechaDesde, LocalDate fechaHasta){

        if (repositorioReportes.existeReporte(pais, TipoDeReporte.COMPOSICION, fechaDesde, fechaHasta)){
            Optional <Reporte> rep = repositorioReportes.conseguirReporte(pais, TipoDeReporte.COMPOSICION, fechaDesde, fechaHasta);
            return rep.get();
        }else{

            RepositorioOrganizaciones repositorioOrganizaciones = RepositorioOrganizaciones.GetInstance();

            ArrayList<Actividad> listaActividades = new ArrayList<>();
            for(Organizacion organizacion : repositorioOrganizaciones.getOrganizaciones()){
                if(organizacion.getPaisOrigen().equals(pais))
                {
                    ReporteComposicion aux = (ReporteComposicion)this.reporteCompHC_Org(organizacion, fechaDesde, fechaHasta);
                    for(Actividad actividad : aux.getActividades()){
                        Double cantidadHC = 0.0;
                        if(listaActividades.stream().filter(unaActividad -> unaActividad.getNombre().equals(actividad.getNombre())).count() == 0){
                            Actividad act = new Actividad(actividad.getNombre(), actividad.getTipoDeConsumo());
                            cantidadHC += calculadorHC.cacluarHcActividadPeriodo(actividad, fechaDesde, fechaHasta);
                            Consumo con = new Consumo(1, 1, cantidadHC);
                            ArrayList <Consumo> lista = new ArrayList<>();
                            lista.add(con);
                            act.setConsumos(lista);
                            listaActividades.add(act);
                        }
                        else{
                            Optional<Actividad> opActividad = listaActividades.stream().filter(unaActividad -> unaActividad.getNombre().equals(actividad.getNombre())).findAny();
                            Actividad act = opActividad.get();
                            Double nuevoHC = act.getConsumos().get(0).getConsumo() + act.getConsumos().get(0).getConsumo();
                            act.getConsumos().get(0).setConsumo(nuevoHC);
                        }
                    }
                }

            }
            ReporteComposicion reporte = new ReporteComposicion(pais, TipoDeReporte.COMPOSICION, fechaDesde, fechaHasta, listaActividades);
            repositorioReportes.getReportes().add(reporte);
            return reporte;
        }

        
    }

    public Reporte reporteEvolucionHC_Org(Organizacion organizacion, LocalDate fechaDesde, LocalDate fechaHasta) throws IOException {

        if (repositorioReportes.existeReporte(organizacion.getRazonSocial(), TipoDeReporte.EVOLUCION, fechaDesde, fechaHasta)){
            Optional <Reporte> rep = repositorioReportes.conseguirReporte(organizacion.getRazonSocial(), TipoDeReporte.EVOLUCION, fechaDesde, fechaHasta);
            return rep.get();
        }else{

        }
        
        Integer mesDesde = fechaDesde.getMonthValue();
        Integer anioDesde = fechaDesde.getYear();
        Integer mesHasta = fechaHasta.getMonthValue();
        Integer anioHasta = fechaHasta.getYear();

        ArrayList<Consumo> evolucionHC = new ArrayList<>();
        if(anioDesde == anioHasta){
            for (int mes = mesDesde; mes <= mesHasta; mes++){
                Consumo consumo = new Consumo(mes, anioDesde, calculadorHC.calcularHC(organizacion, mes, anioDesde));
                evolucionHC.add(consumo);
            }
        }
        else{
            for (int mes = mesDesde; mes <= 12; mes++){
                Consumo consumo = new Consumo(mes, anioDesde, calculadorHC.calcularHC(organizacion, mes, anioDesde));
                evolucionHC.add(consumo);
            }
            for (int anio = anioDesde + 1; anio < anioHasta; anio++)
                for (int mes = 1; mes <= 12; mes++){
                    Consumo consumo = new Consumo(mes, anio, calculadorHC.calcularHC(organizacion, mes, anio));
                    evolucionHC.add(consumo);
                }
            for (int mes = 1; mes <= mesHasta; mes++){
                Consumo consumo = new Consumo(mes, anioHasta, calculadorHC.calcularHC(organizacion, mes, anioHasta));
                evolucionHC.add(consumo);
            }
        }
        ReporteEvolucion reporte = new ReporteEvolucion(organizacion.getRazonSocial(), TipoDeReporte.EVOLUCION, fechaDesde, fechaHasta, evolucionHC);
        repositorioReportes.getReportes().add(reporte);
        return reporte;
    }

    public Reporte reporteEvolucionHC_SecTer(AgenteSectorial agenteSectorial, LocalDate fechaDesde, LocalDate fechaHasta) throws IOException {
        
        if (repositorioReportes.existeReporte(agenteSectorial.getNombre(), TipoDeReporte.EVOLUCION, fechaDesde, fechaHasta)){
            Optional <Reporte> rep = repositorioReportes.conseguirReporte(agenteSectorial.getNombre(), TipoDeReporte.EVOLUCION, fechaDesde, fechaHasta);
            return rep.get();
        }else{

            ArrayList<Consumo> listaConsumos = new ArrayList<>();
            for(Organizacion organizacion : agenteSectorial.getOrganizaciones()){
                ReporteEvolucion aux = (ReporteEvolucion) this.reporteEvolucionHC_Org(organizacion, fechaDesde, fechaHasta);
                for(Consumo consumo : aux.getConsumos()){
                    if(listaConsumos.stream().filter(unConsumo ->
                            unConsumo.getAnio() == consumo.getAnio() && unConsumo.getMes() == consumo.getMes()
                    ).count() == 0){
                        listaConsumos.add(consumo);
                    }
                    else{
                        Optional<Consumo> opConsumo = listaConsumos.stream().filter(unConsumo ->
                                unConsumo.getAnio() == consumo.getAnio() && unConsumo.getMes() == consumo.getMes()
                        ).findAny();

                        opConsumo.get().setConsumo(opConsumo.get().getConsumo() + consumo.getConsumo());
                    }
                }
            }
            ReporteEvolucion reporte = new ReporteEvolucion(agenteSectorial.getNombre(), TipoDeReporte.EVOLUCION, fechaDesde, fechaHasta, listaConsumos);
            repositorioReportes.getReportes().add(reporte);
            return reporte;
        }
        
    }
}
