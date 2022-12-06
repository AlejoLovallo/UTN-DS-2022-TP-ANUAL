package Domain.JSON;

import Domain.Organizacion.AgenteSectorial;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Usuarios.Admin;
import Domain.Usuarios.Contacto;
import org.json.simple.JSONObject;

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

}
