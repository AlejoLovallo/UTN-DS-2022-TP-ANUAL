package Domain.Espacios;

import Domain.BaseDeDatos.EntityManagerHelper;
import javax.persistence.*;

@Entity
@Table(name="estacion")
public class Estacion extends Espacio {
  
  @Column
  private String nombre;

  //////////////////////////////////  CONSTRUCTOR
  public Estacion(){

  }


  public Estacion(String nombre, Integer numeroDeEstacion) {
    this.nombre = nombre;
  }


  //////////////////////////////////  GETTERS

  public String getNombre() {
    return this.nombre;
  }


  //////////////////////////////////  SETTERS

  public void setNombre(String nombre) {
    this.nombre = nombre;
    updateEstacion();
  }

  //////////////////////////////////  INTERFACE

  public void updateEstacion(){
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

  public Estacion getEstacion(int direccionid) {
    EntityManager em = EntityManagerHelper.getEntityManager();
    Estacion estacion = em.find(Estacion.class, new Long(direccionid));
    em.detach(estacion);
    return estacion;
  }

}
