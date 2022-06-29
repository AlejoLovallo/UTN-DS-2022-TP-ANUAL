package Utils;

import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.Espacios.TipoDireccion;
import Domain.Espacios.TipoDireccion;
import Domain.MediosDeTransporte.TipoCombustible;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.Miembro.Miembro;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Organizacion.TipoOrganizacion;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import Domain.Usuarios.Usuario;

import java.util.ArrayList;

public class Common {
  public static Organizacion getOrganizacionMinisterio() {
    return new Organizacion("Ministerio test", TipoOrganizacion.Gubernamental, ClasificacionOrganizacion.Ministerio);
  }

  public static Organizacion getOrganizacionUniversidad() {
    return new Organizacion("Universidad test", TipoOrganizacion.Institucion, ClasificacionOrganizacion.Universidad);
  }

  public static Organizacion getOrganizacionEscuela() {
    return new Organizacion("Escuela test", TipoOrganizacion.Institucion, ClasificacionOrganizacion.Escuela);
  }

  public static Organizacion getOrganizacionEmpresaPrimaria() {
    return new Organizacion("Empresa primaria test", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorPrimario);
  }

  public static Organizacion getOrganizacionEmpresaSecundaria() {
    return new Organizacion("Empresa secundaria test", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorSecundario);
  }

  public static Sector getSectorTrabajo(){
    Espacio espacio = new Direccion("Argentina", "Buenos Aires", "CABA", "CABA","calle test",123, TipoDireccion.Trabajo);
    return new Sector("Sector test",espacio);
  }

  public static Sector getSector(String nombreSector, TipoDireccion tipoEspacio){
    Espacio espacio = new Direccion("Argentina", "Buenos Aires", "CABA", "CABA","esp test",123,tipoEspacio);
    return new Sector(nombreSector,espacio);
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
    return new Usuario("juan98", "juan98@gmail.com", "juan1998", true);
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

}


