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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MiembroTest {
    protected Miembro elMiembro;

    private void initializeMiembro(){
        this.elMiembro=new Miembro("Ester","Exposito", TipoDocumento.DNI,"456789546");
    }

    @BeforeEach
    public void initialize(){this.initializeMiembro();}

    @AfterEach
    public void clean(){}

    @Test
    public void miembroCreadoCorrectamente(){
        Assertions.assertEquals("Ester",this.elMiembro.getNombre());
        Assertions.assertEquals("Exposito",this.elMiembro.getApellido());
        Assertions.assertEquals(TipoDocumento.DNI,this.elMiembro.getTipoDocumento());
        Assertions.assertEquals("456789546",this.elMiembro.getDocumento());
        Assertions.assertEquals(Arrays.asList(),this.elMiembro.getTrayectos());
        Assertions.assertNull(this.elMiembro.getSectorPorOrganizacion());
    }

    @Test
    public void setNombre() {
        String nombreActual=this.elMiembro.getNombre();
        String nombrenuevo="Juana";

        this.elMiembro.setNombre(nombrenuevo);

        Assertions.assertEquals("Ester",nombreActual);
        Assertions.assertEquals(nombrenuevo,this.elMiembro.getNombre());
    }

    @Test
    public void setApellido(){
        String apellidoActual=this.elMiembro.getApellido();
        String apellidonuevo="Gonzalez";

        this.elMiembro.setApellido(apellidonuevo);

        Assertions.assertEquals("Exposito",apellidoActual);
        Assertions.assertEquals(apellidonuevo,this.elMiembro.getApellido());
    }

    @Test
    public void setTipoDoc(){
        TipoDocumento tipodocActual=this.elMiembro.getTipoDocumento();
        TipoDocumento tipodocNuevo=TipoDocumento.PASAPORTE;

        this.elMiembro.setTipoDocumento(tipodocNuevo);

        Assertions.assertEquals(TipoDocumento.DNI,tipodocActual);
        Assertions.assertEquals(tipodocNuevo,this.elMiembro.getTipoDocumento());
    }
    
    @Test
    public void setDoc(){
        String docActual=this.elMiembro.getDocumento();
        String docNuevo="123456789";

        this.elMiembro.setDocumento(docNuevo);

        Assertions.assertEquals("456789546",docActual);
        Assertions.assertEquals(docNuevo,this.elMiembro.getDocumento());
    }

    @Test
    public void setTrayecto(){
        Trayecto trayecto1=new Trayecto();
        Trayecto trayecto2=new Trayecto();
        ArrayList<Trayecto> trayectos=new ArrayList<>();
        trayectos.add(trayecto1);
        trayectos.add(trayecto2);

        this.elMiembro.setTrayectos(trayectos);

        Assertions.assertEquals(trayectos,this.elMiembro.getTrayectos());
    }

    @Test
    public void setSectorxOrg(){

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
