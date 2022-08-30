package CalculadorDistanciaTest.endPointsTest;


import Domain.CalculadorDistancia.Endpoints.Pais;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaisTest {
    protected Pais pais;

    private void initializePais(){
        this.pais=new Pais("1","Argentina");    }

    @BeforeEach
    public void initialize(){this.initializePais();}

    @AfterEach
    public void clean(){}

    @Test
    public void getsetId(){
        String actualid=this.pais.getId();
        String idnew="2";

        this.pais.setId(idnew);

        Assertions.assertEquals("1",actualid);
        Assertions.assertEquals(idnew,this.pais.getId());
    }

    @Test
    public void getsetName(){
        String actualname=this.pais.getNombre();
        String namenew="Brasil";

        this.pais.setNombre(namenew);

        Assertions.assertEquals("Argentina",actualname);
        Assertions.assertEquals(namenew,this.pais.getNombre());

    }

}
