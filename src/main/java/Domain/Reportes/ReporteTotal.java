package Domain.Reportes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="reportetotal")
@PrimaryKeyJoinColumn(name="id_reporte")

public class ReporteTotal extends Reporte {

    @Column(name = "cantidadHC")
    private Double cantidadHC;

    // CONSTRUCTOR

    public ReporteTotal(String _nombre,TipoDeReporte _tipoDeReporte, LocalDate _fechaDesde, LocalDate _fechaHasta, Double _cantidadHC) {
        super(_nombre, _tipoDeReporte, _fechaDesde, _fechaHasta);
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
