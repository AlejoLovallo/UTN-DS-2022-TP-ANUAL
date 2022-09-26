package BaseDeDatos;
import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.Usuarios.Usuario;

import javax.persistence.*;

public class UpdateUsuarioTestDB {
  public static void main(String[] args) {

    Usuario user = Usuario.getUsuario(2);
    user.setUsername("tomas");

    try {
      EntityManagerHelper.beginTransaction();
      System.out.println("----------------LUEGO DE BEGIN TRAN-------------------");
      EntityManagerHelper.getEntityManager().merge(user);
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