package Domain.CalculadorHC;

import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;
import Domain.Organizacion.Organizacion;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name="resultado_hc_miembro")
public class ResultadoHCMiembro extends ResultadoHC{

    @ManyToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_miembro",referencedColumnName = "id_miembro")
    private Miembro miembro;

    public ResultadoHCMiembro(Integer mes, Integer anio, Double resultado, Miembro _miembro) {
        super(mes, anio, resultado);
        this.miembro = _miembro;
    }

    public ResultadoHCMiembro(){}

    public Miembro getMiembro() {
        return miembro;
    }
}
