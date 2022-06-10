package Domain.API;

import Domain.API.Endpoints.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.List;

public class ServicioDistancias {

  private static ServicioDistancias instancia = null;
  private static String APP_URL = "https://ddstpa.com.ar/api/";
  private static String authToken;
  private Retrofit retrofit;

  private ServicioDistancias() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(APP_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static void setAuthToken(String _authToken) {
    authToken = "Bearer " + _authToken;
  }

  public static ServicioDistancias getInstance() {
    if (instancia == null) {
      instancia = new ServicioDistancias();
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

  public ListadoProvincias listadoDeProvincias(Pais unPais, String offset) throws IOException {
    Api apiService = this.retrofit.create(Api.class);
    Call<ListadoProvincias> request = apiService.provincias(authToken, offset, unPais.id());
    Response<ListadoProvincias> response = request.execute();
    ListadoProvincias listaDeProvincias = response.body();

    return listaDeProvincias;
  }

  public ListadoMunicipios listadoDeMunicipios(Provincia unaProvincia,String offset) throws IOException{
    Api apiService = this.retrofit.create(Api.class);
    Call<ListadoMunicipios> request = apiService.municipios(authToken,offset,unaProvincia.id());
    Response<ListadoMunicipios> response = request.execute();
    ListadoMunicipios listadoDeMunicipios = response.body();

    return listadoDeMunicipios;
  }

  public ListadoLocalidades listadoDeLocalidades(Municipio unMunicipio, String offset) throws IOException {
    Api apiService = this.retrofit.create(Api.class);
    Call<ListadoLocalidades> request = apiService.localidades(authToken, offset, unMunicipio.id());
    Response<ListadoLocalidades> response = request.execute();
    ListadoLocalidades listadoDeLocalidades = response.body();

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
    ServicioDistancias.setAuthToken("8MaZ8WdbaW+IhLyWfzV/T7oK54b6ILRxUDpW8+9Ba0c=");
    ServicioDistancias servicioDistancias = ServicioDistancias.getInstance();
    List<Pais> paises = servicioDistancias.listadoDePaises("1");
    String paisNombre = paises.get(0).getNombre();
    System.out.println("NOMBRE OBTENIDO "+ paisNombre);
  }

}
