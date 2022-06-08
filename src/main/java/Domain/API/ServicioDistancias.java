package Domain.API;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioDistancias {

  private static ServicioDistancias instancia = null;
  private static String APP_URL = "https://api.mercadolibre.com/";
  private Retrofit retrofit;

  private ServicioDistancias(){
    this.retrofit = new Retrofit.Builder()
        .baseUrl(APP_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static ServicioDistancias getInstance(){
    if(instancia== null){
      instancia = new ServicioDistancias();
    }
    return instancia;
  }

}
