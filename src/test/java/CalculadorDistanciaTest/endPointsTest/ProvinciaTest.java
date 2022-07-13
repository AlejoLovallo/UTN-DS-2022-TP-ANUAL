package CalculadorDistanciaTest.endPointsTest;


import Domain.CalculadorDistancia.Endpoints.Provincia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProvinciaTest {
    protected Provincia prov;

    private void initializeProvincia(){
        this.prov=new Provincia("1","BsAs");    }

    @BeforeEach
    public void initialize(){this.initializeProvincia();}

    @AfterEach
    public void clean(){}

    @Test
    public void getsetId(){
        String actualid=this.prov.getId();
        String idnew="2";

        this.prov.setId(idnew);

        Assertions.assertEquals("1",actualid);
        Assertions.assertEquals(idnew,this.prov.getId());
    }

    @Test
    public void getsetName(){
        String actualname=this.prov.getNombre();
        String namenew="Salta";

        this.prov.setNombre(namenew);

        Assertions.assertEquals("BsAs",actualname);
        Assertions.assertEquals(namenew,this.prov.getNombre());

    }

}
