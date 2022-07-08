package Espacios;

import Domain.Espacios.Estacion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstacionTest {
    protected Estacion unaEst;

    private void initializeEstacion(){
        this.unaEst=new Estacion("Gerli",4,2.5f,3.6f);
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

    @Test
    public void setnumeroEstacion(){
        Integer actual=this.unaEst.getNumeroDeEstacion();
        Integer nuevo=7;

        this.unaEst.setNumeroDeEstacion(nuevo);

        Assertions.assertEquals(4,actual);
        Assertions.assertEquals(nuevo,this.unaEst.getNumeroDeEstacion());
    }

    @Test
    public void setdistEstAnt(){
        double actual=this.unaEst.getDistEstAnt();
        float nuevo=1.5f;

        this.unaEst.setDistEstAnt(nuevo);

        Assertions.assertEquals(2.5f,actual);
        Assertions.assertEquals(nuevo,this.unaEst.getDistEstAnt());
    }

    @Test
    public void setdistEstPos(){
        double actual=this.unaEst.getDistEstPos();
        float nuevo=1.5f;

        this.unaEst.setDistEstPos(nuevo);

        Assertions.assertEquals(3.6f,actual);
        Assertions.assertEquals(nuevo,this.unaEst.getDistEstPos());
    }
}
