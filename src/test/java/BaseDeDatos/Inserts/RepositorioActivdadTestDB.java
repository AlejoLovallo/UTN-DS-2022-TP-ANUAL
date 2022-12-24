package BaseDeDatos.Inserts;


import Domain.CalculadorDistancia.Endpoints.Localidad;
import Domain.CalculadorDistancia.ServicioAPI;
import Domain.Espacios.Direccion;
import Domain.Espacios.Estacion;
import Domain.Espacios.TipoDireccion;
import Domain.MediosDeTransporte.TipoTransportePublico;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.TransportePublico;
import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.Organizacion.Actividad;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.TipoDeActividad;
import Domain.Organizacion.TipoDeConsumo;
import Domain.Reportes.GeneradorReportes;
import Domain.Repositorios.RepositorioDireccionDB;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Repositorios.RepositorioTransportePublicoDB;
import Domain.Repositorios.RepositorioVehiculoParticularDB;
import Domain.ServicioMedicion.ServicioExcel;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class RepositorioActivdadTestDB {
    public static void main (String args[]) throws IOException {
        //Cargar actividad
        /*RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();

        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorNombre("RazonSocial");

        organizacion.setServicioMediciones(ServicioExcel.getInstance());
        organizacion.cargarMedicionesInternas("example.xls");*/


        //Generar reporte
        /*
        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();

        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorNombre("RazonSocial");

        LocalDate fInicio = LocalDate.of(2019,3,1);
        LocalDate fFin = LocalDate.of(2019,7,1);

        GeneradorReportes.getInstance().reporteEvolucionHC_Org(organizacion,fInicio, fFin);

        System.exit(0);*/
        RepositorioVehiculoParticularDB repositorioVehiculoParticularDB = new RepositorioVehiculoParticularDB();
        VehiculoParticular v = repositorioVehiculoParticularDB.buscarVehiculoParticular( "Moto", "GNC",1, 2.0);

        RepositorioDireccionDB repositorioDireccionDB = new RepositorioDireccionDB();
        Direccion d1 = repositorioDireccionDB.buscarDireccion(
                "ARGENTINA",
                "BUENOS AIRES",
                "ADOLFO ALSINA",
                "CARHUE",
                "pepitoSandCompleto Barros1",
                123,
                "Trabajo"
        );

        Direccion d2 = repositorioDireccionDB.buscarDireccion(
                "ARGENTINA",
                "BUENOS AIRES",
                "ADOLFO ALSINA",
                "CARHUE",
                "pepitoSandCompleto Barros2",
                123,
                "Trabajo"
        );

        System.out.println("------------------VALOR DISTANCIA---------------------------");
        System.out.println(
        ServicioAPI.getInstance().calcularDistancia(v,d1,d2).toString());
        //System.exit(0);
    }
}
