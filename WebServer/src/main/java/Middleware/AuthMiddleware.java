package Middleware;
import spark.Request;
import spark.Response;

public class AuthMiddleware {

  public static void verificarSesion(Request request, Response response){
    if(!request.session().isNew()){
      response.redirect("/menu_logueado");
    }
  }
}