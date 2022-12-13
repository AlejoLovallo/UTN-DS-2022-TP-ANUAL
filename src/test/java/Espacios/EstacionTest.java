package Espacios;

import Domain.Espacios.Estacion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstacionTest {

    protected Estacion unaEst;

    private void initializeEstacion(){
        this.unaEst=new Estacion("Gerli");
    }

    @BeforeEach
    public void initialize(){this.initializeEstacion();}

    @AfterEach
    public void clean(){}

    @Test
    public void setNombre(){
        String actual=this.unaEst.getNombre();
        String nuevo="Lanus";

        this.unaEst.setNombre(nuevo);

        Assertions.assertEquals("Gerli",actual);
        Assertions.assertEquals(nuevo,this.unaEst.getNombre());
    }

}

