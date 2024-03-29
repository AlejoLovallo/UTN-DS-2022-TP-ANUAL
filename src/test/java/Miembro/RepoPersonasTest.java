package Miembro;

import Domain.InterfazUsuario.InterfazUsuarioPersona;
import Domain.Miembro.Persona;
import Domain.Miembro.TipoDocumento;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Usuario;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepoPersonasTest {
    protected RepositorioPersonasDB repoPersonas;

    private void initializePersona(){
        this.repoPersonas = new RepositorioPersonasDB();
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
        Usuario user = Common.getUsuario();
        Persona unaPersona = this.repoPersonas.crearPersona("Alberto","Gonzalez",TipoDocumento.DNI,"4215789", user);
        //WHEN CUANDO
        InterfazUsuarioPersona interfazUsuarioPersona = new InterfazUsuarioPersona();
        interfazUsuarioPersona.ingresarUsuario(user.getUsername(),"juan1998");
        //THEN ENTONCES
        Assertions.assertEquals(user.getUsername(), interfazUsuarioPersona.getUsuario().getUsername());
        Assertions.assertEquals(unaPersona.getId_persona(), interfazUsuarioPersona.getPersona().getId_persona());

    }

    @Test
    public void registrarse(){
        //GIVEN DADO

        //WHEN CUANDO
        InterfazUsuarioPersona interfazUsuarioPersona = new InterfazUsuarioPersona();
        interfazUsuarioPersona.crearUsuarioPersona("Username18",  "mail@hotmail.com",  "contraseniadificil1234", "nombre" , "apellido", TipoDocumento.DNI,  "1141265R63234");

        //THEN ENTONCES
        Assertions.assertEquals("Username18", interfazUsuarioPersona.getUsuario().getUsername());
        Assertions.assertEquals("mail@hotmail.com", interfazUsuarioPersona.getUsuario().getMail());
        Assertions.assertEquals("nombre", interfazUsuarioPersona.getPersona().getNombre());
        Assertions.assertEquals("apellido", interfazUsuarioPersona.getPersona().getApellido());

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

        Assertions.assertEquals(repositorioPersonasDB.buscarPersonaPorUsuario(interfazUsuarioPersona.getUsuario()).getId_persona(), interfazUsuarioPersona.getPersona().getId_persona());
        Assertions.assertTrue(repositorioUsuariosDB.validarUsuario(interfazUsuarioPersona.getUsuario(), true));

    }
}