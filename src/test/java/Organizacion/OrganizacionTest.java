package Organizacion;
import Domain.Espacios.Espacio;
import Domain.Espacios.TipoEspacio;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Organizacion.TipoOrganizacion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;

public class OrganizacionTest {
  protected Organizacion organizacionEmpresa;

  private void initializeOrganizacion(){
    this.organizacionEmpresa = new Organizacion("OrganizacionTest", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorPrimario);
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
    Assertions.assertNull(this.organizacionEmpresa.getMiembros());
    Assertions.assertNull(this.organizacionEmpresa.getSectores());
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
  public void setRegistrarSector(){
  Espacio espacio=new Espacio("Cordoba",3000, TipoEspacio.Trabajo);
  Sector sector1=new Sector("Administracion",espacio);
  Sector sector2=new Sector("direccion",espacio);

  this.organizacionEmpresa.registrarSector(sector1);
  this.organizacionEmpresa.registrarSector(sector2);

  Assertions.assertEquals(Arrays.asList(sector1,sector2),this.organizacionEmpresa.getSectores());
}

}