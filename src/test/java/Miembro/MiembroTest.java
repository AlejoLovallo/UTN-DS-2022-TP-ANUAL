package Miembro;

import Domain.Espacios.Espacio;
import Domain.Espacios.TipoEspacio;
import Domain.Miembro.Excepciones.MiembroNoPerteneceAOrganizacionException;
import Domain.Miembro.Miembro;
import Domain.Miembro.TipoDocumento;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Organizacion.TipoOrganizacion;
import Domain.Trayecto.Trayecto;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MiembroTest {
    protected Miembro unMiembro;

    private void initializeMiembro(){
        Organizacion orgTest = Common.getOrganizacionEmpresaPrimaria();
        Sector sectorTest = Common.getSectorTrabajo();
        orgTest = Common.initializeOrganizacion(orgTest,sectorTest);
        this.unMiembro = new Miembro("miembro1",orgTest,sectorTest);
    }

    @BeforeEach
    public void initialize(){this.initializeMiembro();}

    @AfterEach
    public void clean(){}
/*
    @Test
    public void setTrayecto(){
        Trayecto trayecto1=new Trayecto();
        Trayecto trayecto2=new Trayecto();
        ArrayList<Trayecto> trayectos=new ArrayList<>();
        trayectos.add(trayecto1);
        trayectos.add(trayecto2);

        this.unMiembro.setTrayectos(trayectos);

        Assertions.assertEquals(trayectos,this.unMiembro.getTrayectos());
    }

    /*
    @Test
    public void registrarTrayecto(){
        Organizacion org=new Organizacion("OrganizacionTest", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorPrimario);
        Trayecto trayecto1=new Trayecto();

        this.elMiembro.registrarTrayecto(org,trayecto1);

        Assertions.assertThrows(throw new MiembroNoPerteneceAOrganizacionException(org.getRazonSocial()),this.elMiembro.registrarTrayecto(org,trayecto1));
    }

*/
   /* @Test
    public void vincularseAorg(){
        Organizacion org=new Organizacion("OrganizacionTest", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorPrimario);
        Espacio espacio=new Espacio("Cordoba",3000, TipoEspacio.Trabajo);
        Sector sector=new Sector("Administracion",espacio);

        this.elMiembro.vincularseAOrganizacion(org,sector);

        Assertions.assertNull(this.elMiembro.getSectorPorOrganizacion());


    }
*/

}
