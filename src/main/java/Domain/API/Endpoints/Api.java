package Domain.API.Endpoints;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface Api {

  @GET("paises")
  Call<List<Pais>> paises(@Query("offset") String offset);

  @GET("provincias")
  Call<ListadoProvincias> provincias(@Query("offset") String offset, @Query("paisId") String paisId);

  @GET("localidades")
  Call<ListadoLocalidades> localidades(@Query("offset") String offset, @Query("municipioId") String municipioId);

  @GET("municipios")
  Call<ListadoMunicipios> municipios(@Query("offset") String offset, @Query("provinciaId") String provinciaId);

  @GET("distancia")
  //https://ddstpa.com.ar/api/distancia?localidadOrigenId=1&calleOrigen=maipu&alturaOrigen=100&localidadDestinoId=457&calleDestino=O%27Higgins&alturaDestino=200
  Call<Distancia> distancia(@Query("localidadOrigenId") String localidadOrigenId,
                            @Query("alturaOrigen") String alturaOrigen,
                            @Query("localidadDestinoId") String localidadDestinoId,
                            @Query("calleDestino") String calleDestino,
                            @Query("alturaDestino") String alturaDestino
                            );

  @POST("user")
  Call<User> postUser(@Body User user);

}
