package CalculadorDistanciaTest.endPointsTest;

import Domain.CalculadorDistancia.Endpoints.Distancia;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DistanciaTest {
    protected Distancia unaDistancia;

    private void initializeDistancia(){
        this.unaDistancia=new Distancia();
    }

    @BeforeEach
    public void initialize(){this.initializeDistancia();}

    @AfterEach
    public void clean(){}

    @Test
    public void getsetValor(){
        String unval="50";

        this.unaDistancia.setValor(unval);

        Assertions.assertEquals(unval,this.unaDistancia.getValor());
    }

    @Test
    public void getsetUnidad(){
        String unidad="km";

        this.unaDistancia.setUnidad(unidad);

        Assertions.assertEquals(unidad,this.unaDistancia.getUnidad());
    }
}

