package AfenteSectorial;

import Domain.Organizacion.*;
import Domain.Usuarios.Contacto;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class AgenteSectorialTest {
    private AgenteSectorial agenteSectorialTest;

    private void initializeAgenteSectorial(){
        this.agenteSectorialTest = new AgenteSectorial("AgenteSectorialTest", "territorio", TipoSectorTerritorial.Ministerio);
    }

    @BeforeEach
    public void initialize() {
        this.initializeAgenteSectorial();
    }

    @AfterEach
    public void clean(){

    }


    @Test
    public void agenteSectorialCreadoCorrectamente() {
        Assertions.assertEquals("AgenteSectorialTest",this.agenteSectorialTest.getNombre());
        Assertions.assertEquals("territorio", this.agenteSectorialTest.getTerritorio());
        Assertions.assertEquals(TipoSectorTerritorial.Ministerio, this.agenteSectorialTest.getTipoSectorTerritorial());
    }

    @Test
    public void setNombre(){
        //GIVEN DADO
        String nombreActual = this.agenteSectorialTest.getNombre();
        String nuevoNombreo = "NuevoNombreDeAgenteSectorial";
        //WHEN CUANDO
        this.agenteSectorialTest.setNombre(nuevoNombreo);
        //THEN ENTONCES
        Assertions.assertEquals(nombreActual, "AgenteSectorialTest");
        Assertions.assertEquals(nuevoNombreo,this.agenteSectorialTest.getNombre());
    }

    @Test
    public void setTerritorio(){
        //GIVEN DADO
        String territorioActual = this.agenteSectorialTest.getTerritorio();
        String nuevoTerritorio = "NuevoTerritorioDeAgenteSectorial";
        //WHEN CUANDO
        this.agenteSectorialTest.setTerritorio(nuevoTerritorio);
        //THEN ENTONCES
        Assertions.assertEquals(territorioActual,"territorio");
        Assertions.assertEquals(nuevoTerritorio,this.agenteSectorialTest.getTerritorio());
    }

    @Test
    public void setTipoSectorTerritorial(){
        //GIVEN DADO
        TipoSectorTerritorial tipoSectorTerritorialActual = this.agenteSectorialTest.getTipoSectorTerritorial();
        TipoSectorTerritorial nuevoTipoSectorTerritorial = TipoSectorTerritorial.Departamento;
        //WHEN CUANDO
        this.agenteSectorialTest.setTipoSectorTerritorial(nuevoTipoSectorTerritorial);
        //THEN ENTONCES
        Assertions.assertEquals(tipoSectorTerritorialActual, TipoSectorTerritorial.Ministerio);
        Assertions.assertEquals(nuevoTipoSectorTerritorial,this.agenteSectorialTest.getTipoSectorTerritorial());
    }

    @Test
    public void agregarOrganizacion(){
        //GIVEN DADO
        Contacto contactoOrganizacion = new Contacto("Nombre", "Apellido", 1234, "email@gmail.com");
        ArrayList<Organizacion> organizacioneslActual = this.agenteSectorialTest.getOrganizaciones();
        Organizacion nuevaOrganizacion = new Organizacion("razonSocaial", TipoOrganizacion.Empresa,ClasificacionOrganizacion.EmpresaSectorPrimario,contactoOrganizacion);
        ArrayList<Organizacion> organizacionesActualizadas = organizacioneslActual;
        organizacionesActualizadas.add(nuevaOrganizacion);
        //WHEN CUANDO
        this.agenteSectorialTest.agregarOrganizacion(nuevaOrganizacion);
        //THEN ENTONCES
        Assertions.assertEquals(organizacioneslActual, organizacioneslActual);
        Assertions.assertEquals(organizacioneslActual,this.agenteSectorialTest.getOrganizaciones());
    }

    @Test
    public void crearAgente(){
        AgenteSectorial agenteSectorial = RepositorioAgentes.GetInstance().crearAgente("Nombre", "Territorio",TipoSectorTerritorial.Ministerio);

        Assertions.assertEquals(agenteSectorial.getNombre(), "Nombre");
        Assertions.assertEquals(agenteSectorial.getTerritorio(), "Territorio");
        Assertions.assertEquals(agenteSectorial.getTipoSectorTerritorial(), TipoSectorTerritorial.Ministerio);
    }

   /* @Test
    public void agregarOrganizacionAAgente(){
        AgenteSectorial agenteSectorial = RepositorioAgentes.GetInstance().crearAgente("Nombre", "Territorio",TipoSectorTerritorial.Ministerio);

        //GIVEN DADO
        ArrayList<Organizacion> organizacionesActuales = agenteSectorial.getOrganizaciones();
        ArrayList<Organizacion> organizacionesActualizadas = organizacionesActuales;
        organizacionesActualizadas.add(Common.getOrganizacionMinisterio());
        //WHEN CUANDO
        agenteSectorial.agregarOrganizacion(Common.getOrganizacionMinisterio());

        //THEN ENTONCES
        Assertions.assertEquals(organizacionesActualizadas,agenteSectorial.getOrganizaciones());
    }*/

    @Test
    public void agregarOrganizacionAAgente(){
        Organizacion unaOrganizacion =  Common.getOrganizacionMinisterio();
        RepositorioAgentes.GetInstance().crearAgente("nombre", "territorio", TipoSectorTerritorial.Provincia);
        AgenteSectorial unAgente =  RepositorioAgentes.GetInstance().buscarAgente("nombre");
        RepositorioAgentes.GetInstance().agregarOrganizacionAAgente(unAgente.getNombre(), unaOrganizacion);
        Assertions.assertTrue(unAgente.getOrganizaciones().contains(unaOrganizacion));
    }
}
