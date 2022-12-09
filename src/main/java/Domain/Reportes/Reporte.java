package Domain.Reportes;

import Domain.Organizacion.AgenteSectorial;
import Domain.Organizacion.Organizacion;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reporte")
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_reporte;

    @Column
    private String nombre;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoDeReporte tipoDeReporte;

    @Column
    private LocalDate fechaDesde;

    @Column
    private LocalDate fechaHasta;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_agente",referencedColumnName = "id_agente")
    private AgenteSectorial agenteSectorial;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_organizacion",referencedColumnName = "id_organizacion")
    private Organizacion organizacion;

    // CONSTRUCTOR
    public Reporte (String _nombre, TipoDeReporte tipoDeReporte, LocalDate _fechaDesde, LocalDate _fechaHasta){
        this.nombre = _nombre;
        this.tipoDeReporte = tipoDeReporte;
        this.fechaDesde = _fechaDesde;
        this.fechaHasta = _fechaHasta;
    }

    public Reporte(){}

    // GETTERS


    public int getId_reporte() {
        return id_reporte;
    }

    public String getNombre(){
        return nombre;
    }

    public TipoDeReporte getTipoDeReporte(){
        return tipoDeReporte;
    }


    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    // SETTERS

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setTipoDeReporte(TipoDeReporte tipoDeReporte){
        this.tipoDeReporte = tipoDeReporte;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    // INTERFACE

    public abstract void mostrarReporte();
    
}
