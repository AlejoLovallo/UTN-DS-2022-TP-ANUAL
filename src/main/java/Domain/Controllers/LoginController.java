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
          .toJson(new StandardResponse(StatusResponse.ERROR,c.getMessage())));
      response.status(200);
      return new Gson()
         .toJson(new StandardResponse(StatusResponse.ERROR,c.getMessage()));
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
    response.cookie("username",usuario.get().getUsername());
    //SesionManager sesionManager = SesionManager.get();
    //String idSesion = sesionManager.crearSesion("username", usuario.get().getUsername());



    if(usuario.get() instanceof Admin) {

      response.body(new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,"Pantalla Admin")));
      response.status(200);

      return new Gson()
          .toJson(new StandardResponse(StatusResponse.SUCCESS,"Pantalla Admin"));
    }

    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
    RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();


    Optional<Organizacion> organizacion = Optional.ofNullable(repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario.get()));
    Optional<Persona> persona = Optional.ofNullable(repositorioPersonasDB.buscarPersonaPorUsuario(usuario.get()));


    if(organizacion.isPresent()){
      //TODO mandar a la vista de organizacion
      response.cookie("organizacion",organizacion.get().getRazonSocial());


      response.status(210);
      response.body(new Gson()
          .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla organizacion")));


      return new Gson()
          .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla organizacion"));
    }
    if(persona.isPresent()){
      //TODO hacer que vaya a la vista de Persona
      response.cookie("persona",persona.get().getNroDocumento());
      response.body(new Gson()
          .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla persona")));
      response.status(200);

      return new Gson()
          .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla persona"));
    }
    response.body(new Gson()
        .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla persona")));
    response.status(200);

    return new Gson()
        .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla persona"));
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
}
