package MediosDeTransporte;

import Domain.Espacios.Estacion;
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

public class TransportePublicoTest {

    protected TransportePublico transportePublicoTest;
    protected TipoTransportePublico tipoTransportePublico = TipoTransportePublico.Colectivo;
    protected String linea = "LineaDeEjemplo";
    protected Map<Estacion,Double> paradas = new HashMap<>();

    protected Estacion estacion1Test = new Estacion("Estacion1",1);
    protected Estacion estacion2Test = new Estacion("Estacion2",2);


    private void initializeTransportePublico(){
        paradas.put(estacion1Test,1.0);
        paradas.put(estacion2Test,1.0);
        this.transportePublicoTest = new TransportePublico(tipoTransportePublico, linea, paradas);
    }

    @BeforeEach
    public void initialize() {
        this.initializeTransportePublico();
    }

    @AfterEach
    public void clean(){

    }

    @Test
    public void transportePublicoCreadoCorrectamente() {
        Assertions.assertEquals(tipoTransportePublico,this.transportePublicoTest.getTipoTransportePublico());
        Assertions.assertEquals(linea, this.transportePublicoTest.getLinea());
        Assertions.assertEquals(paradas, this.transportePublicoTest.getParadas());

    }


    @Test
    public void setTipoTransportePublico(){
        //GIVEN DADO
        TipoTransportePublico tipoTransportePublicoActual = this.transportePublicoTest.getTipoTransportePublico();
        TipoTransportePublico nuevoTipoTransportePublico = TipoTransportePublico.Subte;
        //WHEN CUANDO
        this.transportePublicoTest.setTipoTransportePublico(nuevoTipoTransportePublico);
        //THEN ENTONCES
        Assertions.assertEquals(tipoTransportePublicoActual,TipoTransportePublico.Colectivo);
        Assertions.assertEquals(nuevoTipoTransportePublico,this.transportePublicoTest.getTipoTransportePublico());
    }

    @Test
    public void setLinea(){
        //GIVEN DADO
        String lineaActual = this.transportePublicoTest.getLinea();
        String nuevaLinea = "NuevaLineaTest";
        //WHEN CUANDO
        this.transportePublicoTest.setLinea(nuevaLinea);
        //THEN ENTONCES
        Assertions.assertEquals(lineaActual,"LineaDeEjemplo");
        Assertions.assertEquals(nuevaLinea,this.transportePublicoTest.getLinea());
    }

    @Test
    public void setParadas(){
        //GIVEN DADO
        Map<Estacion,Double> paradasActual = this.transportePublicoTest.getParadas();
        Map<Estacion,Double> nuevasParadas = new HashMap<>();

        Estacion estacion1 = new Estacion("Estacion1",1);
        Estacion estacion2 = new Estacion("Estacion2",2);

        this.transportePublicoTest.darDeAltaParada(estacion1,1.0);
        this.transportePublicoTest.darDeAltaParada(estacion2,1.0);

        //WHEN CUANDO
        this.transportePublicoTest.setParadas(nuevasParadas);
        //THEN ENTONCES
        Assertions.assertEquals(nuevasParadas,this.transportePublicoTest.getParadas());
    }

}
