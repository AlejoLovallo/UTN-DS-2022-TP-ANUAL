package BaseDeDatos;
import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.Usuarios.Usuario;

import javax.persistence.*;

public class UsuarioTestDB {
  public static void main(String[] args) {

    Usuario user = new Usuario("pepito","pepito@gmail.com","contraPrueba",false);

    try {
      EntityManagerHelper.beginTransaction();
      System.out.println("----------------LUEGO DE BEGIN TRAN-------------------");
      EntityManagerHelper.getEntityManager().persist(user);
      System.out.println("----------------LUEGO DE INSERT TRAN-------------------");
      EntityManagerHelper.commit();
      System.out.println("----------------LUEGO DE COMMIT-------------------");

    } catch (Exception e) {
      e.getCause();
      e.printStackTrace();
    } finally {
      EntityManagerHelper.closeEntityManager();
      System.out.println("----------------LUEGO DE CLOSE CON-------------------");
    }
  }
}