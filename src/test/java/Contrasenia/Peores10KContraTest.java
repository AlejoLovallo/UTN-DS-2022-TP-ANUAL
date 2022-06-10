package Contrasenia;

import Domain.Contrasenia.Peores10KContra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class Peores10KContraTest {

    protected Peores10KContra peores10KContraTest;

    private void initializePeores10KContra(){
        this.peores10KContraTest = new Peores10KContra();
    }

    @BeforeEach
    public void initialize() {
        this.initializePeores10KContra();
    }

    @AfterEach
    public void clean(){

    }
    //////////////////////////////////  TODO
    /*
    @Test
    public void validarContrasenia(){
        String contraseniaTest = "123456";
        Assertions.assertThrows(ArchivoInaccesibleException,peores10KContraTest.validarContrasenia(contraseniaTest));
    }*/
}
