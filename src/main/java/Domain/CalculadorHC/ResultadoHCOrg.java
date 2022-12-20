package Domain.CalculadorHC;

import Domain.Organizacion.Organizacion;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name="resultado_hc_organizacion")
public class ResultadoHCOrg extends ResultadoHC{


    @ManyToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_organizacion",referencedColumnName = "id_organizacion")
    private Organizacion organizacion;

    public ResultadoHCOrg(Integer mes, Integer anio, Double resultado, Organizacion _organizacion) {
        super(mes, anio, resultado);
        this.organizacion = _organizacion;
    }

    public ResultadoHCOrg(){
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }
}
