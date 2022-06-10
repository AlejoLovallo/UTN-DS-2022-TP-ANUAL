package Contrasenia;

import Domain.Contrasenia.CriterioLongitud;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CriterioLongitudTest {
    protected CriterioLongitud criterioLongitudTest;
    protected int minimo = 8;
    protected int maximo = 60;

    private void initializeCriterioLongitud(){
        this.criterioLongitudTest = new CriterioLongitud(minimo, maximo);
    }


    @BeforeEach
    public void initialize() {
        this.initializeCriterioLongitud();
    }

    @AfterEach
    public void clean(){

    }


    @Test
    public void criterioLongitudCorrectamente() {
        Assertions.assertEquals(minimo,this.criterioLongitudTest.getMinimo());
        Assertions.assertEquals(maximo, this.criterioLongitudTest.getMaximo());
    }

    @Test
    public void validarContrasenia(){
        String contraseniaTest = "contraseniaEntreOchoYSesentaCaracteres";
        Assertions.assertTrue(criterioLongitudTest.validarContrasenia(contraseniaTest));
    }

    @Test
    public void validarContraseniaLarga(){
        String contraseniaTest = "contraseniaEntreDeMasDeSesentaCaractereseeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
        Assertions.assertFalse(criterioLongitudTest.validarContrasenia(contraseniaTest));
    }

    @Test
    public void validarContraseniaCorta(){
        String contraseniaTest = "contra";
        Assertions.assertFalse(criterioLongitudTest.validarContrasenia(contraseniaTest));
    }

}
