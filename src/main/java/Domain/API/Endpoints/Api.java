package Domain.API.Endpoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface Api {

  @GET("paises")
  Call<List<Pais>> paises(@Query("offset") String offset);

  @GET("provincias")
  Call<ListadoProvincias> provincias(@Query("offset") String offset, @Query("paisId") String paisId);


}
