package Domain.JSON;

import Domain.Usuarios.Admin;
import Domain.Usuarios.Usuario;
import org.json.simple.JSONObject;

import java.util.Optional;

public class ParserJSONUsuario {

  public static Usuario jsonToUsuarioPlano(JSONObject obj){

    String username = (String) obj.get("username");
    String contra = (String) obj.get("contra");

    if (username == null) return  null;
    if (contra == null) return  null;

    Usuario usuario = new Usuario();
    usuario.setUsername(username);
    usuario.setContraSinHash(contra);

    return usuario;
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
