package Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class OrganizacionController {

    public String htmlToString(String nombreArchivo)
    {
        //TODO: revisar si funciona
        File archivoHTML = new File("src/main/resources/templates/Registrar_Mediciones.hbs");
        return archivoHTML.toString();
    }


    public ModelAndView getOrganizacion (Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"index.hbs");
    }

}
