package Domain.Trayecto;

import Domain.Espacios.Direccion;
import Domain.Espacios.*;
import Domain.InterfazUsuario.API.Endpoints.*;
import Domain.MediosDeTransporte.TipoCombustible;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.InterfazUsuario.API.ServicioDistancias;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TramoVehiculoParticular extends Tramo {

    private Direccion puntoPartida;
    private Direccion puntoLLegada;
    private VehiculoParticular vehiculoParticular;

    //////////////////////////////////  CONSTRUCTOR

    public TramoVehiculoParticular(Direccion puntoPartida, Direccion puntoLLegada, VehiculoParticular vehiculoParticular) {
        this.puntoPartida = puntoPartida;
        this.puntoLLegada = puntoLLegada;
        this.vehiculoParticular = vehiculoParticular;
    }

    //////////////////////////////////  GETTERS
    public Espacio getPuntoLLegada() {
        return puntoLLegada;
    }

    public Espacio getPuntoPartida() {
        return puntoPartida;
    }

    public VehiculoParticular getVehiculoParticular() {
        return vehiculoParticular;
    }

    //////////////////////////////////  SETTERS

    public void setPuntoPartida(Direccion puntoPartida) {
        this.puntoPartida = puntoPartida;
    }

    public void setPuntoLLegada(Direccion puntoLLegada) {
        this.puntoLLegada = puntoLLegada;
    }

    public void setVehiculoParticular(VehiculoParticular vehiculoParticular) {
        this.vehiculoParticular = vehiculoParticular;
    }


    //////////////////////////////////  INTERFACE



    public Distancia determinarDistancia(String provincia,
                                         String municipio,
                                         String localidadInicio,
                                         String localidadFinal,
                                         String calleOrigen, String alturaOrigen,
                                         String calleDestino, String alturaDestino){
        //SE CONECTA
        ServicioDistancias servicioDistancias = ServicioDistancias.getInstance();
        ServicioDistancias.setAuthToken("8KY5V6zOTDjCrAF8+4ZjnJ9Yn5f+qtTskOS7FVrlvos=");

        try
        {
            //CONSEGUIE PAIS (solo consigue Argentina)
            List<Pais> paises = servicioDistancias.listadoDePaises("1");
            System.out.println("Consiguio pais");

            /* NOTA: si se busca por nombre deberia buscarse en todas las paginas de la api
            (todos los offset)
            */

            //CONSEGUIE PROVINCIAS
            List<Provincia> listaProvincias = servicioDistancias.listadoDeProvincias(paises.get(0),"1");
            System.out.println("Consiguio provincias");

            //BUSCAR PROVINCIA POR NOMBRE
            //Optional<Provincia> provincia1 = listaProvincias.stream()
            //        .filter(unaProvincia -> unaProvincia.getNombre().equals(provincia))
            //        .findAny();
            Provincia provincia1 = listaProvincias.get(0);

            //CONSIGUE MUNICIPIOS
            List<Municipio> listaMunicipios = servicioDistancias.listadoDeMunicipios(provincia1,"2");
            System.out.println("Consiguio municipios");

            //BUSCAR MUNICIPIO POR NOMBRE
            //Optional<Municipio> municipio1 = listaMunicipios.stream()
            //        .filter(unMunicipio -> unMunicipio.getNombre().equals(municipio))
            //        .findAny();
            Municipio municipio1 = listaMunicipios.get(0);

            //CONSIGUE LOCALIDADES
            List<Localidad> listaLocalidades = servicioDistancias.listadoDeLocalidades(municipio1, "1");
            System.out.println("Consiguio localidades");

            //BUSCAR LOCALIDAD POR NOMBRE
            //Optional<Localidad> localidad1 = listaLocalidades.stream()
            //        .filter(unaLocalidad -> unaLocalidad.getNombre().equals(localidadInicio))
            //        .findAny();
            //Optional<Localidad> localidad2 = listaLocalidades.stream()
            //    .filter(unaLocalidad -> unaLocalidad.getNombre().equals(localidadFinal))
            //    .findAny();

            Localidad lInicio = listaLocalidades.get(0);
            Localidad lFinal = listaLocalidades.get(1);

            //CALCULA DISTANCIA
            Distancia ret = servicioDistancias.distancia(lInicio,
                    lFinal,
                    puntoPartida.getCalle(), puntoPartida.getAltura().toString(),
                    puntoLLegada.getCalle(), puntoLLegada.getAltura().toString()
            );
            System.out.println("Valor: " + ret.getValor() + " " + ret.getUnidad());
            return ret;
        }
        catch(IOException io)
        {
            System.out.println("Error io");
        }

        double distanciaTotal = 0;
        return null;
    }
    
    /*public static void main(String[] args) {
        Direccion dir_origen = new Direccion("maipu", 100, TipoDireccion.Trabajo);
        Direccion dir_destino = new Direccion("O'Higgins", 200, TipoDireccion.Trabajo);
        VehiculoParticular vp = new VehiculoParticular(TipoVehiculo.Auto, TipoCombustible.Gasoil, 1);

        TramoVehiculoParticular aux = new TramoVehiculoParticular(dir_origen, dir_destino,vp);
        aux.determinarDistancia("BUENOS AIRES",
                "ADOLFO ALSINA",
                "PUERTO LEONI",
                "PUERTO RICO",
                dir_origen.getCalle(),
                dir_origen.getAltura().toString(),
                dir_destino.getCalle(),
                dir_destino.getAltura().toString());
        System.exit(0);
    }*/
}
