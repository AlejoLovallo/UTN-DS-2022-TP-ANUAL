package MediosDeTransporte.Usuarios;

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


    @Test
    public void validarContraseniaExcepcion(){

        //GIVEN DADO
        String rutaActual = this.peores10KContraTest.getRutaPeoresContra();
        String nuevaRuta = "RutaNoExistente";

        //WHEN CUANDO
        this.peores10KContraTest.setRutaPeoresContra(nuevaRuta);

        //THEN ENTONCES
        String contraseniaTest = "contraseniaTest";
        Assertions.assertEquals(rutaActual, "src/main/java/Domain/Usuarios/10kPasswords.txt");
        Assertions.assertThrows(ArchivoInaccesibleException.class,
                () -> {
                    this.peores10KContraTest.validarContrasenia(contraseniaTest);
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
