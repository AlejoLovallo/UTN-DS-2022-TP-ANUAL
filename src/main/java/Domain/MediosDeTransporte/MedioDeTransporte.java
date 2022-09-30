package Domain.MediosDeTransporte;

import Domain.BaseDeDatos.EntityManagerHelper;

import javax.persistence.*;

@Entity
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
public abstract class MedioDeTransporte {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //Anterior
    @GeneratedValue(strategy = GenerationType.TABLE) // ESTO ES POR LA HERENCIA
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
        updateMedioTransporte();
    }

    //INTERFACE

    public void updateMedioTransporte(){
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

    public MedioDeTransporte getMedioTransporte(int medioTransporteid) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        MedioDeTransporte medioDeTransporte = em.find(MedioDeTransporte.class, new Long(medioTransporteid));
        em.detach(medioDeTransporte);
        return medioDeTransporte;
    }

}
