package Domain.Usuarios;
import javax.persistence.*;

public class UsuarioTestDB {
  public static void main(String[] args) {

    Usuario user = new Usuario();
    user.setEmail("asdas@gmail.com");
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("ds");
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(user);
      em.getTransaction().commit();
    } catch (Exception e) {
      e.getCause();
      e.printStackTrace();
    } finally {
      em.close();

    }
  }
}