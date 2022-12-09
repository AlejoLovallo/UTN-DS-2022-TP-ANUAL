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
  public JSONObject loguear(Request request, Response response) throws ParseException {
    JSONParser parser = new JSONParser();
    JSONObject pedido = (JSONObject) parser.parse(request.body());

    response.type("application/json");


    Optional<Usuario> usuario = Optional.ofNullable(ParserJSONUsuario.jsonToUsuarioPlano(pedido));

    if(!usuario.isPresent()){
      //response.body("error de JSON");
      //response.status(404);
      return new StandardResponse(StatusResponse.ERROR,"error de JSON").toJson();
    }

    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
    /*if(!repositorioUsuariosDB.existe(usuario.get().getUsername())){
      //TODO marcar error
      response.redirect("/menu_login");
      return "no existe un usuario con ese username";
    }*/

    try{
      usuario = Optional.ofNullable(repositorioUsuariosDB.validarLogueoUsuario(usuario.get().getUsername(),usuario.get().getContraSinHash()));
    }
    catch (ContraseniaEsInvalidaException c){
      //response.body(c.getMessage());
      //response.status(404);
      return new StandardResponse(StatusResponse.ERROR,c.getMessage()).toJson();
    }


    //Lo vuelvo a ver porque lo lleno con el validador de usuario
    if(!usuario.isPresent()){
      //TODO marcar error
      //response.body("error de usuario");
      //response.status(404);
      return new StandardResponse(StatusResponse.ERROR,"error de usuario").toJson();
    }

    response.cookie("username",usuario.get().getUsername());


    if(usuario.get() instanceof Admin) {
      //TODO hacer que vaya a la vista de Admin
      //response.body("Pantalla Admin");
      //response.status(200);
      return new StandardResponse(StatusResponse.SUCCESS,"Pantalla Admin").toJson();
    }

    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
    RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();


    Optional<Organizacion> organizacion = Optional.ofNullable(repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario.get()));
    Optional<Persona> persona = Optional.ofNullable(repositorioPersonasDB.buscarPersonaPorUsuario(usuario.get()));


    if(organizacion.isPresent()){
      //TODO mandar a la vista de organizacion
      response.cookie("organizacion",organizacion.get().getRazonSocial());
      //.body("pantalla organizacion");
      //response.status(200);
      return new StandardResponse(StatusResponse.SUCCESS,"pantalla organizacion").toJson();
    }
    if(persona.isPresent()){
      //TODO hacer que vaya a la vista de Persona
      response.cookie("persona",persona.get().getNroDocumento());
      //response.body("pantalla persona");
      //response.status(200);
      return new StandardResponse(StatusResponse.SUCCESS,"pantalla persona").toJson();
    }
    //response.body("pantalla persona");
    //response.status(200);
    return new StandardResponse(StatusResponse.SUCCESS,"pantalla persona").toJson();
  }



  //GET
  public ModelAndView loguearHtml (Request request, Response response) {
    Map<String, Object> parametros = new HashMap<>();
    return new ModelAndView(parametros,"index.hbs");
  }


  public ModelAndView menu_login (Request request, Response response) {
    Map<String, Object> parametros = new HashMap<>();
    return new ModelAndView(parametros,"index.hbs");
  }
}
