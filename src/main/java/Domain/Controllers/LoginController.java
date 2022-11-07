package Domain.Controllers;

import Domain.JSON.ParserJSONUsuario;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Usuario;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.Request;
import spark.Response;

public class LoginController {

  public void menuLogin(Request request, Response response) throws ParseException {
    JSONParser parser = new JSONParser();
    JSONObject pedido = (JSONObject) parser.parse(request.body());

    Usuario usuario = ParserJSONUsuario.jsonToUsuarioPlano(pedido);

    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
    assert usuario != null;
    usuario = repositorioUsuariosDB.validarLogueoUsuario(usuario.getUsername(),usuario.getContraSinHash());

    response.cookie("username",usuario.getUsername());

  }
}
