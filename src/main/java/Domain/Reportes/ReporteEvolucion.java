package Domain.Reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Domain.Organizacion.Consumo;

import javax.persistence.*;

@Entity
@Table(name="reporteevolucion")
@PrimaryKeyJoinColumn(name="id_reporte")

public class ReporteEvolucion extends Reporte {

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "reporteevolucion_consumo",
        joinColumns = { @JoinColumn(name = "id_reporte") },
        inverseJoinColumns = { @JoinColumn(name = "id_consumo") }
    )
    private List<Consumo> consumos;

    // CONSTRUCTOR

    public ReporteEvolucion(String _nombre, TipoDeReporte _tipoDeReporte, LocalDate _fechaDesde, LocalDate _fechaHasta, ArrayList <Consumo> _consumos) {
        super(_nombre, _tipoDeReporte, _fechaDesde, _fechaHasta);
        this.consumos = _consumos;
    }

    private ReporteEvolucion(){}

    // GETTERS

    public List<Consumo> getConsumos() {
        return consumos;
    }

    // SETTERS

    public void setConsumos(ArrayList<Consumo> consumos) {
        this.consumos = consumos;
    }

    // INTERFACE

    @Override
    public void mostrarReporte() {
        
        for(Consumo consumo : this.getConsumos()){
            System.out.println(consumo.getMes() + " - " + consumo.getAnio() + " = " + consumo.getConsumo());
        }
        
    }

    

    
    
}
