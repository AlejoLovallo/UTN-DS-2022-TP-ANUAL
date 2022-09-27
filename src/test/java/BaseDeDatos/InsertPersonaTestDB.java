package BaseDeDatos;
import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;
import Domain.Miembro.TipoDocumento;
import Domain.Usuarios.Usuario;

public class InsertPersonaTestDB {
  public static void main(String[] args) {

    Usuario user = new Usuario("pepitoGamer","pepito@gmail.com","contraSegura54+",true);

    Persona persona = new Persona("pepe", "Sin Apellido", TipoDocumento.DNI, "101469451");

    //Miembro miembro = new Miembro();

    persona.setUsuario(user);

    try {
      EntityManagerHelper.beginTransaction();
      System.out.println("----------------LUEGO DE BEGIN TRAN-------------------");
      EntityManagerHelper.getEntityManager().persist(user);
      EntityManagerHelper.getEntityManager().persist(persona);
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