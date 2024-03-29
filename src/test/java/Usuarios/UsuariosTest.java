package Usuarios;


import Domain.Miembro.Excepciones.UnicoSectorPorOrganizacionException;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Admin;
import Domain.Usuarios.Excepciones.ContraseniaEsInvalidaException;
import Domain.Usuarios.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuariosTest {

    protected Usuario usuariosTest;
    protected String username = "UsuarioEjemplo";
    protected String email = "mail@ejemplo.com";
    protected String contra = "calle474palabrarandompuertapared";
    protected boolean validado = true;
    protected RepositorioUsuariosDB repositorioUsuariosTest;
    protected Admin admin;

    private void initializeUsuario(){
        this.usuariosTest = new Usuario(username, email, contra, validado);

    }

    private void initializeRepoUsuario(){
        this.repositorioUsuariosTest = new RepositorioUsuariosDB();

    }

    private void initializeAdmin(){
        this.admin = new Admin(username, email, contra, validado);

    }


    @BeforeEach
    public void initialize() {
        this.initializeUsuario();
        this.initializeRepoUsuario();
        this.initializeAdmin();
    }

    @AfterEach
    public void clean(){

    }


    @Test
    public void usuarioCreadoCorrectamente() {
        Assertions.assertEquals(username,this.usuariosTest.getUsername());
        Assertions.assertEquals(email, this.usuariosTest.getMail());
        Assertions.assertEquals(validado, this.usuariosTest.isValido());
    }

    @Test
    public void usuarioCreadoIncorrectamente_ContraseniaCorta() {
        Assertions.assertThrows(ContraseniaEsInvalidaException.class,
            () -> {
                new Usuario("usuario", null,"mate", true);
            } );
    }

    @Test
    public void usuarioCreadoIncorrectamente_ContraseniaLarga() {
        Assertions.assertThrows(ContraseniaEsInvalidaException.class,
            () -> {
                new Usuario("usuario", null,"qwertyuiopasdfghjklñzxcvbnmqwertyuiopasdfghjklñzxcvbnmqwertyuiopasdfghjklñzxcvbnmqwertyuiopasdfghjklñzxcvbnm", true);
            } );
    }

    @Test
    public void usuarioCreadoIncorrectamente_Contrasenia10k() {
        Assertions.assertThrows(ContraseniaEsInvalidaException.class,
                () -> {
                    new Usuario("usuario", null, "1234567890", true);
                } );
    }

    @Test
    public void setValidado(){
        //GIVEN DADO
        Boolean validadoActual = this.usuariosTest.isValido();
        Boolean nuevoValidado = false;
        //WHEN CUANDO
        this.usuariosTest.setValidado(nuevoValidado);
        //THEN ENTONCES
        Assertions.assertEquals(validadoActual,true);
        Assertions.assertEquals(nuevoValidado,this.usuariosTest.isValido());
    }



    @Test
    public void crearUsuario(){

        this.repositorioUsuariosTest.crearUsuario(username, email, contra, validado);

        Assertions.assertEquals(username,this.usuariosTest.getUsername());
        Assertions.assertEquals(email, this.usuariosTest.getMail());
        //Assertions.assertEquals(contra, this.usuariosTest.getMedioTransporte());
        //Assertions.assertEquals(validado, this.usuariosTest.get());
    }


    @Test
    public void iniciarSesion_ContraseniaInvalida() {
        Assertions.assertThrows(ContraseniaEsInvalidaException.class,
                () -> {
                    this.repositorioUsuariosTest.validarLogueoUsuario(username,"1234");
                });

    }

    @Test
    public void iniciarSesion_UsuarioInvalido(){
        Assertions.assertNull(this.repositorioUsuariosTest.validarLogueoUsuario("usuarioInvalido","contrartghy6"));
    }

    @Test
    public void crearAdmin() {
        Admin adminTest = repositorioUsuariosTest.crearAdmin("admin", "admin@gmail.com","contrartghy6");
        Assertions.assertEquals("admin",adminTest.getUsername());
        Assertions.assertEquals("admin@gmail.com", adminTest.getMail());
    }

    @Test
    public void adminCreadoCorrectamente() {
        Assertions.assertEquals(username,this.admin.getUsername());
        Assertions.assertEquals(email, this.admin.getMail());
        Assertions.assertEquals(validado, this.admin.isValido());
    }







}
