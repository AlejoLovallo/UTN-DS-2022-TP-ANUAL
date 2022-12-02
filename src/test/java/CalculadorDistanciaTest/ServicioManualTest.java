package CalculadorDistanciaTest;

import Domain.CalculadorDistancia.ServicioManual;
import Domain.Espacios.Espacio;
import Domain.Espacios.Estacion;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.TipoTransportePublico;
import Domain.MediosDeTransporte.TransportePublico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioManualTest {
    protected ServicioManual servManual;

    private void initializeServicioManual(){
        this.servManual=new ServicioManual();    }

    @BeforeEach
    public void initialize(){this.initializeServicioManual();}

    @AfterEach
    public void clean(){}


    @Test
    public void calcularDist(){
        Map<Estacion, Double> paradas = new HashMap<>();
        Estacion estacion1Test = new Estacion("Estacion1",1);
        Estacion estacion2Test = new Estacion("Estacion2",2);
        Estacion estacion3Test = new Estacion("Estacion2",3);
        paradas.put(estacion1Test,1.0);
        paradas.put(estacion2Test,1.0);
        paradas.put(estacion3Test,1.0);
        MedioDeTransporte untransporte= new TransportePublico(TipoTransportePublico.Colectivo,"2",paradas);

        Assertions.assertEquals(2.0,this.servManual.calcularDistancia(untransporte,estacion1Test,estacion3Test));



    }

}
