package CalculadorHC;
import Domain.CalculadorHC.*;
import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.Espacios.TipoDireccion;
import Domain.MediosDeTransporte.TipoCombustible;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.Miembro.Miembro;
import Domain.Organizacion.*;
import Domain.ServicioMedicion.ServicioExcel;
import Domain.ServicioMedicion.TipoDeActividad;
import Domain.Usuarios.Contacto;
import Domain.Trayecto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

public class CalculadorHCTest {
    protected Contacto contacto = new Contacto("Organizacion", "ApellidoEmpresa", 987654321, "org@gmail.com");

    protected Organizacion organizacion = new Organizacion("A",TipoOrganizacion.Empresa,ClasificacionOrganizacion.EmpresaSectorPrimario, contacto, 5);
    protected Espacio espacio1 = new Direccion("Argentina", "Buenos Aires", "GENERAL LAVALLE", "GENERAL LAVALLE","Cordoba",3000, TipoDireccion.Trabajo);
    protected Espacio espacio2 = new Direccion("Argentina", "Buenos Aires", "GENERAL LAVALLE", "PAVON","O'Higgins",3000, TipoDireccion.Trabajo);
    protected Sector sector = new Sector("RRHH", espacio1, organizacion,new ArrayList<Miembro>());
    protected Miembro miembro = new Miembro("1", sector);
    protected AgenteSectorial agenteSectorial = new AgenteSectorial("Alguien", "Lugar", TipoSectorTerritorial.Provincia);


    @BeforeEach
    public void iniciarVariables()
    {
        Tramo tramo = new Tramo(espacio1, espacio2, new VehiculoParticular(TipoVehiculo.Auto, TipoCombustible.Nafta, 1));
        tramo.getMedioTransporte().setConsumoPorKm(1d);

        LocalDate fechaInicio =   LocalDate.of(2010, 1, 1);
        LocalDate fechaFin = LocalDate.of(2022, 12, 31);

        //Trayecto trayecto = new Trayecto(new ArrayList<Tramo>(), miembro);
        Trayecto trayecto = new Trayecto(new ArrayList<Tramo>(), miembro, 3, fechaInicio, fechaFin, true);
        trayecto.getTramos().add(tramo);
        miembro.setTrayectos(new ArrayList<Trayecto>());
        //miembro.getTrayectos().add(trayecto);
        miembro.agregarTrayecto(trayecto);

        organizacion.setServicioMediciones(ServicioExcel.getInstance());
        organizacion.setArchivoMediciones("example.xls");
        organizacion.getSectores().add(sector);
        sector.getMiembros().add(miembro);

        RepositorioFactores.getInstance().agregarFactorDeEmision(new FactorEmision(TipoDeActividad.COMBUSTION_MOVIL, 0.5, Unidad.lt));
        RepositorioFactores.getInstance().agregarFactorDeEmision(new FactorEmision(TipoDeActividad.COMBUSTION_FIJA, 2.0, Unidad.lt));

        agenteSectorial.getOrganizaciones().add(organizacion);
    }

    @Test
    public void calculoHCMiembroCorrectoVehiculoParticualr() throws IOException {
        Integer mes = 4;
        Integer anio = 2015;
        Double resultadoHC = miembro.calcularHC(mes, anio);
        Assertions.assertNotNull(resultadoHC);
        Assertions.assertNotEquals(0, resultadoHC);
    }

    @Test
    public void calculoHCOrganizacion() throws IOException
    {
        Integer mes = 4;
        Integer anio = 2015;

        Double resultadoHC = organizacion.calcularHC();
        Assertions.assertNotNull(resultadoHC);
        Assertions.assertNotEquals(0, resultadoHC);

        //TODO NO SE PUEDE TESTEAR QUE DEN IGUAL PORQUE COMO TIRA UN NUMERO AL AZAR CADA VEZ QUE SE HACE EL CALCULO DA DIFERENTE
//        Assertions.assertEquals(
//                organizacion.getSectores().get(0).getMiembros().get(0).calcularHC(mes, anio),
//                resultadoHC
//                );
    }

    @Test
    public void calculoHCAgenteSectoria() throws IOException
    {
        Double resultadoHC = agenteSectorial.calcularHC();
        Assertions.assertNotNull(resultadoHC);
        Assertions.assertNotEquals(0, resultadoHC);
    }
}
