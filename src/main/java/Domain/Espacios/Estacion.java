package Domain.Espacios;

import Domain.BaseDeDatos.EntityManagerHelper;
import javax.persistence.*;

@Entity
@Table(name="estacion")
@PrimaryKeyJoinColumn(name="id_espacio")
public class Estacion extends Espacio {
  //@Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY) //ANTERIOR
  //@GeneratedValue(strategy = GenerationType.TABLE) //POR HERENCIA
  //private int id_estacion	;
  @Column
  private String nombre;
  @Column
  public Integer numeroDeEstacion;

  //////////////////////////////////  CONSTRUCTOR
  private Estacion(){

  }


  public Estacion(String nombre, Integer numeroDeEstacion) {
    this.nombre = nombre;
    this.numeroDeEstacion = numeroDeEstacion;
  }


  //////////////////////////////////  GETTERS

  public String getNombre() {
    return this.nombre;
  }

  public Integer getNumeroDeEstacion() {
    return this.numeroDeEstacion;
  }


  //////////////////////////////////  SETTERS

  public void setNombre(String nombre) {
    this.nombre = nombre;
    //updateEstacion();
  }

  public void setNumeroDeEstacion(Integer numeroDeEstacion) {
    this.numeroDeEstacion = numeroDeEstacion;
    //updateEstacion();
  }


  //////////////////////////////////  INTERFACE
/*
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
  }*/

}
