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

    user.insert();

    EntityManagerHelper.tranPersist(user);
    EntityManagerHelper.tranPersist(persona);
  }
}