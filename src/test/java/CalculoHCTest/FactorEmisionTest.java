package CalculoHCTest;

import Domain.CalculadorDistancia.ServicioManual;
import Domain.CalculoHC.FactorEmision;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FactorEmisionTest {
    protected FactorEmision facEm;

    private void initializeFactorEmision(){
        this.facEm=FactorEmision.getInstance();    }

    @BeforeEach
    public void initialize(){this.initializeFactorEmision();}

    @AfterEach
    public void clean(){}

    @Test
    public void getEmisionCombustionFija(){
        this.facEm.setEmisionCombustionFija(4);

        Assertions.assertEquals(4,facEm.getEmisionCombustionFija());
    }

    @Test
    public void getEmisionCombustionMovil(){
        this.facEm.setEmisionCombustionMovil(4);

        Assertions.assertEquals(4,facEm.getEmisionCombustionMovil());
    }

    @Test
    public void getEmisionElectricidad(){
        this.facEm.setEmisionElectricidad(4);

        Assertions.assertEquals(4,facEm.getEmisionElectricidad());
    }

    @Test
    public void getEmisionLogistica(){
        this.facEm.setEmisionLogistica(4);

        Assertions.assertEquals(4,facEm.getEmisionLogistica());
    }

}
