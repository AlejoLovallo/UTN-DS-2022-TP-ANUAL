package Trayecto;

import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.Espacios.TipoDireccion;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.TipoCombustible;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.Trayecto.Tramo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class TramoTest {

    protected Tramo tramoTest;
    protected Direccion espacioTest1 = new Direccion("Argentina","Buenos Aires","CABA","CABA","CalleEjemplo1", 100, TipoDireccion.Vivienda);
    protected Direccion espacioTest2 = new Direccion("Argentina","Buenos Aires","CABA","CABA","CalleEjemplo2", 200, TipoDireccion.Trabajo);
    protected VehiculoParticular vehiculoTest = new VehiculoParticular(TipoVehiculo.Auto, TipoCombustible.Nafta,2);
    //protected Integer cantPasajeros = 4;

    private void initializeTramo(){
        this.tramoTest = new Tramo(espacioTest1, espacioTest2, vehiculoTest);
    }


    @BeforeEach
    public void initialize() {
        this.initializeTramo();
    }

    @AfterEach
    public void clean(){

    }


    @Test
    public void tramoCreadoCorrectamente() {
        Assertions.assertEquals(espacioTest1,this.tramoTest.getPuntoPartida());
        Assertions.assertEquals(espacioTest2, this.tramoTest.getPuntoLlegada());
        Assertions.assertEquals(vehiculoTest, this.tramoTest.getMedioTransporte());
    }


    @Test
    public void setPuntoPartida(){
        //GIVEN DADO
        Espacio puntoPartidaActual = this.tramoTest.getPuntoPartida();
        Espacio nuevoPuntoDePartida = espacioTest2;
        //WHEN CUANDO
        this.tramoTest.setPuntoPartida(nuevoPuntoDePartida);
        //THEN ENTONCES
        Assertions.assertEquals(puntoPartidaActual,espacioTest1);
        Assertions.assertEquals(nuevoPuntoDePartida,this.tramoTest.getPuntoPartida());
    }

    @Test
    public void setPuntoLlegada(){
        //GIVEN DADO
        Espacio puntoLlegadaActual = this.tramoTest.getPuntoLlegada();
        Espacio nuevoPuntoDeLlegada = espacioTest1;
        //WHEN CUANDO
        this.tramoTest.setPuntoLLegada(nuevoPuntoDeLlegada);
        //THEN ENTONCES
        Assertions.assertEquals(puntoLlegadaActual,espacioTest2);
        Assertions.assertEquals(nuevoPuntoDeLlegada,this.tramoTest.getPuntoLlegada());
    }

    @Test
    public void setMedio(){

        VehiculoParticular vehiculoTest2 = new VehiculoParticular(TipoVehiculo.Camioneta, TipoCombustible.Electrico,2);

        //GIVEN DADO
        MedioDeTransporte medioDeTransporteActual = this.tramoTest.getMedioTransporte();
        MedioDeTransporte nuevoMedioDeTransporte = vehiculoTest2;
        //WHEN CUANDO
        this.tramoTest.setMedio(nuevoMedioDeTransporte);
        //THEN ENTONCES
        Assertions.assertEquals(medioDeTransporteActual, vehiculoTest);
        Assertions.assertEquals(nuevoMedioDeTransporte,this.tramoTest.getMedioTransporte());
    }

    /*
    @Test
    public void determinarDistancia() throws IOException {
        double distancia = 0;
        Assertions.assertEquals(distancia,this.tramoTest.determinarDistancia());
    }
*/

}
