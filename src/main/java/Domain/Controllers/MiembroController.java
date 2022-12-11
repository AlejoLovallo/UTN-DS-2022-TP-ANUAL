package Domain.Controllers;

import Domain.JSON.ParserJSONMiembro;
import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import Domain.Usuarios.Usuario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiembroController {

    public String htmlToString(String nombreArchivo) {
        // TODO: revisar si funciona
        File archivoHTML = new File("src/main/resources/templates/Registrar_Mediciones.html");
        return archivoHTML.toString();
    }

    // GET
    public Object registradoDeTrayectos(Request request, Response response) {
        response.type("text/html");

        response.body(htmlToString("Registrar_Trayectos.html"));
        return null;
    }

    // GET
    public Object solicitarVinculacion(Request request, Response response) {
        response.type("text/html");

        response.body(htmlToString("Solicitar_Vinculacion.html"));
        return null;
    }

    // GET
    public Object visualizadoReportes(Request request, Response response) {
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

        String organizacion = (String) pedido.get("organizacion");
        for (Miembro miembro : persona.getMiembros()) {
            if (miembro.getSector().getOrganizacion().getRazonSocial().equals(organizacion)) {

                Trayecto trayecto = new Trayecto();

                trayecto.setMiembro(miembro);
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd MM yyyy");
                trayecto.setFrecuenciaSemanal(
                        ((Long) pedido.get("frecuenciaSemanal")).intValue());
                trayecto.setFechaInicio(LocalDate.parse((CharSequence) pedido.get("fechaInicio"), formato));
                trayecto.setFechaFin(LocalDate.parse((CharSequence) pedido.get("fechaFin"), formato));

                List<Tramo> tramos = new ArrayList<>();
                ParserJSONMiembro parserJSONMiembro = new ParserJSONMiembro();
                for (Object tramo : (JSONArray) pedido.get("tramos")) {
                    Tramo tramoObj = parserJSONMiembro.JSONATramo((JSONObject) tramo);
                    tramoObj.setTrayecto(trayecto);
                    tramos.add(tramoObj);
                }
                trayecto.setTramos(tramos);

                miembro.agregarTrayecto(trayecto);

                response.type("text/javascript");
                response.status(200);
                return "window.alert(\"Trayecto registrado\")";
            }
        }
        response.type("text/javascript");
        response.status(404);
        return "window.alert(\"Miembro no encontrado\")";
    }

    public Object visualizarTrayectos(Request request, Response response) {
        String username = request.cookie("username");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);

        ParserJSONMiembro parserJSONMiembro = new ParserJSONMiembro();
        JSONArray listaMiembrosJSON = new JSONArray();
        for (Miembro miembro : persona.getMiembros()) {
            List<Trayecto> trayectos = miembro.getTrayectos();

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            JSONArray trayectosJSON = new JSONArray();
            for (Trayecto trayecto : trayectos) {
                JSONObject trayectoObj = new JSONObject();
                trayectoObj.put("frecuenciaSemanal", trayecto.getFrecuenciaSemanal());
                trayectoObj.put("fechaInicio", trayecto.getFechaInicio().format(formato));
                trayectoObj.put("fechaFin", trayecto.getFechaFin().format(formato));

                JSONArray tramos = new JSONArray();
                for (Tramo tramo : trayecto.getTramos())
                    tramos.add(parserJSONMiembro.tramoAJSONObject(tramo));

                trayectoObj.put("tramos", tramos);

                trayectosJSON.add(trayectoObj);
            }

            JSONObject miembroJSON = new JSONObject();
            miembroJSON.put("organizacion", miembro.getSector().getOrganizacion().getRazonSocial());
            miembroJSON.put("trayectos", trayectosJSON);
            miembroJSON.put("persona", ParserJSONMiembro.personaAJSON(miembro.getPersona()));

            listaMiembrosJSON.add(miembroJSON);
        }

        response.type("application/json");
        response.status(200);
        return listaMiembrosJSON;
    }

    public Object respuestaCalcularHC(Request request, Response response) throws IOException {
        String username = request.cookie("username");
        String mes = request.queryParams("Mes");
        String anio = request.queryParams("Anio");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);

        Double resultado = 0.0;
        for (Miembro miembro : persona.getMiembros())
            resultado += miembro.calcularHC(Integer.parseInt(mes), Integer.parseInt(anio));

        response.type("application/json");
        JSONObject cantidadHC = new JSONObject();
        cantidadHC.put("resultado", resultado);

        response.status(200);
        return cantidadHC;
    }

    public Object menuEnviarSolicitud(Request request, Response response) {
        String username = request.cookie("username");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);

        String nombreOrganizacion = request.queryParams("organizacion");
        String nombreSector = request.queryParams("sector");

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorNombre(nombreOrganizacion);

        Sector miembroSector = null;
        for (Sector sector : organizacion.getSectores()) {
            if (sector.getNombre().equals(nombreSector)) {
                miembroSector = sector;
                break;
            }
        }

        if (miembroSector == null) {
            response.type("text/javascript");
            response.status(200);
            return "window.alert(\"Error, no existe el sector\")";
        } else {
            Miembro miembro = new Miembro(persona.getNombre(), miembroSector);
            miembro.setActivo(false);
            miembroSector.getMiembros().add(miembro);
            persona.agregarMiembro(miembro);

            response.type("text/javascript");
            response.status(200);
            return "window.alert(\"Operacion realizada exitosamente\")";
        }
    }

    /*
     * public Object menuEnviarSolicitud(Request request, Response response)
     * {
     * String username = request.cookie("username");
     * 
     * RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
     * Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);
     * 
     * String nombreOrganizacion = request.queryParams("organizacion");
     * String nombreSector = request.queryParams("sector");
     * 
     * RepositorioOrganizacionesDB repositorioOrganizacionesDB = new
     * RepositorioOrganizacionesDB();
     * Organizacion organizacion =
     * repositorioOrganizacionesDB.buscarOrganizacion(nombreOrganizacion);
     * 
     * Sector miembroSector = null;
     * for(Sector sector : organizacion.getSectores())
     * {
     * if(sector.getNombre().equals(nombreSector))
     * {
     * miembroSector = sector;
     * break;
     * }
     * }
     * 
     * if(miembroSector == null)
     * {
     * //TODO: enviar mensaje que no existe
     * return null;
     * }
     * else{
     * RepositorioSolicitudesDB repositorioSolicitudesDB = new
     * RepositorioSolicitudesDB();
     * 
     * SolicitudPendiente solicitudPendiente = new SolicitudPendiente();
     * solicitudPendiente.setPersona(persona);
     * solicitudPendiente.setSector(miembroSector);
     * repositorioSolicitudesDB.agregar(solicitudPendiente);
     * return null;
     * }
     * }
     */
    public Object getMiembro(Request req, Response res) throws ParseException {
        String nombreUsuario = req.params(":username");
        String organizacion = req.params(":organizacion");

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(nombreUsuario);

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(nombreUsuario);

        RepositorioOrganizacionesDB repoOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion org = repoOrganizacionesDB.buscarOrganizacionPorNombre(organizacion);

        for (Miembro miembro : persona.getMiembros()) {
            if (miembro.getSector().getOrganizacion().equals(org)) {
                res.type("application/json");
                res.status(200);
                JSONObject resOrganizacion = ParserJSONMiembro.miembroToJSON(miembro);
                return resOrganizacion;
            }
        }
        res.type("text/javascript");
        res.status(404);
        return "window.alert(\"Miembro no encontrado\")";
    }

    public ModelAndView menu_miembro(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"Menu_Miembro.html");
    }

}
