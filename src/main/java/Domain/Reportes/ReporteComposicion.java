package Domain.Reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Domain.Organizacion.Actividad;

import javax.persistence.*;

@Entity
@Table(name="reportecomposicion")
@PrimaryKeyJoinColumn(name="id_reporte")

public class ReporteComposicion extends Reporte {


    @ManyToMany(cascade =  CascadeType.ALL  , fetch = FetchType.LAZY)
    @JoinTable(
        name = "reportecomposicion_actividad",
        joinColumns = { @JoinColumn(name = "id_reporte") },
        inverseJoinColumns = { @JoinColumn(name = "id_actividad") }
    )
    private List<Actividad> actividades;

    // CONSTRUCTOR
    public ReporteComposicion(String _nombre, TipoDeReporte _tipoDeReporte, LocalDate _fechaDesde, LocalDate _fechaHasta, List <Actividad> _actividades) {
        super(_nombre, _tipoDeReporte, _fechaDesde, _fechaHasta);
        this.actividades = _actividades;
    }

    private ReporteComposicion(){

    }

    // GETTERS

    public List<Actividad> getActividades() {
        return actividades;
    }

    // SETTERS

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    // INTERFACE

    @Override
    public void mostrarReporte() {
        
        for(Actividad actividad : this.getActividades()){
            System.out.println(actividad.getNombre() + ", " +  actividad.getTipoConsumo() + " = " + actividad.getConsumos().get(0).getConsumo());
        }
        

    }    
}
