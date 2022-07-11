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
import java.util.List;

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
        List<Estacion> paradas = new ArrayList<>();
        Estacion estacion1Test = new Estacion("Estacion1",1,1.5f,2.5f);
        Estacion estacion2Test = new Estacion("Estacion2",2,3.5f,4.5f);
        paradas.add(estacion1Test);
        paradas.add(estacion2Test);
        MedioDeTransporte untransporte= new TransportePublico(TipoTransportePublico.Colectivo,"2",paradas);
        Espacio partida =new Estacion("Estacion1",1,1.5f,2.5f);
        Espacio llegada= new Estacion("Estacion2",2,3.5f,4.5f);

        Assertions.assertEquals(4.5f,this.servManual.calcularDistancia(untransporte,partida,llegada));



    }

}
