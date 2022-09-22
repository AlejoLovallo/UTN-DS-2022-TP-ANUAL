package Domain.CalculadorHC;

import Domain.ServicioMedicion.TipoDeActividad;
import javax.persistence.*;

@Entity
@Table(name="factoremision")
public class FactorEmision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Double numero;

    @Transient
    private TipoDeActividad tipoDeActividad;
    @Transient
    private Unidad unidad;

    // CONSTRUCTOR
    public FactorEmision(){

    }

    public FactorEmision(TipoDeActividad tipoDeActividad, Double numero, Unidad unidad) {
        this.tipoDeActividad = tipoDeActividad;
        this.numero = numero;
        this.unidad = unidad;
    }

    // GETTERS

    public  TipoDeActividad getTipoDeActividad() {
        return tipoDeActividad;
    }

    public Double getNumero() {
        return numero;
    }

    public Unidad getUnidad() {
        return unidad;
    }


    // SETTERS


    public void setTipoDeActividad(TipoDeActividad tipoDeActividad) {
        this.tipoDeActividad = tipoDeActividad;
    }

    public void setNumero(Double numero) {
        this.numero = numero;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }
}
