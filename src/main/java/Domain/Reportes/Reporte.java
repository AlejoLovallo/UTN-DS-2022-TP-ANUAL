package Domain.Reportes;

import java.time.LocalDate;
import java.util.ArrayList;

import Domain.Organizacion.Consumo;

public abstract class Reporte {
    
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;

    // CONSTRUCTOR
    public Reporte (LocalDate _fechaDesde, LocalDate _fechaHasta){
        this.fechaDesde = _fechaDesde;
        this.fechaHasta = _fechaHasta;
    }

    // GETTERS

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    // SETTERS

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    // INTERFACE

    public abstract void mostrarReporte();
    
}
