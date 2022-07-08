package Domain.CalculadorHC;

public class FactorEmision {

    private TipoDeFactor tipoDeFactor;

    private Double numero;

    private Unidad unidad;

    // CONSTRUCTOR

    public FactorEmision(TipoDeFactor tipoDeFactor, Double numero, Unidad unidad) {
        this.tipoDeFactor = tipoDeFactor;
        this.numero = numero;
        this.unidad = unidad;
    }


    // GETTERS

    public TipoDeFactor getTipoDeFactor() {
        return tipoDeFactor;
    }

    public Double getNumero() {
        return numero;
    }

    public Unidad getUnidad() {
        return unidad;
    }


    // SETTERS

    public void setTipoDeFactor(TipoDeFactor tipoDeFactor) {
        this.tipoDeFactor = tipoDeFactor;
    }

    public void setNumero(Double numero) {
        this.numero = numero;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }
}
