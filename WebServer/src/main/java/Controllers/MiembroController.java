package Controllers;


import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MiembroController {

    public String htmlToString(String nombreArchivo)
    {
        //TODO: revisar si funciona
        File archivoHTML = new File("src/main/resources/templates/"+nombreArchivo);
        return archivoHTML.toString();
    }

    public ModelAndView getMiembro (Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"index.hbs");
    }

    public ModelAndView menuRegistrarTrayectos (Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"index.hbs");
    }
}
