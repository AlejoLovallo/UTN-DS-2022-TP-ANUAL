package Miembro;

import Domain.Miembro.Excepciones.MiembroNoPerteneceAOrganizacionException;
import Domain.Miembro.Excepciones.UnicoSectorPorOrganizacionException;
import Domain.Miembro.Miembro;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Trayecto.Trayecto;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MiembroTest {
    protected Miembro unMiembro;

    private void initializeMiembro(){
        Organizacion orgTest = Common.getOrganizacionEmpresaPrimaria();
        Sector sectorTest = Common.getSectorTrabajo();
        orgTest = Common.initializeOrganizacion(orgTest,sectorTest);
        this.unMiembro = new Miembro("miembro1",sectorTest);
    }

    @BeforeEach
    public void initialize(){this.initializeMiembro();}

    @AfterEach
    public void clean(){}

    @Test
    public void registrarTrayectos(){
        ArrayList<Trayecto> listaTrayectos=Common.getTrayectos(2);
        Organizacion orgTest = Common.getOrganizacionEmpresaPrimaria();
        this.unMiembro.getSector().setOrganizacion(orgTest);
        orgTest = Common.initializeOrganizacion(orgTest,this.unMiembro.getSector());
        this.unMiembro.registrarTrayectos(orgTest,listaTrayectos);

        Assertions.assertEquals(listaTrayectos,this.unMiembro.getTrayectos());
    }

    @Test
    public void registrarTrayectoExcepcion(){
        ArrayList<Trayecto> trayecto1=Common.getTrayectos(1);
        Organizacion org=Common.getOrganizacionMinisterio();
        this.unMiembro.getSector().setOrganizacion(Common.getOrganizacionEmpresaPrimaria());


        Assertions.assertThrows(MiembroNoPerteneceAOrganizacionException.class,
                () -> {
                    this.unMiembro.registrarTrayectos(org,trayecto1);
                });
    }

    @Test
    public void vincularSectorExcepcion(){
        Sector nuevoSector=Common.getSectorTrabajo();

        Assertions.assertThrows(UnicoSectorPorOrganizacionException.class,
                () -> {
                    this.unMiembro.vincularSector(nuevoSector);
                });


    }

    @Test
    public void vincularSector(){
        Sector nuevoSector=Common.getSectorTrabajo();
        Organizacion orgTest = Common.getOrganizacionEmpresaPrimaria();
        orgTest = Common.initializeOrganizacion(orgTest,nuevoSector);
        Miembro MiembroSinSector=new Miembro("miembro1",null);


        nuevoSector.setOrganizacion(orgTest);
        MiembroSinSector.vincularSector(nuevoSector);

        Assertions.assertEquals(nuevoSector,MiembroSinSector.getSector());

    }

}
