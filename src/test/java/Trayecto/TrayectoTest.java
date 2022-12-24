package Trayecto;

import Domain.Miembro.Miembro;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrayectoTest {

    protected Trayecto trayectoTest;
    Tramo tramo1 = Common.getTramoTest("Calle1", "Calle2");
    Tramo tramo2 = Common.getTramoTest("Calle3", "Calle4");
    protected ArrayList<Tramo> tramosTest = new ArrayList<Tramo>();

    //protected Organizacion organizacionTest = new Organizacion("OrganizacionEjemplo", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorPrimario);

    protected Miembro unMiembro = Common.getMiembro();

    private void initializeTrayecto(){
        tramosTest.add(tramo1);
        tramosTest.add(tramo2);

        LocalDate fechaInicio =   LocalDate.of(2010, 1, 1);
        LocalDate fechaFin = LocalDate.of(2021, 12, 31);

        this.trayectoTest = new Trayecto(tramosTest, unMiembro, 3, fechaInicio, fechaFin, true);
    }


    @BeforeEach
    public void initialize() {
        this.initializeTrayecto();
    }

    @AfterEach
    public void clean(){

    }


    @Test
    public void trayectoCreadoCorrectamente() {
        Assertions.assertEquals(tramosTest,this.trayectoTest.getTramos());
        Assertions.assertEquals(unMiembro, this.trayectoTest.getMiembro());
    }

    @Test
    public void getTramos(){
        Assertions.assertEquals(tramosTest,this.trayectoTest.getTramos());
    }

    @Test
    public void getMiembro(){
        Assertions.assertEquals(unMiembro,this.trayectoTest.getMiembro());
    }


    @Test
    public void getPuntoDePartida(){
        Assertions.assertEquals(tramo1.getPuntoPartida(),this.trayectoTest.getPuntoDePartida());
    }

    @Test
    public void getPuntoDeLlegada(){
        Assertions.assertEquals(tramo2.getPuntoLlegada(),this.trayectoTest.getPuntoDeLlegada());
    }

    @Test
    public void setTramos(){

        Tramo tramo1 = Common.getTramoTest("Calle2", "Calle3");
        Tramo tramo2 = Common.getTramoTest("Calle4", "Calle5");

        //GIVEN DADO
        List<Tramo> tramosActuales = this.trayectoTest.getTramos();
        ArrayList<Tramo> nuevosTramos = new ArrayList<Tramo>();

        //WHEN CUANDO
        nuevosTramos.add(tramo1);
        nuevosTramos.add(tramo2);
        this.trayectoTest.setTramos(nuevosTramos);

        //THEN ENTONCES
        //Assertions.assertEquals(miembroActual,xxxxxxxxxxxxxx);
        Assertions.assertEquals(nuevosTramos,this.trayectoTest.getTramos());
    }
    @Test
    public void setMiembro(){
        //GIVEN DADO
        Miembro miembroActual = this.trayectoTest.getMiembro();
        Miembro nuevoMiembro = Common.getMiembro();;
        //WHEN CUANDO
        this.trayectoTest.setMiembro(nuevoMiembro);
        //THEN ENTONCES
        //Assertions.assertEquals(miembroActual,xxxxxxxxxxxxxx);
        Assertions.assertEquals(nuevoMiembro,this.trayectoTest.getMiembro());
    }


    @Test
    public void addTramo(){

        //GIVEN DADO
        List<Tramo> tramosActuales = this.trayectoTest.getTramos();
        Tramo nuevoTramo = Common.getTramoTest("Calle10", "Calle20");

        //WHEN CUANDO
        tramosActuales.add(nuevoTramo);
        this.trayectoTest.addTramo(nuevoTramo);

        //THEN ENTONCES
        //Assertions.assertEquals(miembroActual,xxxxxxxxxxxxxx);
        Assertions.assertEquals(tramosActuales,this.trayectoTest.getTramos());
    }


/*
    @Test
    public void determinarDistanciaTotal() throws IOException {

        double distaciaTotal = 2;
        Assertions.assertEquals(distaciaTotal,this.trayectoTest.determinarDistanciaTotal());
    }
*/
}

