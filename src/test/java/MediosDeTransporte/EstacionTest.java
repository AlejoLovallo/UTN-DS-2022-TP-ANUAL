package MediosDeTransporte;


import Domain.MediosDeTransporte.Estacion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstacionTest {

    protected Estacion estacionTest;

    protected String nombre = "EstacionTest";
    protected Integer numeroDeEstacion = 11;

    private void initializeEstacion(){
        this.estacionTest = new Estacion(nombre, numeroDeEstacion);
    }

    @BeforeEach
    public void initialize() {
        this.initializeEstacion();
    }

    @AfterEach
    public void clean(){

    }


    @Test
    public void estacionCreadaCorrectamente() {
        Assertions.assertEquals(nombre,this.estacionTest.getNombre());
        Assertions.assertEquals(numeroDeEstacion, this.estacionTest.getNumeroDeEstacion());
    }

    @Test
    public void setNombre(){
        //GIVEN DADO
        String nombreActual = this.estacionTest.getNombre();
        String nuevoNombre = "NuevoNombre";
        //WHEN CUANDO
        this.estacionTest.setNombre(nuevoNombre);
        //THEN ENTONCES
        Assertions.assertEquals(nombreActual,"EstacionTest");
        Assertions.assertEquals(nuevoNombre,this.estacionTest.getNombre());
    }


    @Test
    public void setNumeroDeEstacion(){
        //GIVEN DADO
        Integer numeroDeEstacionActual = this.estacionTest.getNumeroDeEstacion();
        Integer nuevoNumeroDeEstacion = 22;
        //WHEN CUANDO
        this.estacionTest.setNumeroDeEstacion(nuevoNumeroDeEstacion);
        //THEN ENTONCES
        Assertions.assertEquals(numeroDeEstacionActual,11);
        Assertions.assertEquals(nuevoNumeroDeEstacion,this.estacionTest.getNumeroDeEstacion());
    }



}
