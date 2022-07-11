package CalculadorDistanciaTest.endPointsTest;

import Domain.CalculadorDistancia.Endpoints.Localidad;
import Domain.CalculadorDistancia.Endpoints.Municipio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MunicipioTest {
    protected Municipio muni;

    private void initializeMunicipio(){
        this.muni=new Municipio("1","Lanus");    }

    @BeforeEach
    public void initialize(){this.initializeMunicipio();}

    @AfterEach
    public void clean(){}

    @Test
    public void getsetId(){
        String actualid=this.muni.getId();
        String idnew="2";

        this.muni.setId(idnew);

        Assertions.assertEquals("1",actualid);
        Assertions.assertEquals(idnew,this.muni.getId());
    }

    @Test
    public void getsetName(){
        String actualname=this.muni.getNombre();
        String namenew="Avellaneda";

        this.muni.setNombre(namenew);

        Assertions.assertEquals("Lanus",actualname);
        Assertions.assertEquals(namenew,this.muni.getNombre());

    }

}
