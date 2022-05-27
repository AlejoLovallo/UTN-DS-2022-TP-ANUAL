package Organizacion;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.TipoOrganizacion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

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

}