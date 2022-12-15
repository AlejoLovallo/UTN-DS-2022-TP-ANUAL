package Domain.JSON;

import Domain.Organizacion.AgenteSectorial;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
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

     if (organizaciones.isEmpty()) return null;

     JsonArray organizacionesJson = new JsonArray();

     JsonObject organizacionJson ;

     JsonArray sectoresJson ;

     JsonObject sectorJson;

     for(Organizacion organizacion : organizaciones){
       organizacionJson = new JsonObject();
       sectoresJson = new JsonArray();

       organizacionJson.addProperty("nombre",organizacion.getRazonSocial());
       organizacionJson.addProperty("id",organizacion.getId());


       if(!organizacion.getSectores().isEmpty()){
         for(Sector sector: organizacion.getSectores()){
           sectorJson = new JsonObject();
           sectorJson.addProperty("nombre",sector.getNombre());
           sectorJson.addProperty("idSector",sector.getId_sector());
           sectoresJson.add(sectorJson);
         }
       }
       else
         sectoresJson = null;


       organizacionJson.add("sectores",sectoresJson);
       organizacionesJson.add(organizacionJson);
     }



     return organizacionesJson;

    }

}
