package Trayecto;

import Domain.Espacios.Espacio;
import Domain.Espacios.TipoEspacio;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.TipoCombustible;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.Trayecto.Tramo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TramoTest {

    protected Tramo tramoTest;
    protected Espacio espacioTest1 = new Espacio("CalleEjemplo1", 100, TipoEspacio.Vivienda);
    protected Espacio espacioTest2 = new Espacio("CalleEjemplo2", 200, TipoEspacio.Trabajo);
    protected VehiculoParticular vehiculoTest = new VehiculoParticular(TipoVehiculo.Auto, TipoCombustible.Nafta);
    protected Integer cantPasajeros = 4;

    private void initializeTramo(){
        this.tramoTest = new Tramo(espacioTest1, espacioTest2, vehiculoTest, cantPasajeros);
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
        Assertions.assertEquals(espacioTest1.toString(),this.tramoTest.getPuntoPartida());
        Assertions.assertEquals(espacioTest2.toString(), this.tramoTest.getPuntoLlegada());
        Assertions.assertEquals(vehiculoTest, this.tramoTest.getMedioTransporte());
        Assertions.assertEquals(cantPasajeros, this.tramoTest.getCantPasajeros());
    }

    @Test
    public void setPuntoPartida(){
        //GIVEN DADO
        String puntoPartidaActual = this.tramoTest.getPuntoPartida();
        Espacio nuevoPuntoDePartida = espacioTest2;
        //WHEN CUANDO
        this.tramoTest.setPuntoPartida(nuevoPuntoDePartida);
        //THEN ENTONCES
        Assertions.assertEquals(puntoPartidaActual,espacioTest1.toString());
        Assertions.assertEquals(nuevoPuntoDePartida.toString(),this.tramoTest.getPuntoPartida());
    }

    @Test
    public void setPuntoLlegada(){
        //GIVEN DADO
        String puntoLlegadaActual = this.tramoTest.getPuntoLlegada();
        Espacio nuevoPuntoDeLlegada = espacioTest1;
        //WHEN CUANDO
        this.tramoTest.setPuntoLLegada(nuevoPuntoDeLlegada);
        //THEN ENTONCES
        Assertions.assertEquals(puntoLlegadaActual,espacioTest2.toString());
        Assertions.assertEquals(nuevoPuntoDeLlegada.toString(),this.tramoTest.getPuntoLlegada());
    }

    @Test
    public void setMedio(){

        VehiculoParticular vehiculoTest2 = new VehiculoParticular(TipoVehiculo.Camioneta, TipoCombustible.Electrico);

        //GIVEN DADO
        MedioDeTransporte medioDeTransporteActual = this.tramoTest.getMedioTransporte();
        MedioDeTransporte nuevoMedioDeTransporte = vehiculoTest2;
        //WHEN CUANDO
        this.tramoTest.setMedio(nuevoMedioDeTransporte);
        //THEN ENTONCES
        Assertions.assertEquals(medioDeTransporteActual, vehiculoTest);
        Assertions.assertEquals(nuevoMedioDeTransporte,this.tramoTest.getMedioTransporte());
    }

    @Test
    public void setCantPasajeros(){

        Integer cantPasajeros2 = 2;

        //GIVEN DADO
        Integer cantPasajeroActual = this.tramoTest.getCantPasajeros();
        Integer nuevaCantPasajero = cantPasajeros2;
        //WHEN CUANDO
        this.tramoTest.setCantPasajeros(nuevaCantPasajero);
        //THEN ENTONCES
        Assertions.assertEquals(cantPasajeroActual, cantPasajeros);
        Assertions.assertEquals(cantPasajeros2,this.tramoTest.getCantPasajeros());
    }

}
