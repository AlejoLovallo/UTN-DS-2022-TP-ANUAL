package Trayecto;

import Domain.Espacios.Espacio;
import Domain.Espacios.TipoEspacio;
import Domain.MediosDeTransporte.TipoCombustible;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.TipoOrganizacion;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TrayectoTest {

    protected Trayecto trayectoTest;
    Tramo tramo1 = getTramoTest("Calle1", "Calle2");
    Tramo tramo2 = getTramoTest("Calle3", "Calle4");
    protected ArrayList<Tramo> tramosTest = new ArrayList<Tramo>();
    protected Organizacion organizacionTest = new Organizacion("OrganizacionEjemplo", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorPrimario);

    private void initializeTrayecto(){
        tramosTest.add(tramo1);
        tramosTest.add(tramo2);
        this.trayectoTest = new Trayecto(tramosTest, organizacionTest);
    }


    @BeforeEach
    public void initialize() {
        this.initializeTrayecto();
    }

    @AfterEach
    public void clean(){

    }


    private Tramo getTramoTest(String calle1, String calle2) {

        Espacio espacioTest1 = new Espacio(calle1, 100, TipoEspacio.Vivienda);
        Espacio espacioTest2 = new Espacio(calle2, 200, TipoEspacio.Trabajo);
        VehiculoParticular vehiculoTest = new VehiculoParticular(TipoVehiculo.Auto, TipoCombustible.Nafta);
        Integer cantPasajeros = 4;
        Tramo tramoEjemplo = new Tramo(espacioTest1, espacioTest2, vehiculoTest, cantPasajeros);;

        return tramoEjemplo;
    }


    @Test
    public void trayectoCreadoCorrectamente() {
        Assertions.assertEquals(tramosTest,this.trayectoTest.getTramos());
        Assertions.assertEquals(organizacionTest, this.trayectoTest.getOrganizacion());
    }

    @Test
    public void getTramos(){
        //GIVEN DADO
        //ArrayList<Tramo> tramosActuales = this.trayectoTest.getTramos();
        //ArrayList<Tramo> nuevosTramos = espacioTest2;
        //WHEN CUANDO
        //this.tramoTest.setPuntoPartida(nuevoPuntoDePartida);
        //THEN ENTONCES
        //Assertions.assertEquals(tramosTest,espacioTest1.toString());
        Assertions.assertEquals(tramosTest,this.trayectoTest.getTramos());
    }

    @Test
    public void getOrganizacion(){
        Assertions.assertEquals(organizacionTest,this.trayectoTest.getOrganizacion());
    }

    @Test
    public void getPuntoDePartida(){
        Assertions.assertEquals(tramo1.getPuntoPartida(),this.trayectoTest.getPuntoDePartida());
    }

    @Test
    public void getPuntoDeLlegada(){
        Assertions.assertEquals(tramo2.getPuntoLlegada(),this.trayectoTest.getPuntoDeLlegada());
    }


}
