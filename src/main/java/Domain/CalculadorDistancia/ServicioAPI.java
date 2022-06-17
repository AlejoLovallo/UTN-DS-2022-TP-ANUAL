package Domain.CalculadorDistancia;

import Domain.CalculadorDistancia.Endpoints.*;
import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.Espacios.Estacion;
import Domain.MediosDeTransporte.MedioDeTransporte;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioAPI extends ServicioDistancia{

  private static ServicioAPI instancia = null;
  private static String APP_URL = "https://ddstpa.com.ar/api/";
  private static String authToken;
  private Retrofit retrofit;

  private ServicioAPI() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(APP_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static void setAuthToken(String _authToken) {
    authToken = "Bearer " + _authToken;
  }

  public static ServicioAPI getInstance() {
    if (instancia == null) {
      instancia = new ServicioAPI();
    }
    return instancia;
  }

  public List<Pais> listadoDePaises(String offset) throws IOException {
    Api apiService = this.retrofit.create(Api.class);
    Call<List<Pais>> request = apiService.paises(authToken, offset);
    Response<List<Pais>> response = request.execute();
    System.out.println("ACA LLEGO BIEN");
    System.out.println(response.toString());
    List<Pais> listaPaises = response.body();
    ListadoPaises listado = ListadoPaises.getInstance();
    listado.setPaises(listaPaises);

    return listaPaises;
  }

  public List<Provincia> listadoDeProvincias(Pais unPais, String offset) throws IOException {
    Api apiService = this.retrofit.create(Api.class);
    Call<List<Provincia>> request = apiService.provincias(authToken, offset, unPais.getId());
    Response<List<Provincia>> response = request.execute();
    List<Provincia> listaDeProvincias = response.body();

    return listaDeProvincias;
  }

  public List<Municipio> listadoDeMunicipios(Provincia unaProvincia, String offset) throws IOException{
    Api apiService = this.retrofit.create(Api.class);
    Call<List<Municipio>> request = apiService.municipios(authToken,offset,unaProvincia.getId());
    Response<List<Municipio>> response = request.execute();
    List<Municipio> listadoDeMunicipios = response.body();

    return listadoDeMunicipios;
  }

  public List<Localidad> listadoDeLocalidades(Municipio unMunicipio, String offset) throws IOException {
    Api apiService = this.retrofit.create(Api.class);
    Call<List<Localidad>> request = apiService.localidades(authToken, offset, unMunicipio.getId());
    Response<List<Localidad>> response = request.execute();
    List<Localidad> listadoDeLocalidades = response.body();

    return listadoDeLocalidades;
  }

  public Distancia distancia(Localidad localidadOrigen,
                             Localidad localidadDestino,
                             String calleOrigen,
                             String alturaOrigen,
                             String calleDestino,
                             String alturaDestino)
      throws IOException{
    Api apiService = this.retrofit.create(Api.class);
    Call<Distancia> request = apiService.distancia(authToken,
        localidadOrigen.getId(),
        localidadDestino.getId(),
        calleOrigen,
        alturaOrigen,
        calleDestino,
        alturaDestino
    );
    Response<Distancia> response = request.execute();
    Distancia distancia = response.body();
    return distancia;
  }

  public Pais buscarPais(String _nombre, List<Pais> _listaPaises){
    for(int i = 0; i < _listaPaises.size(); i++){
      if(_nombre == _listaPaises.get(i).getNombre()){
        return _listaPaises.get(i);
      }
    }
    return null;
  }
  public Provincia buscarProvincia(String _nombre, List<Provincia> _listaProvincias){
    for(int i = 0; i < _listaProvincias.size(); i++){
      if(_nombre == _listaProvincias.get(i).getNombre()){
        return _listaProvincias.get(i);
      }
    }
    return null;
  }
  public Municipio buscarMunicipio(String _nombre, List<Municipio> _listaMunicipios){
    for(int i = 0; i < _listaMunicipios.size(); i++){
      if(_nombre == _listaMunicipios.get(i).getNombre()){
        return _listaMunicipios.get(i);
      }
    }
    return null;
  }
  public Localidad buscarLocalidad(String _nombre, List<Localidad> _listaLocalidades){
    for(int i = 0; i < _listaLocalidades.size(); i++){
      if(_nombre == _listaLocalidades.get(i).getNombre()){
        return _listaLocalidades.get(i);
      }
    }
    return null;
  }
  @Override
  public Double calcularDistancia(MedioDeTransporte _medioDeTransporte, Espacio _puntoPartida, Espacio _puntoLlegada) throws IOException {
    Double distanciaTotal = 0.0;

    Direccion puntoPartida = (Direccion) _puntoPartida;
    Direccion puntoLLegada = (Direccion) _puntoLlegada;

    ServicioAPI.setAuthToken("8MaZ8WdbaW+IhLyWfzV/T7oK54b6ILRxUDpW8+9Ba0c=");
    ServicioAPI servicio = ServicioAPI.getInstance();

    //CONSIGUE PAIS
    List<Pais> listaPaises = servicio.listadoDePaises("1");

    Pais paisPartida = buscarPais(puntoPartida.getPais(), listaPaises);
    Pais paisLlegada = buscarPais(puntoLLegada.getPais(), listaPaises);

    //CONSEGUIE PROVINCIAS
    List<Provincia> listaProvincias1 = servicio.listadoDeProvincias(paisPartida,"1");
    List<Provincia> listaProvincias2 = servicio.listadoDeProvincias(paisLlegada,"1");

    Provincia provinciaPartida = buscarProvincia(puntoPartida.getProvincia(), listaProvincias1);
    Provincia provinciaLlegada = buscarProvincia(puntoLLegada.getProvincia(), listaProvincias2);

    //CONSIGUE MUNICIPIOS
    List<Municipio> listaMunicipios1 = servicio.listadoDeMunicipios(provinciaPartida,"2");
    List<Municipio> listaMunicipios2 = servicio.listadoDeMunicipios(provinciaLlegada,"2");

    Municipio municipioPartida = buscarMunicipio(puntoPartida.getMunicipio(),listaMunicipios1);
    Municipio municipioLlegada = buscarMunicipio(puntoLLegada.getMunicipio(),listaMunicipios2);

    //CONSIGUE LOCALIDADES
    List<Localidad> listaLocalidades1 = servicio.listadoDeLocalidades(municipioPartida,"1");
    List<Localidad> listaLocalidades2 = servicio.listadoDeLocalidades(municipioPartida,"1");

    Localidad localidadPartida = buscarLocalidad(puntoPartida.getLocalidad(), listaLocalidades1);
    Localidad localidadLlegada = buscarLocalidad(puntoLLegada.getLocalidad(), listaLocalidades1);


    //CALCULA DISTANCIA
    Distancia ret = servicio.distancia(localidadPartida, localidadLlegada,
            puntoPartida.getCalle(), puntoPartida.getAltura().toString(),
            puntoLLegada.getCalle(), puntoLLegada.getAltura().toString());
    System.out.println("Valor: " + ret.getValor() + " " + ret.getUnidad());
    distanciaTotal = Double.parseDouble(ret.getValor());
    return distanciaTotal;
  }

  public static void main(String [] args) throws IOException {
    ServicioAPI.setAuthToken("8MaZ8WdbaW+IhLyWfzV/T7oK54b6ILRxUDpW8+9Ba0c=");
    ServicioAPI servicioDistancias = ServicioAPI.getInstance();
    List<Pais> paises = servicioDistancias.listadoDePaises("1");
    String paisNombre = paises.get(0).getNombre();
    System.out.println("NOMBRE OBTENIDO "+ paisNombre);
  }

}

