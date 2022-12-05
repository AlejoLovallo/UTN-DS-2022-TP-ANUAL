package Domain.Controllers;

import Domain.CalculadorHC.CalculadorHC;
import Domain.JSON.ParserJSONMiembro;
import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Organizacion.SolicitudPendiente;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Repositorios.RepositorioSolicitudesDB;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.Request;
import spark.Response;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MiembroController {

    public String htmlToString(String nombreArchivo)
    {
        //TODO: revisar si funciona
        File archivoHTML = new File("src/main/resources/templates/Registrar_Mediciones.html");
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

    public Object agregarTrayecto(Request request, Response response) throws ParseException {

        String username = request.cookie("username");
        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);

        JSONParser parser = new JSONParser();
        JSONObject pedido = (JSONObject) parser.parse(request.body());

        String organizacion = request.queryParams("organizacion");
        Miembro aux = null;
        for(Miembro miembro : persona.getMiembros())
        {
            if(miembro.getSector().getOrganizacion().getRazonSocial().equals(organizacion))
            {
                aux = miembro;
                break;
            }
        }

        Trayecto trayecto = new Trayecto();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        trayecto.setFrecuenciaSemanal((Integer)pedido.get("frecuenciaSemanal"));
        trayecto.setFechaInicio(LocalDate.parse((String)pedido.get("fechaInicio"), formato));
        trayecto.setFechaFin(LocalDate.parse((String)pedido.get("fechaFin"), formato));

        List<Tramo> tramos = new ArrayList<>();
        ParserJSONMiembro parserJSONMiembro = new ParserJSONMiembro();
        for(Object tramo : (JSONArray)pedido.get("tramos"))
        {
            tramos.add(parserJSONMiembro.JSONATramo((JSONObject)tramo));
        }

        aux.getTrayectos().add(trayecto);
        return null;
    }

    public Object menuRegistrarTrayectos(Request request, Response response)
    {
        String username = request.cookie("username");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);

        ParserJSONMiembro parserJSONMiembro = new ParserJSONMiembro();
        JSONArray listaMiembrosJSON = new JSONArray();
        for(Miembro miembro : persona.getMiembros())
        {
            List<Trayecto> trayectos = miembro.getTrayectos();

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            JSONArray trayectosJSON = new JSONArray();
            for(Trayecto trayecto : trayectos)
            {
                JSONObject trayectoObj = new JSONObject();
                trayectoObj.put("frecuenciaSemanal", trayecto.getFrecuenciaSemanal());
                trayectoObj.put("fechaInicio", trayecto.getFechaInicio().format(formato));
                trayectoObj.put("fechaFin", trayecto.getFechaFin().format(formato));

                JSONArray tramos = new JSONArray();
                for(Tramo tramo : trayecto.getTramos())
                    tramos.add(parserJSONMiembro.tramoAJSONObject(tramo));

                trayectoObj.put("tramos", tramos);

                trayectosJSON.add(trayectoObj);
            }

            JSONObject miembroJSON = new JSONObject();
            miembroJSON.put("organizacion",miembro.getSector().getOrganizacion().getRazonSocial());
            miembroJSON.put("trayectos", trayectosJSON);

            listaMiembrosJSON.add(miembroJSON);
        }

        response.type("application/json");
        response.body(listaMiembrosJSON.toJSONString());
        return null;
    }

    public Object respuestaCalcularHC(Request request, Response response) throws IOException {
        String username = request.cookie("username");
        String mes = request.queryParams("Mes");
        String anio = request.queryParams("Anio");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);

        Double resultado = 0.0;
        for(Miembro miembro : persona.getMiembros())
            resultado += miembro.calcularHC(Integer.parseInt(mes), Integer.parseInt(anio));

        response.type("application/json");
        response.body("{ \"resultado\":" + resultado.toString() + "}");
        return null;
    }

    public Object menuEnviarSolicitud(Request request, Response response)
    {
        String username = request.cookie("username");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);

        String nombreOrganizacion = request.queryParams("organizacion");
        String nombreSector = request.queryParams("sector");

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorNombre(nombreOrganizacion);

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
            Miembro miembro = new Miembro(persona.getNombre(), miembroSector);
            miembro.setActivo(false);
            persona.getMiembros().add(miembro);
            miembroSector.getMiembros().add(miembro);

            repositorioOrganizacionesDB.modificar(organizacion);
            repositorioPersonasDB.modificar(persona);

            return null;
        }
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
public Object getMiembro(Request req, Response res) throws ParseException {
    String nombreUsuario = req.params(":username");
    String organizacion = req.params(":organizacion");

    RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
    Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(nombreUsuario);

    RepositorioOrganizacionesDB repoOrganizacionesDB = new RepositorioOrganizacionesDB();
    Organizacion org = repoOrganizacionesDB.buscarOrganizacionPorNombre(organizacion);


    for(Miembro miembro : persona.getMiembros())
    {
        if(miembro.getSector().getOrganizacion().equals(org))
        {
            res.type("application/json");
            JSONObject resOrganizacion = ParserJSONMiembro.miembroToJSON(miembro);
            return resOrganizacion;
        }
    }
    return null;
}



}
