package Domain.CalculadorHC;

import Domain.Organizacion.Organizacion;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "resultado_hc")
@Inheritance(strategy =  InheritanceType.JOINED)
public abstract class ResultadoHC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_resultado;

    @Column
    private Integer mes;

    @Column
    private Integer anio;

    @Column
    private Double resultado;

    public ResultadoHC(){

    }

    public ResultadoHC(Integer mes, Integer anio, Double resultado) {
        this.mes = mes;
        this.anio = anio;
        this.resultado = resultado;
    }

    public int getId_resultado() {
        return id_resultado;
    }

    public void setId_resultado(int id_resultado) {
        this.id_resultado = id_resultado;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

}
