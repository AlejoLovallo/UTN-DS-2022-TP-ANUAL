package Utils;

import Domain.CalculadorHC.FactorEmision;
import Domain.CalculadorHC.Unidad;
import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.Espacios.TipoDireccion;
import Domain.MediosDeTransporte.TipoCombustible;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.Miembro.Miembro;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Organizacion.TipoOrganizacion;
import Domain.Organizacion.TipoDeActividad;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import Domain.Usuarios.Usuario;

import java.util.ArrayList;

public class Common {
  public static Organizacion getOrganizacionMinisterio() {
    return new Organizacion("Ministerio test", TipoOrganizacion.Gubernamental, ClasificacionOrganizacion.Ministerio, null);
  }

  public static Organizacion getOrganizacionUniversidad() {
    return new Organizacion("Universidad test", TipoOrganizacion.Institucion, ClasificacionOrganizacion.Universidad, null);
  }

  public static Organizacion getOrganizacionEscuela() {
    return new Organizacion("Escuela test", TipoOrganizacion.Institucion, ClasificacionOrganizacion.Escuela, null);
  }

  public static Organizacion getOrganizacionConUnMiembro() {
    Organizacion organizacion = new Organizacion("Escuela test", TipoOrganizacion.Institucion, ClasificacionOrganizacion.Escuela, null);
    ArrayList<Sector> sectores = new ArrayList<>();
    Sector sector = Common.getMiembro().getSector();
    sector.getMiembros().add(Common.getMiembro());
    sectores.add(sector);
    organizacion.setSectores(sectores);
    return organizacion;
  }


  public static Organizacion getOrganizacionEmpresaPrimaria() {
    return new Organizacion("Empresa primaria test", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorPrimario, null);
  }

  public static Organizacion getOrganizacionEmpresaSecundaria() {
    return new Organizacion("Empresa secundaria test", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorSecundario, null);
  }

  public static Sector getSectorTrabajo(){
    Espacio espacio = new Direccion("Argentina", "Buenos Aires", "CABA", "CABA","calle test",123, TipoDireccion.Trabajo);
    return new Sector("Sector test",espacio, null, new ArrayList<Miembro>());
  }

  public static Sector getSector(String nombreSector, TipoDireccion tipoEspacio){
    Espacio espacio = new Direccion("Argentina", "Buenos Aires", "CABA", "CABA","esp test",123,tipoEspacio);
    return new Sector(nombreSector,espacio, null, new ArrayList<Miembro>());
  }

  public static Organizacion initializeOrganizacion(Organizacion org, Sector sector) {
    org.registrarSector(sector);
    return org;
  }

  public static ArrayList<Miembro> getMiembros(Integer cantMiembros) {
    ArrayList<Miembro> listaDeMiembros = new ArrayList<Miembro>();
    for (int i = 0; i < cantMiembros; i++) {
      Sector sector = getSector("Sector" + Integer.toString(i), TipoDireccion.Trabajo);
      listaDeMiembros.add(new Miembro("miembro" + Integer.toString(i), sector));
    }
    return listaDeMiembros;
  }

  public static Usuario getUsuarioConContraseniaValida(){
    return new Usuario("usuario", "email", "nuevaContraseniaValida", true);
  }


  public static Usuario getUsuario() {
    return new Usuario("juan98", "email", "juan1998", true);
  }
  public static Usuario getUsuarioQueEsteEnElRepo() {
    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

    return repositorioUsuariosDB.crearUsuario("juan98", "email", "juan1998", true);
  }




  public static Miembro getMiembro() {
    Organizacion orgTest = Common.getOrganizacionEmpresaPrimaria();
    Sector sectorTest = Common.getSectorTrabajo();
    orgTest = Common.initializeOrganizacion(orgTest,sectorTest);
    Miembro unMiembro = new Miembro("miembro1",sectorTest);

    return unMiembro;
  }


  public static ArrayList<Trayecto> getTrayectos(Integer cantTrayectos) {
    ArrayList<Trayecto> listaDeTrayectos = new ArrayList<Trayecto>();
    for (int i = 0; i < cantTrayectos; i++) {
      listaDeTrayectos.add(new Trayecto());
    }
    return listaDeTrayectos;
  }

  public static Tramo getTramoTest(String calle1, String calle2) {

    Direccion direccionTest1 = new Direccion("Argentina", "BsAs", "Lomas", "Banfield", calle1 , 2, TipoDireccion.Vivienda);
    Direccion direccionTest2 = new Direccion("Argentina", "BsAs", "Capital", "Almagro", calle2 , 2, TipoDireccion.Trabajo);
    VehiculoParticular vehiculoTest = new VehiculoParticular(TipoVehiculo.Auto, TipoCombustible.Nafta,2);
    //Integer cantPasajeros = 4;
    Tramo tramoEjemplo = new Tramo(direccionTest1, direccionTest2, vehiculoTest);

    return tramoEjemplo;
  }

  public static FactorEmision getFactorDeEmision() {
    return new FactorEmision(TipoDeActividad.COMBUSTION_FIJA, 2.0, Unidad.m3);
  }

    public static FactorEmision getFactorDeEmisionCOMBUSTION_MOVIL() {
      return new FactorEmision(TipoDeActividad.COMBUSTION_MOVIL, 2.0, Unidad.m3);
    }
}


