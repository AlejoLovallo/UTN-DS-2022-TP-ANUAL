package Domain.CalculadorDistancia;

import Domain.Espacios.Direccion;

import java.io.IOException;
import java.util.List;

public abstract class ServicioDistancia {

  public Double calcularDistancia(){

    Double distanciaTotal = 0.0;
    //API CALL
    //SE CONECTA
    ServicioAPI servicioAPI = servicioApi.getInstance();
    ServicioDistancia.setAuthToken("8KY5V6zOTDjCrAF8+4ZjnJ9Yn5f+qtTskOS7FVrlvos=");

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
      Direccion dir_inicial = (Direccion) puntoPartida;
      Direccion dir_destino = (Direccion) puntoLLegada;
      Distancia ret = servicioDistancias.distancia(lInicio,
              lFinal,
              dir_inicial.getCalle(), dir_inicial.getAltura().toString(),
              dir_destino.getCalle(), dir_destino.getAltura().toString()
      );
      System.out.println("Valor: " + ret.getValor() + " " + ret.getUnidad());
      distanciaTotal = Double.parseDouble(ret.getValor());
    }
    catch(IOException io)
    {
      System.out.println("Error io");
    }
    return distanciaTotal;
  }
}
