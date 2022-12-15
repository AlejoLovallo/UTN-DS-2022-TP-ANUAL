package Domain.Controllers;

import Domain.JSON.ParserJSONUsuario;
import Domain.Miembro.Persona;
import Domain.Organizacion.Organizacion;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Admin;
import Domain.Usuarios.Excepciones.ContraseniaEsInvalidaException;
import Domain.Usuarios.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginController {

  //POST
  public String loguear(Request request, Response response) throws ParseException {
    JSONParser parser = new JSONParser();
    JSONObject pedido = (JSONObject) parser.parse(request.body());

    response.type("application/json");


    Optional<Usuario> usuario = Optional.ofNullable(ParserJSONUsuario.jsonToUsuarioPlano(pedido));

    if(!usuario.isPresent()){
      response.body(new Gson()
          .toJson(new StandardResponse(StatusResponse.ERROR,"error de JSON")));
      response.status(200);
      return new Gson()
          .toJson(new StandardResponse(StatusResponse.ERROR,"error de JSON"));
    }

    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

    try{
      usuario = Optional.ofNullable(repositorioUsuariosDB.validarLogueoUsuario(usuario.get().getUsername(),usuario.get().getContraSinHash()));
    }
    catch (ContraseniaEsInvalidaException c){
      response.body(new Gson()
          .toJson(new StandardResponse(StatusResponse.ERROR,"error de contra",new Gson()
              .toJsonTree(c.getMessage()))));
      response.status(200);
      return new Gson()
         .toJson(new StandardResponse(StatusResponse.ERROR,"error de contra",new Gson()
             .toJsonTree(c.getMessage())));
    }


    //Lo vuelvo a ver porque lo lleno con el validador de usuario
    if(!usuario.isPresent()){
      //TODO marcar error
      response.status(200);
      response.body(new Gson()
          .toJson(new StandardResponse(StatusResponse.ERROR,"error de usuario")));

      return new Gson()
          .toJson(new StandardResponse(StatusResponse.ERROR,"error de usuario"));
    }

    //Cambio esto por el session manager
    //response.cookie("username",usuario.get().getUsername());
    SesionManager sesionManager = SesionManager.get();
    String idSesion = sesionManager.crearSesion("username", usuario.get().getUsername());

    JsonElement sesionJson = new Gson().toJsonTree(idSesion);


    if(usuario.get() instanceof Admin) {

      sesionManager.agregarAtributo(idSesion,"rol","admin");
      //response.cookie("idSesion",idSesion);

      response.body(new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,"Pantalla Admin",sesionJson)));
      response.status(200);

      return new Gson()
          .toJson(new StandardResponse(StatusResponse.SUCCESS,"Pantalla Admin",sesionJson));
    }

    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
    RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();


    Optional<Organizacion> organizacion = Optional.ofNullable(repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario.get()));
    Optional<Persona> persona = Optional.ofNullable(repositorioPersonasDB.buscarPersonaPorUsuario(usuario.get()));


    if(organizacion.isPresent()){
      //Envia a la vista de organizacion
      //response.cookie("organizacion",organizacion.get().getRazonSocial());
      sesionManager.agregarAtributo(idSesion, "rol","organizacion");
      sesionManager.agregarAtributo(idSesion,"RazonSocial",organizacion.get().getRazonSocial());
      //response.cookie("idSesion",idSesion);

      response.status(210);
      response.body(new Gson()
          .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla organizacion",sesionJson)));
      return new Gson()
          .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla organizacion",sesionJson));
    }

    if(persona.isPresent()){
      //Envia a la vista de Persona
      //response.cookie("persona",persona.get().getNroDocumento());
      sesionManager.agregarAtributo(idSesion, "rol","persona");
      sesionManager.agregarAtributo(idSesion,"documento",persona.get().getNroDocumento());
      //response.cookie("idSesion",idSesion);

      response.body(new Gson()
          .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla persona",sesionJson)));
      response.status(200);

      return new Gson()
          .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla persona",sesionJson));
    }
    //Envia a la un mensaje de usuario default
    response.body(new Gson()
        .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla usuario default",sesionJson)));
    response.status(200);
    sesionManager.agregarAtributo(idSesion, "rol","usuarioDefault");
    //response.cookie("idSesion",idSesion);

    return new Gson()
        .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla usuario default",sesionJson));
  }

  //GET
  public ModelAndView loguearHtml (Request request, Response response) {
    Map<String, Object> parametros = new HashMap<>();
    return new ModelAndView(parametros,"index.html");
  }


  public ModelAndView menu_login (Request request, Response response) {
    Map<String, Object> parametros = new HashMap<>();
    return new ModelAndView(parametros,"index.html");
  }

//  public String logout(Request request, Response response){
//
//    //response.removeCookie("idSesion");
//    response.redirect("/menu_login");
//
//    return "";
//  }
}
