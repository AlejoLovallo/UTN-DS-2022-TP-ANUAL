package BaseDeDatos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Domain.Usuarios.Usuario;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.BaseDeDatos.EntityManagerHelper;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

public class UsuarioTest {

    @Test
    @Transactional
    @Rollback(false)
    public void usuarioSavedOnDB(){
      Usuario user = new Usuario();
      user.setEmail("asdas");
      user.setEmail("test@example.com");
      EntityManagerHelper.entityManager().persist(user);
    }

}
