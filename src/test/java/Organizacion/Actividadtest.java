package Organizacion;

import Domain.CalculadorHC.FactorEmision;
import Domain.Organizacion.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

public class Actividadtest {
/*
    protected Actividad act;

    private void initializeActividad(){
        Date fechaCarga = new Date(2020, 07, 22);
        this.act = new Actividad(TipoDeActividad.COMBUSTION_FIJA, TipoDeConsumo.CARBON_DE_LENIA);
    }

    @BeforeEach
    public void initialize(){this.initializeActividad();}

    @AfterEach
    public void clean(){}

    @Test
    public void ActividadCreadaCorrectamente(){

        Assertions.assertEquals(TipoDeActividad.COMBUSTION_FIJA,act.getNombre());
        Assertions.assertEquals(TipoDeConsumo.CARBON_DE_LENIA,act.getTipoConsumo());
    }

    @Test
    public void setTipoActividad(){
        TipoDeActividad actual=this.act.getNombre();
        TipoDeActividad nueva=TipoDeActividad.ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA;

        this.act.setNombre(nueva);

        Assertions.assertEquals(TipoDeActividad.COMBUSTION_FIJA,actual);
        Assertions.assertEquals(nueva,this.act.getNombre());
    }

    @Test
    public void setFactorEmision(){
        FactorEmision FE=new FactorEmision();

        this.act.setFactorEmision(FE);

        Assertions.assertEquals(FE,this.act.getFactorEmision());
    }

    @Test
    public void setUnidadConsumo(){
        UnidadDeConsumo u= UnidadDeConsumo.kg;

        this.act.setUnidad_Consumo(u);

        Assertions.assertEquals(u,this.act.getUnidad_Consumo());
    }
    @Test
    public void setTipoConsumo(){
        TipoDeConsumo actual=this.act.getTipoConsumo();
        TipoDeConsumo nuevo=TipoDeConsumo.CARBON;

        this.act.setTipoConsumo(nuevo);

        Assertions.assertEquals(actual,TipoDeConsumo.CARBON_DE_LENIA);
        Assertions.assertEquals(nuevo,this.act.getTipoConsumo());

    }
    @Test
    public void setConsumos(){
        ArrayList<Consumo> consumos=new ArrayList<>();

        Consumo consumo1=new Consumo(1,2022,50.0);
        Consumo consumo2=new Consumo(3,2022,30.0);
        Consumo consumo3=new Consumo(4,2022,20.0);

        consumos.add(consumo1);
        consumos.add(consumo2);
        consumos.add(consumo3);

        this.act.setConsumos(consumos);

        Assertions.assertEquals(consumos,this.act.getConsumos());
    }

    @Test
    public void agregarConsumo(){
        ArrayList<Consumo> consumos=new ArrayList<>();
        Consumo consumo1=new Consumo(1,2022,50.0);
        Consumo consumo2=new Consumo(3,2022,30.0);
        Consumo consumo3=new Consumo(4,2022,20.0);

        consumos.add(consumo1);
        consumos.add(consumo2);
        consumos.add(consumo3);

        this.act.setConsumos(consumos);
        this.act.agregarConsumo(3,2022,15.0);
        Consumo consumo=new Consumo(3,2022,15.0);


        Assertions.assertEquals(consumos,this.act.getConsumos());
        Assertions.assertEquals(30.0+15.0,this.act.encontrarConsumo(3,2022));
    }
    @Test
    public void agregarConsumoqueNoEsta(){
        ArrayList<Consumo> consumos=new ArrayList<>();
        Consumo consumo1=new Consumo(1,2022,50.0);
        Consumo consumo3=new Consumo(4,2022,20.0);

        consumos.add(consumo1);
        consumos.add(consumo3);

        this.act.setConsumos(consumos);
        this.act.agregarConsumo(3,2022,15.0);
        Consumo consumo=new Consumo(3,2022,15.0);
        consumos.add(consumo);

        Assertions.assertEquals(consumos,this.act.getConsumos());
        Assertions.assertEquals(15.0,this.act.encontrarConsumo(3,2022));
    }
    @Test
    public  void encontrarConsumo(){
        ArrayList<Consumo> consumos=new ArrayList<>();

        Consumo consumo1=new Consumo(1,2022,50.0);
        Consumo consumo2=new Consumo(3,2022,30.0);
        Consumo consumo3=new Consumo(4,2022,20.0);

        consumos.add(consumo1);
        consumos.add(consumo2);
        consumos.add(consumo3);

        this.act.setConsumos(consumos);

        Assertions.assertEquals(30,this.act.encontrarConsumo(3,2022));
        Assertions.assertEquals(20,this.act.encontrarConsumo(4,2022));
    }*/
}