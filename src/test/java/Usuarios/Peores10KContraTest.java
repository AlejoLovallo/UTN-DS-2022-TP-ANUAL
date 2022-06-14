package Usuarios;

import Domain.Usuarios.Excepciones.ArchivoInaccesibleException;
import Domain.Usuarios.Peores10KContra;
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


    //////////////////////////////////  TODO validarContraseniaExcepcion da mal porque SI lee el txt

    @Test
    public void validarContraseniaExcepcion(){
        String contraseniaTest1 = "123456";
        Assertions.assertThrows(ArchivoInaccesibleException.class,
                () -> {
                    this.peores10KContraTest.validarContrasenia(contraseniaTest1);
                } );
    }

    @Test
    public void validarContraseniaInvalida(){
        String contraseniaTest = "123456";
        Assertions.assertFalse(this.peores10KContraTest.validarContrasenia(contraseniaTest));

    }

    @Test
    public void validarContraseniaValida(){
        String contraseniaTest = "unaContraseniaReDificilDeAdivinar";
        Assertions.assertTrue(this.peores10KContraTest.validarContrasenia(contraseniaTest));

    }
}
