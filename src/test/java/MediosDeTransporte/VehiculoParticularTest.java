package MediosDeTransporte;

import Domain.MediosDeTransporte.TipoCombustible;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.VehiculoParticular;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VehiculoParticularTest {

    protected TipoVehiculo tipoVehiculo = TipoVehiculo.Camioneta ;
    protected TipoCombustible tipoCombustible = TipoCombustible.Nafta;

    protected VehiculoParticular vehiculoParticularTest;

    private void initializeVehiculoParticular(){
        this.vehiculoParticularTest = new VehiculoParticular(tipoVehiculo, tipoCombustible);
    }


    @BeforeEach
    public void initialize() {
        this.initializeVehiculoParticular();
    }

    @AfterEach
    public void clean(){

    }


    @Test
    public void tramoCreadoCorrectamente() {
        Assertions.assertEquals(tipoVehiculo,this.vehiculoParticularTest.getTipoVehiculo());
        Assertions.assertEquals(tipoCombustible, this.vehiculoParticularTest.getTipoCombustible());
    }

    @Test
    public void setTipoVehiculo(){
        //GIVEN DADO
        TipoVehiculo vehiculoParticularActual = this.vehiculoParticularTest.getTipoVehiculo();
        TipoVehiculo nuevoVehiculoParticular = TipoVehiculo.Moto;
        //WHEN CUANDO
        this.vehiculoParticularTest.setTipoVehiculo(nuevoVehiculoParticular);
        //THEN ENTONCES
        Assertions.assertEquals(vehiculoParticularActual, TipoVehiculo.Camioneta);
        Assertions.assertEquals(nuevoVehiculoParticular,this.vehiculoParticularTest.getTipoVehiculo());
    }

    @Test
    public void setTipoCombustible(){
        //GIVEN DADO
        TipoCombustible tipoCombustibleActual = this.vehiculoParticularTest.getTipoCombustible();
        TipoCombustible nuevoTipoCombustible = TipoCombustible.Electrico;
        //WHEN CUANDO
        this.vehiculoParticularTest.setTipoCombustible(nuevoTipoCombustible);
        //THEN ENTONCES
        Assertions.assertEquals(tipoCombustibleActual,TipoCombustible.Nafta);
        Assertions.assertEquals(nuevoTipoCombustible,this.vehiculoParticularTest.getTipoCombustible());
    }
}
