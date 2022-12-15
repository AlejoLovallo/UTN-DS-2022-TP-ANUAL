package Domain.JSON;

import Domain.Organizacion.AgenteSectorial;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Usuarios.Admin;
import Domain.Usuarios.Contacto;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

import java.util.List;

public class ParserJSONOrganizacion {

    //TODO: Actividad to JSON metodo

   public static JSONObject organizacionToJSON(Organizacion organizacion){
      if(organizacion == null) return null;

      JSONObject organizacionJSON = new JSONObject();

      organizacionJSON.put("razonSocial",organizacion.getRazonSocial());
      organizacionJSON.put("tipo",organizacion.getTipo());
      organizacionJSON.put("clasificacion",organizacion.getClasificacion());
     AgenteSectorial agente = organizacion.getAgenteSectorial();

     Contacto contacto = organizacion.getContacto();
     JSONObject contactoJSON = new JSONObject();
     contactoJSON.put("contacto_nombre",contacto.getNombre());
     contactoJSON.put("contacto_apellido",contacto.getApellido());
     contactoJSON.put("contacto_email",contacto.getEmail());
     contactoJSON.put("contacto_telefono",contacto.getTelefono());

     organizacionJSON.put("contacto",contactoJSON);

     organizacionJSON.put("actividades",organizacion.getActividades());

      return organizacionJSON;
    }

    public static JsonElement organizacionesJsonElement(List<Organizacion> organizaciones){

     JsonArray organizacionesJson = new JsonArray();

     JsonObject organizacionJson = new JsonObject();

     JsonArray sectoresJson = new JsonArray();

     JsonObject sectorJson = new JsonObject();

     for(Organizacion organizacion : organizaciones){
       organizacionJson.addProperty("nombre",organizacion.getRazonSocial());
       organizacionJson.addProperty("id",organizacion.getId());

     }



     return organizacionesJson;

    }

}
