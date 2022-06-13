package Espacios;

import Domain.Espacios.Espacio;
import Domain.Espacios.TipoEspacio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EspaciosTest {
    protected Espacio unEspacio;

    private void initializeEspacio(){
        this.unEspacio=new Espacio("SiempreViva",355, TipoEspacio.Trabajo);
    }

    @BeforeEach
    public void initialize(){this.initializeEspacio();}

    @AfterEach
    public void clean(){}

    @Test
    public void toStrings(){
        Assertions.assertEquals(this.unEspacio.getTipoEspacio().toString() + ": " +  this.unEspacio.getCalle() + " " + this.unEspacio.getAltura().toString(),this.unEspacio.toString());
    }

    @Test
    public  void setCalle(){
        String calleActual=this.unEspacio.getCalle();
        String calleNueva="Cordoba";

        this.unEspacio.setCalle(calleNueva);

        Assertions.assertEquals("SiempreViva",calleActual);
        Assertions.assertEquals(calleNueva,this.unEspacio.getCalle());
    }

    @Test
    public void setAltura(){
        Integer actual=this.unEspacio.getAltura();
        Integer nueva=345;

        this.unEspacio.setAltura(nueva);

        Assertions.assertEquals(355,actual);
        Assertions.assertEquals(nueva,this.unEspacio.getAltura());
    }

    @Test
    public void setTipoEspacio(){
        TipoEspacio actual=this.unEspacio.getTipoEspacio();
        TipoEspacio nuevo=TipoEspacio.Vivienda;

        this.unEspacio.setTipoEspacio(nuevo);

        Assertions.assertEquals(TipoEspacio.Trabajo,actual);
        Assertions.assertEquals(nuevo,this.unEspacio.getTipoEspacio());
    }


}
