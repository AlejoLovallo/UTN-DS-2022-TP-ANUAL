package Domain.Middleware;

import Domain.Controllers.SesionManager;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.Map;
import java.util.Optional;

public class AuthMiddleware {
  public static void verificarSesion(Request request, Response response){
    Optional<String> idSesion = Optional.ofNullable(request.cookie("idSesion"));

    if (idSesion.isPresent()){
      //mandar a la sesion correspondiente
      SesionManager sesionManager = SesionManager.get();
      Map<String,Object> atributos = sesionManager.obtenerAtributos(idSesion.get());

      if(atributos != null){
        if (atributos.get("rol").equals("persona")){
          response.redirect("/menu_miembro");
        }
        if (atributos.get("rol").equals("organizacion")){
          response.redirect("/menu_organizacion");
        }
        if(atributos.get("rol").equals("usuarioDefault")){
          response.redirect("/menu_login");
        }
        if (atributos.get("rol").equals("admin")){
          response.redirect("/menu_login");
        }
      }
    }
  }
}
