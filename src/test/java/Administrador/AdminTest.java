package Administrador;

import Domain.Usuarios.Admin;
import Domain.Usuarios.RepositorioUsuarios;
import Domain.Usuarios.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminTest {

    protected String username = "UsuarioEjemplo";
    protected String email = "mail@ejemplo.com";
    protected String contra = "calle474palabrarandompuertapared";
    protected boolean validado = true;

    protected Admin admin;
    private void initializeAdmin(){
        this.admin = new Admin(username, email, contra, validado);

    }

    @BeforeEach
    public void initialize() {
        this.initializeAdmin();
    }

    @AfterEach
    public void clean(){

    }

    @Test
    public void adminCreadoCorrectamente() {
        Assertions.assertEquals(username,this.admin.getUsername());
        Assertions.assertEquals(email, this.admin.getEmail());
        Assertions.assertEquals(validado, this.admin.isValido());
    }


    @Test
    public void validarUsuario(){
        Usuario usuario = RepositorioUsuarios.getInstance().crearUsuario("username", "email",  "juan1998", true);
        Assertions.assertTrue(admin.validarUsuario(usuario, true));
    }
}
