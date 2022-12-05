package AgenteSectorial;

import Domain.Organizacion.*;
import Domain.Repositorios.RepositorioAgentesDB;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Usuarios.Contacto;
import Utils.Common;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.stream.Collectors;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    public void agenteSectorialCreadoCorrectamente() {
        Assertions.assertEquals("AgenteSectorialTest",this.agenteSectorialTest.getNombre());
        Assertions.assertEquals("territorio", this.agenteSectorialTest.getTerritorio());
        Assertions.assertEquals(TipoSectorTerritorial.Ministerio, this.agenteSectorialTest.getTipoSectorTerritorial());
    }

    @Test
    @Order(1)
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
    @Order(2)
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
    @Order(3)
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
    @Order(4)
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
    @Order(5)
    public void crearAgente(){
        RepositorioAgentesDB repoagentesDB = new RepositorioAgentesDB();
        AgenteSectorial agenteSectorial= repoagentesDB.crearAgente("Nombre", "Territorio",TipoSectorTerritorial.Ministerio);

        Assertions.assertEquals(agenteSectorial.getNombre(), "Nombre");
        Assertions.assertEquals(agenteSectorial.getTerritorio(), "Territorio");
        Assertions.assertEquals(agenteSectorial.getTipoSectorTerritorial(), TipoSectorTerritorial.Ministerio);
    }


    @Test
    @Order(6)
    public void agregarOrganizacionAAgente(){
        Organizacion unaOrganizacion =  Common.getOrganizacionMinisterio();
        RepositorioAgentesDB repoagentesDB = new RepositorioAgentesDB();
        RepositorioOrganizacionesDB repoOrganizacionesDB = new RepositorioOrganizacionesDB();

        unaOrganizacion.setUsuario(Common.getUsuario());

        unaOrganizacion.getUsuario().setUsername(unaOrganizacion.getUsuario().getUsername()+" Org Prueba 456");



        unaOrganizacion = repoOrganizacionesDB.crearOrganizacion(unaOrganizacion.getRazonSocial()+" Prueba456",
                                                        unaOrganizacion.getClasificacion(),unaOrganizacion.getTipo(),
                                                        unaOrganizacion.getContacto(),
                                                        unaOrganizacion.getUsuario());

        // AgenteSectorial agente=repoagentesDB.crearAgente("nombre", "territorio", TipoSectorTerritorial.Provincia);


        AgenteSectorial unAgente =  repoagentesDB.buscarAgente("Nombre");


        repoagentesDB.agregarOrganizacionAAgente(unAgente, unaOrganizacion);
        List<Integer> organizacionesDeAgente = unAgente.getOrganizaciones().stream().map(organizacion -> organizacion.getId()).collect(Collectors.toList());
        Assertions.assertTrue(organizacionesDeAgente.contains(unaOrganizacion.getId()));
    }
}
