package Administrador;

import Domain.CalculadorHC.FactorEmision;
import Domain.Repositorios.RepositorioFactoresEmisionDB;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Admin;
import Domain.Usuarios.Usuario;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        Assertions.assertEquals(email, this.admin.getMail());
        Assertions.assertEquals(validado, this.admin.isValido());
    }


    @Test
    public void validarUsuario(){
        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

        repositorioUsuariosDB.crearUsuario("u", "email@gmail.com",  "juan1998", false);
        Usuario usuario = repositorioUsuariosDB.buscarUsuario("username");
        Assertions.assertTrue(admin.validarUsuario(usuario, true));
    }

    @Test
    public void registrarNuevoFE(){

        RepositorioFactoresEmisionDB repositorioFactoresEmisionDB = new RepositorioFactoresEmisionDB();

        //GIVEN DADO
        ArrayList<FactorEmision> nuevosFactoresDeEmision = new ArrayList<FactorEmision>(repositorioFactoresEmisionDB.getFactoresDeEmision());

        nuevosFactoresDeEmision.add(Common.getFactorDeEmision());
        //WHEN CUANDO
        admin.registrarFactorDeEmision(Common.getFactorDeEmision());
        ArrayList<FactorEmision>  factoresDeEmision = new ArrayList<FactorEmision>(repositorioFactoresEmisionDB.getFactoresDeEmision());
        //THEN ENTONCES
        //TODO Verificar los ids de los elementos de las 2 listas
        Assertions.assertEquals(nuevosFactoresDeEmision, factoresDeEmision);
    }

    @Test
    public void cambiarValoresDeFE(){

        RepositorioFactoresEmisionDB repositorioFactoresEmisionDB = new RepositorioFactoresEmisionDB();

        //GIVEN DADO
        FactorEmision factorEmision = Common.getFactorDeEmision();
        ArrayList<FactorEmision> factoresDeEmision =  new ArrayList<FactorEmision>(repositorioFactoresEmisionDB.getFactoresDeEmision());
        ArrayList<FactorEmision> nuevosFactoresDeEmision = factoresDeEmision;
        nuevosFactoresDeEmision.add(factorEmision);
        Double numeroAnterior = factorEmision.getNumero();
        //WHEN CUANDO
        admin.registrarFactorDeEmision(factorEmision);
        admin.cambiarFactorDeEmision(factorEmision, 3.0);
        //THEN ENTONCES
        Assertions.assertEquals(numeroAnterior, 2.0);
        Assertions.assertEquals(repositorioFactoresEmisionDB.getFactorDeEmision(factorEmision).getNumero(), 3.0);
    }
}
