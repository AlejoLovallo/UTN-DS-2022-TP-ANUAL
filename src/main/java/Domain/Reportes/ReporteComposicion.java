package Domain.Reportes;

import java.time.LocalDate;
import java.util.ArrayList;

import Domain.Organizacion.Actividad;

public class ReporteComposicion extends Reporte {

    private ArrayList <Actividad> actividades;

    // CONSTRUCTOR
    public ReporteComposicion(String _nombre, TipoDeReporte _tipoDeReporte, LocalDate _fechaDesde, LocalDate _fechaHasta, ArrayList <Actividad> _actividades) {
        super(_nombre, _tipoDeReporte, _fechaDesde, _fechaHasta);
        this.actividades = _actividades;
    }


    // GETTERS

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    // SETTERS

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

    // INTERFACE

    @Override
    public void mostrarReporte() {
        
        for(Actividad actividad : this.getActividades()){
            System.out.println(actividad.getNombre() + ", " +  actividad.getTipoDeConsumo() + " = " + actividad.getConsumos().get(0).getConsumo());
        }
        

    }    
}
