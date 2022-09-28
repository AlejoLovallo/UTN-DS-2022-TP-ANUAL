package Domain.Organizacion;

public class Consumo {
    
    private Integer mes;
    private Integer anio;
    private Double consumo;
    

    // CONSTRUCTOR 

    public Consumo(Integer mes, Integer anio, Double consumo) {
        this.mes = mes;
        this.anio = anio;
        this.consumo = consumo;
    }


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
