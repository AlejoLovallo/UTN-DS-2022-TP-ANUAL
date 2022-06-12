package Organizacion;

import Domain.Organizacion.Actividad;
import Domain.Organizacion.TipoDeActividad;
import Domain.Organizacion.TipoDeConsumo;
import Domain.Organizacion.UnidadDeConsumo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Actividadtest {

    protected Actividad act;

    private void initializeActividad(){
        this.act=new Actividad(TipoDeActividad.COMBUSTION_FIJA,TipoDeConsumo.CARBON_DE_LENIA,UnidadDeConsumo.kg);
    }

    @BeforeEach
    public void initialize(){this.initializeActividad();}

    @AfterEach
    public void clean(){}

    @Test
    public void ActividadCreadaCorrectamente(){

        Assertions.assertEquals(TipoDeActividad.COMBUSTION_FIJA,act.getNombre());
        Assertions.assertEquals(TipoDeConsumo.CARBON_DE_LENIA,act.getTipoDeConsumo());
        Assertions.assertEquals(UnidadDeConsumo.kg,act.getUnidadDeConsumo());
    }

    @Test
    public void setTipoActividad(){
        TipoDeActividad actual=this.act.getNombre();
        TipoDeActividad nueva=TipoDeActividad.ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA;

        this.act.setNombre(nueva);

        Assertions.assertEquals(TipoDeActividad.COMBUSTION_FIJA,actual);
        Assertions.assertEquals(nueva,this.act.getNombre());
    }
}
//faltan mas test con lector de actividades