package Controllers;

import spark.Request;
import spark.Response;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MiembroController {

    public String htmlToString(String nombreArchivo)
    {
        //TODO: revisar si funciona
        File archivoHTML = new File("src/main/resources/templates/"+nombreArchivo);
        return archivoHTML.toString();
    }

    //GET
    public Object registradoDeTrayectos(Request request, Response response)
    {
        response.type("text/html");

        response.body(htmlToString("Registrar_Trayectos.html"));
        return null;
    }

    //GET
    public Object solicitarVinculacion(Request request, Response response)
    {
        response.type("text/html");

        response.body(htmlToString("Solicitar_Vinculacion.html"));
        return null;
    }

    //GET
    public Object visualizadoReportes(Request request, Response response)
    {
        response.type("text/html");

        response.body(htmlToString("Visualizar_Reporte.html"));
        return null;
    }

    public Object agregarTrayecto(Request request, Response response)  {

        return null;
    }

    public Object menuRegistrarTrayectos(Request request, Response response)
    {

        return null;
    }

    public Object respuestaCalcularHC(Request request, Response response)  {

        return null;
    }

    public Object menuEnviarSolicitud(Request request, Response response) {

        return null;
    }

/*
    public Object menuEnviarSolicitud(Request request, Response response)
    {
        String username = request.cookie("username");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);

        String nombreOrganizacion = request.queryParams("organizacion");
        String nombreSector = request.queryParams("sector");

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacion(nombreOrganizacion);

        Sector miembroSector = null;
        for(Sector sector : organizacion.getSectores())
        {
            if(sector.getNombre().equals(nombreSector))
            {
                miembroSector = sector;
                break;
            }
        }

        if(miembroSector == null)
        {
            //TODO: enviar mensaje que no existe
            return null;
        }
        else{
            RepositorioSolicitudesDB repositorioSolicitudesDB = new RepositorioSolicitudesDB();

            SolicitudPendiente solicitudPendiente = new SolicitudPendiente();
            solicitudPendiente.setPersona(persona);
            solicitudPendiente.setSector(miembroSector);
            repositorioSolicitudesDB.agregar(solicitudPendiente);
            return null;
        }
    }
*/
public Object getMiembro(Request req, Response res)  {

    return null;
}



}
