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
import Domain.Repositorios.RepositorioFactoresEmisionDB;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import Domain.ServicioMedicion.ServicioExcel;
import Domain.Organizacion.TipoDeActividad;
import Domain.Usuarios.Contacto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CalculadorHCTest {
    protected Contacto contacto = new Contacto("Organizacion", "ApellidoEmpresa", 987654321, "org@gmail.com");

    protected Organizacion organizacion = new Organizacion("A", TipoOrganizacion.Empresa, ClasificacionOrganizacion.EmpresaSectorPrimario, contacto);
    protected Espacio espacio1 = new Direccion("Argentina", "Buenos Aires", "GENERAL LAVALLE", "GENERAL LAVALLE","Cordoba",3000, TipoDireccion.Trabajo);
    protected Espacio espacio2 = new Direccion("Argentina", "Buenos Aires", "GENERAL LAVALLE", "PAVON","O'Higgins",3000, TipoDireccion.Trabajo);
    protected Sector sector = new Sector("RRHH", espacio1, organizacion,new ArrayList<Miembro>());
    protected Miembro miembro = new Miembro("1", sector);
    protected AgenteSectorial agenteSectorial = new AgenteSectorial("Alguien", "Lugar", TipoSectorTerritorial.Provincia);


    @BeforeEach
    public void iniciarVariables()
    {
        RepositorioFactoresEmisionDB repositorioFactoresEmisionDB = new RepositorioFactoresEmisionDB();

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

        repositorioFactoresEmisionDB.agregarFactorDeEmision(new FactorEmision(TipoDeActividad.COMBUSTION_MOVIL, 0.5, Unidad.lt));
        repositorioFactoresEmisionDB.agregarFactorDeEmision(new FactorEmision(TipoDeActividad.COMBUSTION_FIJA, 2.0, Unidad.lt));

        agenteSectorial.getOrganizaciones().add(organizacion);
    }

    @AfterEach
    public void clean(){

    }

    @Test
    public void calculoHCMiembroCorrectoVehiculoParticualr() throws IOException {
        Integer mes = 4;
        Integer anio = 2015;
        Double resultadoHC = miembro.calcularHC(mes, anio);
        Assertions.assertNotNull(resultadoHC);
        Assertions.assertNotEquals(0, resultadoHC);
    }
/*

    @Test
    public void calculoHCOrganizacion() throws IOException, ParseException {
        ArrayList<FactorEmision> factoresDeEmision = new ArrayList<>();
        factoresDeEmision.add(Common.getFactorDeEmision());
        factoresDeEmision.add(Common.getFactorDeEmisionCOMBUSTION_MOVIL());
        RepositorioFactores.getInstance().setFactoresDeEmision(factoresDeEmision);
        organizacion.setServicioMediciones(ServicioExcel.getInstance());
        organizacion.setArchivoMediciones("example.xls");
        CalculadorHC.getInstance().procesarActividadAnual(organizacion);

        Double resultadoHC = organizacion.calcularHCAA();

        //System.out.println("organizacion.calcularHCAA() = " + resultadoHC);
        Assertions.assertNotNull(resultadoHC);
        Assertions.assertNotEquals(0, resultadoHC);



        Assertions.assertEquals(organizacion.getActividades().get(0).obtenercantidadHCTotal(), 240.0);
        Assertions.assertEquals(organizacion.getActividades().get(1).obtenercantidadHCTotal(), 250.00000000000003);
        Assertions.assertEquals(organizacion.getActividades().get(2).obtenercantidadHCTotal(), 2080.0);
        Assertions.assertEquals(organizacion.getActividades().get(3).obtenercantidadHCTotal(), 27750.0);

        Assertions.assertEquals(organizacion.getActividades().get(0).obtenercantidadHC(4, 2022), 0.0);
        Assertions.assertEquals(organizacion.getActividades().get(0).obtenercantidadHC(5, 2022), 60.0);
        Assertions.assertEquals(organizacion.getActividades().get(0).obtenercantidadHC(6, 2022), 60.0);

        Assertions.assertEquals(organizacion.getActividades().get(1).obtenercantidadHC(5, 2021), 0.0);
        Assertions.assertEquals(organizacion.getActividades().get(1).obtenercantidadHC(6, 2021), 16.666666666666668);
        Assertions.assertEquals(organizacion.getActividades().get(1).obtenercantidadHC(7, 2021), 16.666666666666668);

        Assertions.assertEquals(organizacion.getActividades().get(2).obtenercantidadHC(6, 2020), 0.0);
        Assertions.assertEquals(organizacion.getActividades().get(2).obtenercantidadHC(7, 2020), 80.0);
        Assertions.assertEquals(organizacion.getActividades().get(2).obtenercantidadHC(8, 2020), 80.0);

        Assertions.assertEquals(organizacion.getActividades().get(3).obtenercantidadHC(7, 2019), 0.0);
        Assertions.assertEquals(organizacion.getActividades().get(3).obtenercantidadHC(8, 2019), 750.0);
        Assertions.assertEquals(organizacion.getActividades().get(3).obtenercantidadHC(9, 2019), 750.0);
        Assertions.assertEquals(organizacion.getActividades().get(3).obtenercantidadHC(4, 2022), 750.0);

        //Assertions.assertEquals(organizacion.calcularHCAA(), 30320.0);
//        Assertions.assertEquals(organizacion.calcularHCMesAnio(4, 2019), 0.0);
//        Assertions.assertEquals(organizacion.calcularHCMesAnio(8, 2019), 750.0);
//        Assertions.assertEquals(organizacion.calcularHCMesAnio(7, 2020), 830.0);
//        Assertions.assertEquals(organizacion.calcularHCMesAnio(6, 2021), 846.6666666666666);
//        Assertions.assertEquals(organizacion.calcularHCMesAnio(5, 2022), 906.6666666666667);
    }*/
/*
    //TODO volver a testear
    @Test
    public void calculoHCAgenteSectoria() throws IOException
    {
        Double resultadoHC = agenteSectorial.calcularHC();
        Assertions.assertNotNull(resultadoHC);
        Assertions.assertNotEquals(0, resultadoHC);
    }*/
}
