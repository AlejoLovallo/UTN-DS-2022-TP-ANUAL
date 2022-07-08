package Domain.CalculadorHC;

public class FactorEmision {

    private TipoDeActividad tipoDeActividad;

    private Double numero;

    private Unidad unidad;

    // CONSTRUCTOR

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
