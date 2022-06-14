package Contrasenia;

import Domain.Contrasenia.Contrasenia;
import Domain.Contrasenia.Excepciones.ArchivoInaccesibleException;
import Domain.Contrasenia.UltimoIntento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContraseniaTest {

    protected Contrasenia contraseniaTest;

    private void initializeContrasenia(){
        this.contraseniaTest = new Contrasenia();
    }

    @BeforeEach
    public void initializeTramo() {
        this.initializeContrasenia();
    }

    @AfterEach
    public void clean(){

    }


    @Test
    public void isValida(){
        String unaContrasenia = "123456";

        Assertions.assertFalse(this.contraseniaTest.isValida(unaContrasenia));

    }








}
