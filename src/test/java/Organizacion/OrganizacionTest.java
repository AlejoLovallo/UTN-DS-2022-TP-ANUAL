package Organizacion;
import Domain.CalculadorHC.FactorEmision;
import Domain.CalculadorHC.RepositorioFactores;
import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.Espacios.TipoDireccion;
import Domain.Miembro.Miembro;
import Domain.Organizacion.*;
import Domain.Organizacion.FrecuenciaServicio;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Repositorios.RepositorioSectoresDB;
import Domain.ServicioMedicion.ServicioExcel;
import Domain.Organizacion.TipoDeActividad;
import Domain.Organizacion.TipoDeConsumo;
import Domain.Usuarios.Contacto;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import Domain.CalculadorHC.CalculadorHC;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class OrganizacionTest {
  protected Organizacion organizacionEmpresa;
  protected Contacto contacto = new Contacto("Organizacion", "ApellidoEmpresa", 987654321, "org@gmail.com");

  private void initializeOrganizacion(){
    this.organizacionEmpresa = new Organizacion("OrganizacionTest", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorPrimario, contacto);
  }

  @BeforeEach
  public void initialize() {
    this.initializeOrganizacion();
  }

  @AfterEach
  public void clean(){

  }

  @Test
  public void organizacionCreadaCorrectamente() {
    Assertions.assertEquals("OrganizacionTest",this.organizacionEmpresa.getRazonSocial());
    Assertions.assertEquals(TipoOrganizacion.Empresa, this.organizacionEmpresa.getTipo());
    Assertions.assertEquals(ClasificacionOrganizacion.EmpresaSectorPrimario, this.organizacionEmpresa.getClasificacion());
    //Assertions.assertEquals(Arrays.asList(),this.organizacionEmpresa.getMiembros());
    Assertions.assertEquals(Arrays.asList(),this.organizacionEmpresa.getSectores());
    Assertions.assertEquals(this.contacto,this.organizacionEmpresa.getContacto());
  }

  @Test
  public void setRazonSocial(){
    String razonsocialActual=this.organizacionEmpresa.getRazonSocial();
    String razonsocialNueva="ORG_SA";

    this.organizacionEmpresa.setRazonSocial(razonsocialNueva);

    Assertions.assertEquals(razonsocialNueva,this.organizacionEmpresa.getRazonSocial());
    Assertions.assertEquals(razonsocialActual,"OrganizacionTest");
  }

  @Test
  public void setTipo(){
    //GIVEN DADO
    TipoOrganizacion tipoActual = this.organizacionEmpresa.getTipo();
    TipoOrganizacion nuevoTipo = TipoOrganizacion.Gubernamental;
    //WHEN CUANDO
    this.organizacionEmpresa.setTipo(nuevoTipo);
    //THEN ENTONCES
    Assertions.assertEquals(tipoActual,TipoOrganizacion.Empresa);
    Assertions.assertEquals(nuevoTipo,this.organizacionEmpresa.getTipo());
  }

  @Test
  public void setClasificacion(){
    ClasificacionOrganizacion clasificActual=this.organizacionEmpresa.getClasificacion();
    ClasificacionOrganizacion clasificNueva=ClasificacionOrganizacion.Ministerio;

    this.organizacionEmpresa.setClasificacion(clasificNueva);

    Assertions.assertEquals(clasificActual, ClasificacionOrganizacion.EmpresaSectorPrimario);
    Assertions.assertEquals(clasificNueva,this.organizacionEmpresa.getClasificacion());

  }

  @Test
  public void setContacto(){
    //GIVEN DADO
    Contacto contactoActual = this.organizacionEmpresa.getContacto();
    Contacto nuevoContacto = new Contacto("NuevoNombre", "NuevoApellido", 123456, "Nuevo@gmail.com");
    //WHEN CUANDO
    this.organizacionEmpresa.setContacto(nuevoContacto);
    //THEN ENTONCES
    Assertions.assertEquals(contactoActual,this.contacto);
    Assertions.assertEquals(nuevoContacto,this.organizacionEmpresa.getContacto());
  }

  @Test
  public void setAgenteSectorial(){
    //GIVEN DADO
    AgenteSectorial nuevoAgenteSectorial = new AgenteSectorial("NuevoNombre", "NuevoApellido", TipoSectorTerritorial.Ministerio);
    //WHEN CUANDO
    this.organizacionEmpresa.setAgenteSectorial(nuevoAgenteSectorial);
    //THEN ENTONCES
    Assertions.assertEquals(nuevoAgenteSectorial,this.organizacionEmpresa.getAgenteSectorial());
  }

  @Test
  public void setRegistrarSector(){

  Espacio espacio=new Direccion("Argentina", "Buenos Aires", "CABA", "CABA","Cordoba",3000, TipoDireccion.Trabajo);
  Sector sector1=new Sector("Administracion",espacio, organizacionEmpresa,new ArrayList<Miembro>());
  Sector sector2=new Sector("direccion", espacio,organizacionEmpresa,new ArrayList<Miembro>());


  this.organizacionEmpresa.registrarSector(sector1);
  this.organizacionEmpresa.registrarSector(sector2);

  Assertions.assertEquals(Arrays.asList(sector1,sector2),this.organizacionEmpresa.getSectores());
}

  @Test
  public void aceptarvinculacion(){
    ArrayList<Miembro> miembros = Common.getMiembros(2);

    ArrayList<Sector> sectores = new ArrayList<>();
    Sector sector = Common.getSector( "nombreSector", TipoDireccion.Trabajo);
    sectores.add(sector);

    organizacionEmpresa.setSectores(sectores);

    miembros.forEach(miembro -> this.organizacionEmpresa.aceptarVinculacion(miembro, organizacionEmpresa.getSectores().get(0)));

    Assertions.assertEquals(Arrays.asList(miembros.get(0),miembros.get(1)),this.organizacionEmpresa.getSectores().get(0).getMiembros());
    Assertions.assertTrue(this.organizacionEmpresa.aceptarVinculacion(miembros.get(0), organizacionEmpresa.getSectores().get(0)));
    Assertions.assertTrue(this.organizacionEmpresa.aceptarVinculacion(miembros.get(1), organizacionEmpresa.getSectores().get(0)));
  }

@Test
  public void RepoOrganizaciones(){
  RepositorioOrganizacionesDB repoOrg = null;

  Assertions.assertNull(repoOrg);
  }

  @Test
  public void RepoSectores(){
  RepositorioSectoresDB repoSector = null;

  Assertions.assertNull(repoSector);
  }
/*
  //TODO volver a testear
  @Test
  public void subirReportesDeMediciones() throws IOException, ParseException {
    ArrayList<FactorEmision> factoresDeEmision = new ArrayList<>();
    factoresDeEmision.add(Common.getFactorDeEmision());
    RepositorioFactores.getInstance().setFactoresDeEmision(factoresDeEmision);
    organizacionEmpresa.setServicioMediciones(ServicioExcel.getInstance());
    organizacionEmpresa.setArchivoMediciones("example.xls");
    CalculadorHC.getInstance().procesarActividadAnual(organizacionEmpresa);

    Assertions.assertEquals(organizacionEmpresa.getActividades().get(0).getTipoDeActividad(), TipoDeActividad.COMBUSTION_FIJA);
    Assertions.assertEquals(organizacionEmpresa.getActividades().get(1).getTipoDeActividad(), TipoDeActividad.COMBUSTION_FIJA);
    Assertions.assertEquals(organizacionEmpresa.getActividades().get(2).getTipoDeActividad(), TipoDeActividad.COMBUSTION_FIJA);
    Assertions.assertEquals(organizacionEmpresa.getActividades().get(3).getTipoDeActividad(), TipoDeActividad.COMBUSTION_FIJA);

    Assertions.assertEquals(organizacionEmpresa.getActividades().get(0).getTipoDeConsumo(), TipoDeConsumo.NAFTA);
    Assertions.assertEquals(organizacionEmpresa.getActividades().get(1).getTipoDeConsumo(), TipoDeConsumo.CARBON);
    Assertions.assertEquals(organizacionEmpresa.getActividades().get(2).getTipoDeConsumo(), TipoDeConsumo.NAFTA);
    Assertions.assertEquals(organizacionEmpresa.getActividades().get(3).getTipoDeConsumo(), TipoDeConsumo.CARBON);

    Assertions.assertEquals(organizacionEmpresa.getActividades().get(0).getConsumo(),30.0);
    Assertions.assertEquals(organizacionEmpresa.getActividades().get(1).getConsumo(), 100.0);
    Assertions.assertEquals(organizacionEmpresa.getActividades().get(2).getConsumo(), 40.0);
    Assertions.assertEquals(organizacionEmpresa.getActividades().get(3).getConsumo(), 4500.0);

    Assertions.assertEquals(organizacionEmpresa.getActividades().get(0).getFrecuenciaServicio(), FrecuenciaServicio.MENSUAL);
    Assertions.assertEquals(organizacionEmpresa.getActividades().get(1).getFrecuenciaServicio(), FrecuenciaServicio.ANUAL);
    Assertions.assertEquals(organizacionEmpresa.getActividades().get(2).getFrecuenciaServicio(), FrecuenciaServicio.MENSUAL);
    Assertions.assertEquals(organizacionEmpresa.getActividades().get(3).getFrecuenciaServicio(), FrecuenciaServicio.ANUAL);

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    Assertions.assertEquals("01/05/2022", formato.format(organizacionEmpresa.getActividades().get(0).getFechaCarga()));
    Assertions.assertEquals("02/06/2021", formato.format(organizacionEmpresa.getActividades().get(1).getFechaCarga()));
    Assertions.assertEquals("03/07/2020", formato.format(organizacionEmpresa.getActividades().get(2).getFechaCarga()));
    Assertions.assertEquals("04/08/2019", formato.format(organizacionEmpresa.getActividades().get(3).getFechaCarga()));







//ESTO VA EN CALCULADORHCTEST calculoHCOrganizacion()

//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(0).obtenercantidadHCTotal(), 240.0);
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(1).obtenercantidadHCTotal(), 250.00000000000003);
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(2).obtenercantidadHCTotal(), 2080.0);
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(3).obtenercantidadHCTotal(), 27750.0);
//
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(0).obtenercantidadHC(4, 2022), 0.0);
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(0).obtenercantidadHC(5, 2022), 60.0);
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(0).obtenercantidadHC(6, 2022), 60.0);
//
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(1).obtenercantidadHC(5, 2021), 0.0);
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(1).obtenercantidadHC(6, 2021), 16.666666666666668);
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(1).obtenercantidadHC(7, 2021), 16.666666666666668);
//
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(2).obtenercantidadHC(6, 2020), 0.0);
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(2).obtenercantidadHC(7, 2020), 80.0);
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(2).obtenercantidadHC(8, 2020), 80.0);
//
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(3).obtenercantidadHC(7, 2019), 0.0);
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(3).obtenercantidadHC(8, 2019), 750.0);
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(3).obtenercantidadHC(9, 2019), 750.0);
//    Assertions.assertEquals(organizacionEmpresa.getActividades().get(3).obtenercantidadHC(4, 2022), 750.0);
//
//    Assertions.assertEquals(organizacionEmpresa.calcularHCAA(), 30320.0);
//    Assertions.assertEquals(organizacionEmpresa.calcularHCMesAnio(4, 2019), 0.0);
//    Assertions.assertEquals(organizacionEmpresa.calcularHCMesAnio(8, 2019), 750.0);
//    Assertions.assertEquals(organizacionEmpresa.calcularHCMesAnio(7, 2020), 830.0);
//    Assertions.assertEquals(organizacionEmpresa.calcularHCMesAnio(6, 2021), 846.6666666666666);
//    Assertions.assertEquals(organizacionEmpresa.calcularHCMesAnio(5, 2022), 906.6666666666667);

  }*/

}