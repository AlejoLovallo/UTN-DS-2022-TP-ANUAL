package Domain.Organizacion;

import Domain.Reportes.ReporteEvolucion;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "consumo")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_consumo;

    @Column
    private Integer mes;

    @Column
    private Integer anio;

    @Column
    private Double consumo;

    @ManyToOne
    @JoinColumn(name="id_actividad", referencedColumnName = "id_actividad")
    private Actividad actividad;

    @ManyToMany(mappedBy = "consumos")
    private List<ReporteEvolucion> reporteEvolucion;
    

    // CONSTRUCTOR 

    public Consumo(Integer mes, Integer anio, Double consumo) {
        this.mes = mes;
        this.anio = anio;
        this.consumo = consumo;
    }

    private Consumo(){}

    // GETTERS

    public Integer getMes() {
        return mes;
    }


    public Integer getAnio() {
        return anio;
    }


    public Double getConsumo() {
        return consumo;
    }
    

    // SETTERS

    
    public void setMes(Integer mes) {
        this.mes = mes;
    }


    public void setAnio(Integer anio) {
        this.anio = anio;
    }


    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }
    
}
