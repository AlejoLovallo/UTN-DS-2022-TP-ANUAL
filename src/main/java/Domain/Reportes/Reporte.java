package Domain.Reportes;

import java.time.LocalDate;


public abstract class Reporte {
    
    private String nombre;
    private TipoDeReporte tipoDeReporte;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;

    // CONSTRUCTOR
    public Reporte (String _nombre, TipoDeReporte tipoDeReporte, LocalDate _fechaDesde, LocalDate _fechaHasta){
        this.nombre = _nombre;
        this.tipoDeReporte = tipoDeReporte;
        this.fechaDesde = _fechaDesde;
        this.fechaHasta = _fechaHasta;
    }

    // GETTERS

    public String getNombre(){
        return nombre;
    }

    public TipoDeReporte getTipoDeReporte(){
        return tipoDeReporte;
    }


    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    // SETTERS

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setTipoDeReporte(TipoDeReporte tipoDeReporte){
        this.tipoDeReporte = tipoDeReporte;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    // INTERFACE

    public abstract void mostrarReporte();
    
}
