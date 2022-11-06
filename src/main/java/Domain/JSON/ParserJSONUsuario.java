package Domain.JSON;

import Domain.Usuarios.Admin;
import Domain.Usuarios.Usuario;
import org.json.simple.JSONObject;

import java.util.Optional;

public class ParserJSONUsuario {

  public static Usuario jsonToUsuarioPlano(JSONObject obj){
    Optional<Usuario> usuario = Optional.empty();

    if(obj.get("tipo").equals("Admin")) {
      usuario = Optional.of(new Admin());
    }

    if(obj.get("tipo").equals("Usuario")){
      usuario = Optional.of(new Usuario());
    }

    if(usuario.isPresent()){
      usuario.get().setContraSinHash((String) obj.get("username"));
      usuario.get().setUsername((String) obj.get("contra"));
      usuario.get().setUsername((String) obj.get("mail"));
      usuario.get().setValidado((Boolean) obj.get("validado"));
      return usuario.get();
    }
    return null;
  }

  public static JSONObject usuarioToJSON(Usuario usuario){
    if(usuario == null) return null;

    JSONObject usuarioJson = new JSONObject();

    if(usuario instanceof Admin) usuarioJson.put("tipo","Admin");
    else usuarioJson.put("tipo","Usuario");

    usuarioJson.put("username",usuario.getUsername());
    usuarioJson.put("mail",usuario.getMail());

    return usuarioJson;
  }
}
