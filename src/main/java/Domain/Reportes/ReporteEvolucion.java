package Domain.Reportes;

import java.time.LocalDate;
import java.util.ArrayList;

import Domain.Organizacion.Consumo;

public class ReporteEvolucion extends Reporte {

    private ArrayList <Consumo> consumos;

    // CONSTRUCTOR

    public ReporteEvolucion(LocalDate _fechaDesde, LocalDate _fechaHasta, ArrayList <Consumo> _consumos) {
        super(_fechaDesde, _fechaHasta);
        this.consumos = _consumos;
    }

    // GETTERS

    public ArrayList<Consumo> getConsumos() {
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
