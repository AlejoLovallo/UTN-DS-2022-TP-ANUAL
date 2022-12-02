package AgenteSectorial;

import Domain.Organizacion.*;
import Domain.Repositorios.RepositorioAgentesDB;
import Domain.Usuarios.Contacto;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AgenteSectorialTest {
    private AgenteSectorial agenteSectorialTest;



    private void initializeAgenteSectorial(){
        this.agenteSectorialTest = new AgenteSectorial("AgenteSectorialTest", "territorio", TipoSectorTerritorial.Ministerio);
        agenteSectorialTest.agregarOrganizacion(Common.getOrganizacionConUnMiembro());
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
        List<Organizacion> organizacioneslActual = this.agenteSectorialTest.getOrganizaciones();
        Organizacion nuevaOrganizacion = new Organizacion("razonSocaial", TipoOrganizacion.Empresa,ClasificacionOrganizacion.EmpresaSectorPrimario,contactoOrganizacion);
        List<Organizacion> organizacionesActualizadas = organizacioneslActual;
        organizacionesActualizadas.add(nuevaOrganizacion);
        //WHEN CUANDO
        this.agenteSectorialTest.agregarOrganizacion(nuevaOrganizacion);
        //THEN ENTONCES
        Assertions.assertEquals(organizacioneslActual, organizacioneslActual);
        Assertions.assertEquals(organizacioneslActual,this.agenteSectorialTest.getOrganizaciones());
        //TODO TESTEAR ESTO CUANDO BAJEMOS LO QUE SUBIO TOMI
        //Assertions.assertEquals(organizacioneslActual.get(0).getAgenteSectorial(),this.agenteSectorialTest);
    }

    @Test
    public void crearAgente(){
        RepositorioAgentesDB repoagentesDB = new RepositorioAgentesDB();
        AgenteSectorial agenteSectorial= repoagentesDB.crearAgente("Nombre", "Territorio",TipoSectorTerritorial.Ministerio);

        Assertions.assertEquals(agenteSectorial.getNombre(), "Nombre");
        Assertions.assertEquals(agenteSectorial.getTerritorio(), "Territorio");
        Assertions.assertEquals(agenteSectorial.getTipoSectorTerritorial(), TipoSectorTerritorial.Ministerio);
    }

    @Test
    public void agregarOrganizacionAAgente(){
        Organizacion unaOrganizacion =  Common.getOrganizacionMinisterio();
        RepositorioAgentesDB repoagentesDB = new RepositorioAgentesDB();
        // AgenteSectorial agente=repoagentesDB.crearAgente("nombre", "territorio", TipoSectorTerritorial.Provincia);
        AgenteSectorial unAgente =  repoagentesDB.buscarAgente("nombre");
        repoagentesDB.agregarOrganizacionAAgente(unAgente, unaOrganizacion);
        Assertions.assertTrue(unAgente.getOrganizaciones().contains(unaOrganizacion));
    }
}
