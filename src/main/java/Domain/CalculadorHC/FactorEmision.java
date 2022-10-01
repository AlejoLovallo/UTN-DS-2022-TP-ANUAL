package Domain.CalculadorHC;

import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.Organizacion.TipoDeActividad;
import javax.persistence.*;

@Entity
@Table(name="factoremision")
public class FactorEmision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factor")
    private int id;

    @Column
    private Double numero;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoDeActividad tipoActividad;
    @Column
    private Unidad unidad;

    // CONSTRUCTOR
    public FactorEmision(){

    }

    public FactorEmision(TipoDeActividad tipoDeActividad, Double numero, Unidad unidad) {
        this.tipoActividad = tipoDeActividad;
        this.numero = numero;
        this.unidad = unidad;
    }

    // GETTERS

    public  TipoDeActividad getTipoActividad() {
        return tipoActividad;
    }

    public Double getNumero() {
        return numero;
    }

    public Unidad getUnidad() {
        return unidad;
    }


    // SETTERS


    public void setTipoActividad(TipoDeActividad tipoActividad) {
        this.tipoActividad = tipoActividad;
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
