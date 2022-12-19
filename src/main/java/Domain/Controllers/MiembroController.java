package Domain.Controllers;

import Domain.JSON.ParserJSONMiembro;
import Domain.Miembro.Excepciones.PersonaException;
import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;
import Domain.Miembro.TipoDocumento;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Organizacion.TipoOrganizacion;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import Domain.Usuarios.Usuario;
import com.google.gson.Gson;
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


    public Object crearPersona(Request req, Response res) throws ParseException{
        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();

            String personaString = req.body();
            JSONParser jsonParser = new JSONParser();
            JSONObject persona = (JSONObject) jsonParser.parse(personaString);

            Usuario usuario = new Usuario(
                    persona.get("username").toString(),
                    persona.get("email").toString(),
                    persona.get("password").toString(),
                    true
            );

            Persona persona1 = repositorioPersonasDB.crearPersona(
                    persona.get("name").toString(),
                    persona.get("surname").toString(),
                    TipoDocumento.values()[(Integer.parseInt(persona.get("tipoDocumento").toString())) -1],
                    persona.get("documento").toString(),
                    usuario
            );
//            res.cookie("username", usuario.getUsername());
//            res.cookie("organizacion",  persona.get("name").toString());
        SesionManager sesionManager = SesionManager.get();
        String idSesion = sesionManager.crearSesion("username", usuario.getUsername());
        sesionManager.agregarAtributo(idSesion, "rol","persona");
        sesionManager.agregarAtributo(idSesion,"documento",persona1.getNroDocumento());
        res.cookie("idSesion",idSesion);

            return new Gson()
                    .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla miembro"));

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

        response.body(htmlToString("Generar_Reporte.html"));
        return null;
    }

    public Object agregarTrayecto(Request request, Response response) throws ParseException, IOException {

        System.out.println(1);

        String id = request.cookie("idSesion");
        String username = (String) SesionManager.get().obtenerAtributos(id).get("username");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);

        JSONParser parser = new JSONParser();
        JSONObject pedido = (JSONObject) parser.parse(request.body());

        String organizacion = (String) pedido.get("organizacion");

        System.out.println(2);
        for (Miembro miembro : persona.getMiembros()) {
            if (miembro.getSector().getOrganizacion().getRazonSocial().equals(organizacion)) {
                System.out.println(3);
                Trayecto trayecto = new Trayecto();

                trayecto.setMiembro(miembro);
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                trayecto.setFrecuenciaSemanal(
                        Integer.parseInt(pedido.get("frecuenciaSemanal").toString()));
                trayecto.setFechaInicio(LocalDate.parse((CharSequence) pedido.get("fechaInicio"), formato));
                trayecto.setFechaFin(LocalDate.parse((CharSequence) pedido.get("fechaFin"), formato));

                List<Tramo> tramos = new ArrayList<>();
                ParserJSONMiembro parserJSONMiembro = new ParserJSONMiembro();
                JSONParser jsonParser = new JSONParser();
                for (Object tramo : (JSONArray) pedido.get("tramos")) {
                    Tramo tramoObj = parserJSONMiembro.JSONATramo((JSONObject) jsonParser.parse(tramo.toString()));
                    tramoObj.setTrayecto(trayecto);
                    tramos.add(tramoObj);
                }
                trayecto.setTramos(tramos);

                miembro.agregarTrayecto(trayecto);

                miembro.recalcularHC(
                        trayecto.getFechaInicio().getMonthValue(),
                        trayecto.getFechaInicio().getYear(),
                        trayecto.getFechaFin().getMonthValue(),
                        trayecto.getFechaFin().getYear()
                );

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
        String id = request.cookie("idSesion");
        String username = (String) SesionManager.get().obtenerAtributos(id).get("username");

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

    public Object respuestaCalcularHC(Request request, Response response) throws IOException, ParseException {
        String id = request.cookie("idSesion");
        String username = (String) SesionManager.get().obtenerAtributos(id).get("username");

        JSONParser jsonParser = new JSONParser();
        JSONObject pedido = (JSONObject) jsonParser.parse(request.body());
        String mesDesde = pedido.get("mesDesde").toString();
        String anioDesde = pedido.get("añoDesde").toString();
        String mesHasta = pedido.get("mesHasta").toString();
        String anioHasta = pedido.get("añoHasta").toString();

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);

        Double resultado = 0.0;
        for (Miembro miembro : persona.getMiembros())
            resultado += miembro.calcularHCPeriodo(
                    Integer.parseInt(mesDesde),
                    Integer.parseInt(anioDesde),
                    Integer.parseInt(mesHasta),
                    Integer.parseInt(anioHasta)
            );

        response.type("application/json");
        JSONObject cantidadHC = new JSONObject();
        cantidadHC.put("resultado", resultado);

        response.status(200);
        return cantidadHC;
    }

    public Object menuEnviarSolicitud(Request request, Response response) throws ParseException {
        //String username = request.cookie("username");

        JSONParser jsonParser = new JSONParser();
        JSONObject pedido = (JSONObject) jsonParser.parse(request.body());
        String nombreOrganizacion = (String) pedido.get("organizacion");
        String nombreSector = (String) pedido.get("sector");
        String idSesion = (String) pedido.get("idSesion");

        String username = SesionManager.get().obtenerAtributos(idSesion).get("username").toString();

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);

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
        if (!chequearCookie(request,response)){
            return new ModelAndView(parametros,"index.html");
        }
        //response.cookie("username", request.cookie("username"));
        //response.cookie("persona", request.cookie("persona"));
        return new ModelAndView(parametros,"Menu_Miembro.html");
    }

    public ModelAndView calcularHTMLmiembro(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        if (!chequearCookie(request,response)){
            return new ModelAndView(parametros,"index.html");
        }
        //response.cookie("username", request.cookie("username"));
        //response.cookie("persona", request.cookie("persona"));
        return new ModelAndView(parametros,"Calcular_HC.html");
    }

    public ModelAndView registrarTrayectoHTML(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        if (!chequearCookie(request,response)){
            return new ModelAndView(parametros,"index.html");
        }
        //response.cookie("username", request.cookie("username"));
        //response.cookie("persona", request.cookie("persona"));
        return new ModelAndView(parametros,"Registrar_Trayecto.html");
    }

    public ModelAndView solicitarVinculacionHTML(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        if (!chequearCookie(request,response)){
            return new ModelAndView(parametros,"index.html");
        }
        //response.cookie("username", request.cookie("username"));
        //response.cookie("persona", request.cookie("persona"));
        return new ModelAndView(parametros,"Solicitar_Vinculacion.html");
    }



    private boolean chequearCookie(Request request,Response response){
        String idSesion = request.cookie("idSesion");
        SesionManager sesionManager = SesionManager.get();

        Map<String,Object> atributos = sesionManager.obtenerAtributos(idSesion);

        if (atributos == null){
            response.redirect("/");
            return false;
        }
        return true;
    }

}
