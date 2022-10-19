package Organizacion;

import Domain.Organizacion.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class Actividadtest {
/*
    //TODO volver a testear
    protected Actividad act;

    private void initializeActividad(){
        Date fechaCarga = new Date(2020, 07, 22);
        this.act = new Actividad(TipoDeActividad.COMBUSTION_FIJA, TipoDeConsumo.CARBON_DE_LENIA, FrecuenciaServicio.MENSUAL,  fechaCarga, UnidadDeConsumo.lts);
    }

    @BeforeEach
    public void initialize(){this.initializeActividad();}

    @AfterEach
    public void clean(){}

    @Test
    public void ActividadCreadaCorrectamente(){

        Assertions.assertEquals(TipoDeActividad.COMBUSTION_FIJA,act.getTipoDeActividad());
        Assertions.assertEquals(TipoDeConsumo.CARBON_DE_LENIA,act.getTipoDeConsumo());
        Assertions.assertEquals(UnidadDeConsumo.kg,act.getUnidadDeConsumo());
    }

    @Test
    public void setTipoActividad(){
        TipoDeActividad actual=this.act.getTipoDeActividad();
        TipoDeActividad nueva=TipoDeActividad.ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA;

        this.act.setTipoDeActividad(nueva);

        Assertions.assertEquals(TipoDeActividad.COMBUSTION_FIJA,actual);
        Assertions.assertEquals(nueva,this.act.getTipoDeActividad());
    }*/
}
//faltan mas test con lector de actividades