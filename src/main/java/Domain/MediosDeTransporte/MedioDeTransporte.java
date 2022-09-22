package Domain.MediosDeTransporte;

import javax.persistence.*;

@Entity
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
public abstract class MedioDeTransporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_transporte;

    @Column
    private Double consumoPorKm;

    // CONSTRUCTOR



    // GETTER

    public Double getConsumoPorKm() {
        return consumoPorKm;
    }

    // SETTER

    public void setConsumoPorKm(Double consumoPorKm) {
        this.consumoPorKm = consumoPorKm;
    }
}
