package CalculadorDistanciaTest.endPointsTest;

import Domain.CalculadorDistancia.Endpoints.Localidad;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LocalidadTest {
    protected Localidad localidad;

    private void initializeLocalidad(){
        this.localidad=new Localidad("1","Lanus");    }

    @BeforeEach
    public void initialize(){this.initializeLocalidad();}

    @AfterEach
    public void clean(){}

    @Test
    public void getsetId(){
        String actualid=this.localidad.getId();
        String idnew="2";

        this.localidad.setId(idnew);

        Assertions.assertEquals("1",actualid);
        Assertions.assertEquals(idnew,this.localidad.getId());
    }

    @Test
    public void getsetName(){
        String actualname=this.localidad.getNombre();
        String namenew="Avellaneda";

        this.localidad.setNombre(namenew);

        Assertions.assertEquals("Lanus",actualname);
        Assertions.assertEquals(namenew,this.localidad.getNombre());

    }
}
