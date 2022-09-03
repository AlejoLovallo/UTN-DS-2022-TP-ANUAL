package Organizacion;
import Administrador.AdminTest;
import Domain.CalculadorHC.CalculadorHC;
import Domain.CalculadorHC.FactorEmision;
import Domain.CalculadorHC.RepositorioFactores;
import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.Espacios.TipoDireccion;
import Domain.Miembro.Miembro;
import Domain.Organizacion.*;
import Domain.ServicioMedicion.FrecuenciaServicio;
import Domain.ServicioMedicion.ServicioExcel;
import Domain.Usuarios.Contacto;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class OrganizacionTest {
  protected Organizacion organizacionEmpresa;
  protected Contacto contacto = new Contacto("Organizacion", "ApellidoEmpresa", 987654321, "org@gmail.com");

  private void initializeOrganizacion(){
    this.organizacionEmpresa = new Organizacion("OrganizacionTest", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorPrimario, contacto, 5);
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
  RepositorioOrganizaciones repoOrg = null;

  Assertions.assertNull(repoOrg);
  Assertions.assertNotNull(repoOrg.GetInstance());
  }

  @Test
  public void RepoSectores(){
  RepositorioSectores repoSector = null;

  Assertions.assertNull(repoSector);
  Assertions.assertNotNull(repoSector.getInstance());
  }

  @Test
  public void subirReportesDeMediciones() throws IOException, ParseException {
    ArrayList< FactorEmision > factoresDeEmision = new ArrayList<>();
    factoresDeEmision.add(Common.getFactorDeEmision());
    RepositorioFactores.getInstance().setFactoresDeEmision(factoresDeEmision);
    organizacionEmpresa.setServicioMediciones(ServicioExcel.getInstance());
    organizacionEmpresa.setArchivoMediciones("example.xls");
    CalculadorHC.getInstance().procesarActividadAnual(organizacionEmpresa);



    System.out.println("Hay esta cantidad de Reportes: " + organizacionEmpresa.getReportes().size());

    for(int i = 0; i<organizacionEmpresa.getReportes().size(); i++){
      Double calculoHC = organizacionEmpresa.getReportes().get(i).getCalculoHC();
      int dia = organizacionEmpresa.getReportes().get(i).getFechaCarga().getDate();
      int mes = organizacionEmpresa.getReportes().get(i).getFechaCarga().getMonth();
      int anio = organizacionEmpresa.getReportes().get(i).getFechaCarga().getYear();
      System.out.println("EL CALCULO HC ES: " + calculoHC);
      System.out.println("EL DIA  ES: " + dia);
      System.out.println("EL MES ES: " + mes);
      System.out.println("EL ANIO ES: " + anio);
      System.out.println();
      System.out.println();
    }


    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    Assertions.assertEquals("01/05/2022", formato.format(organizacionEmpresa.getReportes().get(0).getFechaCarga()));
    Assertions.assertEquals("02/06/2021", formato.format(organizacionEmpresa.getReportes().get(1).getFechaCarga()));
    Assertions.assertEquals("03/07/2020", formato.format(organizacionEmpresa.getReportes().get(2).getFechaCarga()));
    Assertions.assertEquals("04/08/2019", formato.format(organizacionEmpresa.getReportes().get(3).getFechaCarga()));

    Assertions.assertEquals(organizacionEmpresa.getReportes().get(0).getPeriodicidad(), FrecuenciaServicio.MENSUAL);
    Assertions.assertEquals(organizacionEmpresa.getReportes().get(1).getPeriodicidad(), FrecuenciaServicio.ANUAL);
    Assertions.assertEquals(organizacionEmpresa.getReportes().get(2).getPeriodicidad(), FrecuenciaServicio.MENSUAL);
    Assertions.assertEquals(organizacionEmpresa.getReportes().get(3).getPeriodicidad(), FrecuenciaServicio.ANUAL);




    //Assertions.assertEquals(organizacionEmpresa.getReportes().get(0).getCalculoHC(), 30 * 2.0 * 1);


    //Assertions.assertEquals(organizacionEmpresa.getReportes().stream().filter(reporte -> reporte.getMes() == 5).findFirst().get().getCalculoHC(), 30 * 2.0 * 1);

    Assertions.assertEquals(organizacionEmpresa.calcularHC(), 7188.571720123291);

    //System.out.println("HC DE LA ORGANIZACION ES : " + organizacionEmpresa.calcularHC());

  }

}