package Domain.Organizacion;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name="recomendacion")
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_recomendacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_organizacion",referencedColumnName = "id_organizacion")
    private Organizacion organizacion;

    @Column
    private String recomendacion;

    // CONSTRUCTOR

    public  Recomendacion(){}
    public Recomendacion(Organizacion organizacion, String recomendacion) {
        this.organizacion = organizacion;
        this.recomendacion = recomendacion;
    }

    // GETTERS

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    // SETTERS

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }
}
