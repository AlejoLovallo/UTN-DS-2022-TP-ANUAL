package Domain.Reportes;

import java.time.LocalDate;

public class ReporteTotal extends Reporte {

    private Double cantidadHC;

    // CONSTRUCTOR

    public ReporteTotal(LocalDate _fechaDesde, LocalDate _fechaHasta, Double _cantidadHC) {
        super(_fechaDesde, _fechaHasta);
        this.cantidadHC = _cantidadHC;
    }


    // GETTERS

    public Double getCantidadHC() {
        return cantidadHC;
    }


    // SETTERS

    public void setCantidadHC(Double cantidadHC) {
        this.cantidadHC = cantidadHC;
    }

    // INTERFACE

    @Override
    public void mostrarReporte (){
        System.out.println("El HC del período es = " + cantidadHC.toString());
    }

    
}
