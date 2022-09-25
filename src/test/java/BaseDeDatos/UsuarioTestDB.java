package BaseDeDatos;
import Domain.Usuarios.Usuario;

import javax.persistence.*;

public class UsuarioTestDB {
  public static void main(String[] args) {

    Usuario user = new Usuario("pepito","pepito@gmail.com","contraPrueba",false);
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("ds");
        System.out.println("----------------LUEGO DE PERSISTANCE-------------------");
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      System.out.println("----------------LUEGO DE BEGIN TRAN-------------------");
      em.persist(user);
      System.out.println("----------------LUEGO DE INSERT TRAN-------------------");
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
}