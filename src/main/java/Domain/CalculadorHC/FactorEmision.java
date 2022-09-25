package Domain.CalculadorHC;

import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.Espacios.Direccion;
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
        updateFactorEmision();
    }

    public void setNumero(Double numero) {
        this.numero = numero;
        updateFactorEmision();
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
        updateFactorEmision();
    }

    //INTERFACE

    public void updateFactorEmision(){
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ds");
        System.out.println("----------------LUEGO DE Crear Entity Manager-------------------");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            System.out.println("----------------LUEGO DE BEGIN TRAN-------------------");
            em.merge(this);
            System.out.println("----------------LUEGO DE UPDATE TRAN-------------------");
            em.getTransaction().commit();
            System.out.println("----------------LUEGO DE COMMIT-------------------");
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        } finally {
            em.close();
            System.out.println("----------------LUEGO DE CLOSE CON-------------------");
        }
    }

    public FactorEmision getFactorEmision(int factorID) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        FactorEmision factorEmision = em.find(FactorEmision.class, new Long(factorID));
        em.detach(factorEmision);
        return factorEmision;
    }

}
