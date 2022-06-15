package Domain.InterfazUsuario.API.Endpoints;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface Api {

  @GET("paises")
  Call<List<Pais>> paises(@Header("Authorization") String bearerAuth,@Query("offset") String offset);

  @GET("provincias")
  Call<List<Provincia>> provincias(@Header("Authorization") String bearerAuth, @Query("offset") String offset, @Query("paisId") String paisId);

  @GET("municipios")
  Call<List<Municipio>> municipios(@Header("Authorization") String bearerAuth, @Query("offset") String offset, @Query("provinciaId") String provinciaId);

  @GET("localidades")
  Call<List<Localidad>> localidades(@Header("Authorization") String bearerAuth, @Query("offset") String offset, @Query("municipioId") String municipioId);

  @GET("distancia")
  //https://ddstpa.com.ar/api/distancia?localidadOrigenId=1&calleOrigen=maipu&alturaOrigen=100&localidadDestinoId=457&calleDestino=O%27Higgins&alturaDestino=200
  Call<Distancia> distancia(@Header("Authorization") String bearerAuth,
                            @Query("localidadOrigenId") String localidadOrigenId,
                            @Query("calleOrigen") String calleOrigen,
                            @Query("alturaOrigen") String alturaOrigen,
                            @Query("localidadDestinoId") String localidadDestinoId,
                            @Query("calleDestino") String calleDestino,
                            @Query("alturaDestino") String alturaDestino
  );

  @POST("user")
  Call<User> postUser(@Body User user);

}
