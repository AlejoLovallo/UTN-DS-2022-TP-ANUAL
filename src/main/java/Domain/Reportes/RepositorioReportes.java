package Domain.Reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class RepositorioReportes {

    private static RepositorioReportes instance = null;
    private ArrayList<Reporte> reportes;

    //////////////////////////////////  CONSTRUCTORES
    private RepositorioReportes(){
        this.reportes = new ArrayList<>();
    }

    public static RepositorioReportes GetInstance(){
        if(instance == null){
        instance = new RepositorioReportes();
        }
        return instance;
    }

    //////////////////////////////////  GETTERS
    
    public ArrayList<Reporte> getReportes() {
        return reportes;
    }

    //////////////////////////////////  SETTERS

    //////////////////////////////////  INTERFACE

    public void agregarReporte(Reporte reporte){
        this.reportes.add(reporte);
    }

    public Boolean existeReporte(String nombre, TipoDeReporte tipoDeReporte, LocalDate fechaDesde,  LocalDate fechaHasta){

        return this.getReportes().stream().filter(unReporte -> unReporte.getNombre().equals(nombre) && unReporte.getTipoDeReporte().equals(tipoDeReporte) && unReporte.getFechaDesde().equals(fechaDesde) && unReporte.getFechaHasta().equals(fechaHasta)).count() != 0;

    }

    public Optional<Reporte> conseguirReporte(String nombre, TipoDeReporte tipoDeReporte,LocalDate fechaDesde,  LocalDate fechaHasta){

        return this.getReportes().stream().filter(unReporte -> unReporte.getNombre().equals(nombre) && unReporte.getTipoDeReporte().equals(tipoDeReporte) && unReporte.getFechaDesde().equals(fechaDesde) && unReporte.getFechaHasta().equals(fechaHasta)).findAny();
    }
}
