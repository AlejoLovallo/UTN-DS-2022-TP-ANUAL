package Domain.CalculadorDistancia;

import Domain.CalculadorDistancia.Endpoints.*;
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
    Call<List<Provincia>> request = apiService.provincias(authToken, offset, unPais.id());
    Response<List<Provincia>> response = request.execute();
    List<Provincia> listaDeProvincias = response.body();

    return listaDeProvincias;
  }

<<<<<<< HEAD:src/main/java/Domain/InterfazUsuario/API/ServicioDistancias.java
  public List<Municipio> listadoDeMunicipios(Provincia unaProvincia, String offset) throws IOException{
=======
  public ListadoMunicipios listadoDeMunicipios(Provincia unaProvincia, String offset) throws IOException{
>>>>>>> develop:src/main/java/Domain/CalculadorDistancia/ServicioAPI.java
    Api apiService = this.retrofit.create(Api.class);
    Call<List<Municipio>> request = apiService.municipios(authToken,offset,unaProvincia.id());
    Response<List<Municipio>> response = request.execute();
    List<Municipio> listadoDeMunicipios = response.body();

    return listadoDeMunicipios;
  }

  public List<Localidad> listadoDeLocalidades(Municipio unMunicipio, String offset) throws IOException {
    Api apiService = this.retrofit.create(Api.class);
    Call<List<Localidad>> request = apiService.localidades(authToken, offset, unMunicipio.id());
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
        localidadOrigen.id(),
        localidadDestino.id(),
        calleOrigen,
        alturaOrigen,
        calleDestino,
        alturaDestino
    );
    Response<Distancia> response = request.execute();
    Distancia distancia = response.body();
    return distancia;
  }

  public static void main(String [] args) throws IOException {
    ServicioAPI.setAuthToken("8MaZ8WdbaW+IhLyWfzV/T7oK54b6ILRxUDpW8+9Ba0c=");
    ServicioAPI servicioDistancias = ServicioAPI.getInstance();
    List<Pais> paises = servicioDistancias.listadoDePaises("1");
    String paisNombre = paises.get(0).getNombre();
    System.out.println("NOMBRE OBTENIDO "+ paisNombre);
  }


  @Override
  public Double calcularDistancia(){
    return 0.0;
  }
}
