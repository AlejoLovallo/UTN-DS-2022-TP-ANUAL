package Usuarios;

import Domain.Usuarios.UltimoIntento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UltimoIntentoTest {

    protected UltimoIntento ultimoIntentoTest;
    private void initializeUltimoIntento(){
        this.ultimoIntentoTest = new UltimoIntento();
    }

    @BeforeEach
    public void initializeTramo() {
        this.initializeUltimoIntento();
    }

    @AfterEach
    public void clean(){

    }

    //////////////////////////////////  CHEQUEAR
    @Test
    public void validar_acceso(){
        Assertions.assertFalse(ultimoIntentoTest.validar_acceso());
    }
}
