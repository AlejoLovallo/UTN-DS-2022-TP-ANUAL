package Miembro;

import Domain.InterfazUsuario.InterfazUsuarioPersona;
import Domain.Miembro.Persona;
import Domain.Miembro.RepositorioPersonas;
import Domain.Miembro.TipoDocumento;
import Domain.Usuarios.RepositorioUsuarios;
import Domain.Usuarios.Usuario;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepoPersonasTest {
    protected RepositorioPersonas repoPersonas;

    private void initializePersona(){
        this.repoPersonas = RepositorioPersonas.getInstance();
    }

    @BeforeEach
    public void initialize(){
        initializePersona();
    }

    @AfterEach
    public void clean(){}

    //probar cuando se traiga todos los users de la DB
/*    @Test
    public void buscarPersonaxUser(){
        Usuario user= Common.getUsuario();

        Assertions.assertEquals("UNA PERSONA",this.repoPersonas.buscarPersonaPorUsuario(user));
    }*/

    @Test
    public void crearPersona(){
        Assertions.assertNotNull(this.repoPersonas.crearPersona("Alberto","Gonzalez",TipoDocumento.DNI,"123456", Common.getUsuario()));
    }

    @Test
    public void loguearse(){
        //GIVEN DADO
        Usuario user = Common.getUsuarioQueEsteEnElRepo();
        Persona unaPersona = this.repoPersonas.crearPersona("Alberto","Gonzalez",TipoDocumento.DNI,"4215789", user);
        //WHEN CUANDO
        InterfazUsuarioPersona interfazUsuarioPersona = new InterfazUsuarioPersona();
        interfazUsuarioPersona.ingresarUsuario(user.getUsername(),"juan1998");
        //THEN ENTONCES
        Assertions.assertEquals(user, interfazUsuarioPersona.getUsuario());
        Assertions.assertEquals(unaPersona, interfazUsuarioPersona.getPersona());

    }

    @Test
    public void registrarse(){
        //GIVEN DADO

        //WHEN CUANDO
        InterfazUsuarioPersona interfazUsuarioPersona = new InterfazUsuarioPersona();
        interfazUsuarioPersona.crearUsuarioPersona("username",  "mail",  "contraseniadificil1234", "nombre" , "apellido", TipoDocumento.DNI,  "1112324");

        //THEN ENTONCES
        Assertions.assertEquals("username", interfazUsuarioPersona.getUsuario().getUsername());
<<<<<<< HEAD
        Assertions.assertEquals("mail", interfazUsuarioPersona.getUsuario().getEmail());
=======
        Assertions.assertEquals("mail", interfazUsuarioPersona.getUsuario().getMail());
>>>>>>> develop
        Assertions.assertEquals("nombre", interfazUsuarioPersona.getPersona().getNombre());
        Assertions.assertEquals("apellido", interfazUsuarioPersona.getPersona().getApellido());

        Assertions.assertEquals(RepositorioPersonas.getInstance().buscarPersonaPorUsuario(interfazUsuarioPersona.getUsuario()), interfazUsuarioPersona.getPersona());
        Assertions.assertTrue(RepositorioUsuarios.getInstance().validarUsuario(interfazUsuarioPersona.getUsuario(), true));

    }
}
