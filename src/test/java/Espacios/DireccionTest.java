package Espacios;

import Domain.Espacios.Direccion;
import Domain.Espacios.TipoDireccion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DireccionTest {
    protected Direccion unaDirec;

    private void initializeDireccion(){
        this.unaDirec=new Direccion("Argentina","Buenos Aires","Mar del Plata","localidad1","siempreviva",333, TipoDireccion.Trabajo);
    }

    @BeforeEach
    public void initialize(){this.initializeDireccion();}

    @AfterEach
    public void clean(){}

    @Test
    public void setPais(){
        String actual=this.unaDirec.getPais();
        String nueva="BRASIL";

        this.unaDirec.setPais(nueva);

        Assertions.assertEquals("ARGENTINA",actual);
        Assertions.assertEquals(nueva,this.unaDirec.getPais());
    }

    @Test
    public void setprovincia(){
        String actual=this.unaDirec.getProvincia();
        String nueva="BRASILIA";

        this.unaDirec.setProvincia(nueva);

        Assertions.assertEquals("BUENOS AIRES",actual);
        Assertions.assertEquals(nueva,this.unaDirec.getProvincia());
    }

    @Test
    public void setmunicipio(){
        String actual=this.unaDirec.getMunicipio();
        String nueva="SANTA TERESITA";

        this.unaDirec.setMunicipio(nueva);

        Assertions.assertEquals("MAR DEL PLATA",actual);
        Assertions.assertEquals(nueva.toUpperCase(),this.unaDirec.getMunicipio());
    }

    @Test
    public void setlocalidad(){
        String actual=this.unaDirec.getLocalidad();
        String nueva="LOCALIDAD2";

        this.unaDirec.setLocalidad(nueva);

        Assertions.assertEquals("LOCALIDAD1",actual);
        Assertions.assertEquals(nueva,this.unaDirec.getLocalidad());
    }

    @Test
    public void setcalle(){
        String actual=this.unaDirec.getCalle();
        String nueva="cordoba";

        this.unaDirec.setCalle(nueva);

        Assertions.assertEquals("siempreviva",actual);
        Assertions.assertEquals(nueva,this.unaDirec.getCalle());
    }

    @Test
    public void setaltura(){
        Integer actual=this.unaDirec.getAltura();
        Integer nueva=123;

        this.unaDirec.setAltura(nueva);

        Assertions.assertEquals(333,actual);
        Assertions.assertEquals(nueva,this.unaDirec.getAltura());
    }

    @Test
    public void settipoDireccion(){
        TipoDireccion actual=this.unaDirec.getTipoDireccion();
        TipoDireccion nueva=TipoDireccion.Otro;

        this.unaDirec.setTipoEspacio(nueva);

        Assertions.assertEquals(TipoDireccion.Trabajo,actual);
        Assertions.assertEquals(nueva,this.unaDirec.getTipoDireccion());
    }

    @Test
    public void toStrings(){
        Assertions.assertEquals(this.unaDirec.getTipoDireccion().toString() + ": " +  this.unaDirec.getCalle() + " " + this.unaDirec.getAltura().toString(),this.unaDirec.toString());
    }

}
