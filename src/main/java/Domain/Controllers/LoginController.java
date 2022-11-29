package Domain.Controllers;

import Domain.JSON.ParserJSONUsuario;
import Domain.Miembro.Persona;
import Domain.Organizacion.Organizacion;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Admin;
import Domain.Usuarios.Usuario;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Optional;

public class LoginController {

  //POST
  public String loguear(Request request, Response response) throws ParseException {
    JSONParser parser = new JSONParser();
    JSONObject pedido = (JSONObject) parser.parse(request.body());

    Optional<Usuario> usuario = Optional.ofNullable(ParserJSONUsuario.jsonToUsuarioPlano(pedido));

    if(!usuario.isPresent()){
      //TODO marcar error
      response.redirect("/menu_login");
      return "error de JSON";
    }

    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
    usuario = Optional.ofNullable(repositorioUsuariosDB.validarLogueoUsuario(usuario.get().getUsername(),usuario.get().getContraSinHash()));


    //Lo vuelvo a ver porque lo lleno con el validador de usuario
    if(!usuario.isPresent()){
      //TODO marcar error
      response.redirect("/menu_login");
      return "error de usuario";
    }

    response.cookie("username",usuario.get().getUsername());


    if(usuario.get() instanceof Admin) {
      //TODO hacer que vaya a la vista de Admin
      //response.redirect("");
      return "Pantalla Admin";
    }

    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
    RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();


    Optional<Organizacion> organizacion = Optional.ofNullable(repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario.get()));
    Optional<Persona> persona = Optional.ofNullable(repositorioPersonasDB.buscarPersonaPorUsuario(usuario.get()));


    if(organizacion.isPresent()){
      //TODO mandar a la vista de organizacion
      response.cookie("organizacion",organizacion.get().getRazonSocial());
      //response.redirect("");
      return "pantalla organizacion";
    }
    if(persona.isPresent()){
      //TODO hacer que vaya a la vista de Persona
      response.cookie("persona",persona.get().getNroDocumento());
      //response.redirect("");
      return "pantalla persona";
    }
    return "usuario default";
  }

  //GET
  public void menu_login(){

  }
}
