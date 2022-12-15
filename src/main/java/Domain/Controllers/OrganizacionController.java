package Domain.Controllers;

import Domain.CalculadorHC.CalculadorHC;
import Domain.JSON.ParserJSONMiembro;
import Domain.JSON.ParserJSONOrganizacion;
import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;
import Domain.Organizacion.*;
import Domain.Organizacion.Excepciones.OrganizacionException;
import Domain.Reportes.*;
import Domain.Repositorios.*;
import Domain.Usuarios.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class OrganizacionController {

    private CalculadorHC calculadorHC = CalculadorHC.getInstance();

    public String htmlToString(String nombreArchivo)
    {
        //TODO: revisar si funciona
        File archivoHTML = new File("src/main/resources/templates/Registrar_Mediciones.html");
        return archivoHTML.toString();
    }

    public Object pedidoMenuCalcularHC(Request request, Response response){

        JSONObject tipo = new JSONObject();

        String idSesion = request.cookie("idSesion");

        if(SesionManager.get().obtenerAtributos(idSesion).get("rol").equals("persona")){
            tipo.put("tipoUsuario", "persona");
        }
        else if(SesionManager.get().obtenerAtributos(idSesion).get("rol").equals("organizacion")){
            tipo.put("tipoUsuario", "organizacion");
        }

        response.type("application/json");
        response.body(tipo.toString());


        return tipo;
        //return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,"identificado", tipo));
    }

    //GET
    public Object cargardoMediciones(Request request, Response response)
    {
        response.type("text/html");

        response.body(htmlToString("Registro_Mediciones.html"));
        return null;
    }

    //GET
    public Object aceptadoMiembros(Request request, Response response)
    {
        response.type("text/html");

        response.body(htmlToString("Aceptar_Miembros.html"));
        return null;
    }

    //GET
    public Object visualizadoReportes(Request request, Response response)
    {
        response.type("text/html");

        response.body(htmlToString("Generar_Reporte.html"));
        return null;
    }

    public Object calculadorDeHC(Request request, Response response)
    {
        response.type("text/html");

        response.body(htmlToString("Calcular_HC.html"));
        return null;
    }

  public Object getOrganizacion(Request req, Response res) throws ParseException {
      String nombreOg = req.params(":nombre");

      RepositorioOrganizacionesDB repoOrganizacionesDB = new RepositorioOrganizacionesDB();
      Organizacion org = repoOrganizacionesDB.buscarOrganizacionPorNombre(nombreOg);

      JSONObject resOrganizacion = ParserJSONOrganizacion.organizacionToJSON(org);

      res.type("application/json");

      res.status(200);
      return resOrganizacion;
  }

 public Object crearOrganizacion(Request req, Response res) throws ParseException{

        try{
            RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();

            String organizacionString = req.body();
            JSONParser jsonParser = new JSONParser();
            JSONObject org = (JSONObject) jsonParser.parse(organizacionString);
            JSONObject user = (JSONObject) jsonParser.parse(org.get("user").toString());

            Usuario usuario = new Usuario(
                    user.get("name").toString(),
                    user.get("mail").toString(),
                    user.get("password").toString(),
                    true
            );

            Organizacion organizacion = repositorioOrganizacionesDB.crearOrganizacion(
                    org.get("socialReason").toString(),
                    ClasificacionOrganizacion.values()[Integer.parseInt(org.get("clasification").toString()) -1],
                    TipoOrganizacion.values()[(Integer.parseInt(org.get("type").toString())) -1],
                    Integer.parseInt(org.get("diasSemana").toString()),
                    null,
                    usuario
            );

            res.status(200);

            res.body(new Gson()
                    .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla organizacion").toString()));

//            res.cookie("username", usuario.getUsername());
//            res.cookie("organizacion", organizacion.getRazonSocial());

            return new Gson()
                    .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla organizacion"));
        }
        catch(OrganizacionException e){
            return null;
        }
 }

  public Object modificarOrganizacion(Request req, Response res) throws ParseException{
    return null;
  }

  public Object respuestaListaMiembros(Request request, Response response)
  {
      String idSesion = request.cookie("idSesion");

      String username = SesionManager.get().obtenerAtributos(idSesion).get("username").toString();

      RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
      Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

      RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
      Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

      response.type("application/json");

      JSONArray listaMiembros = new JSONArray();
      for(Sector sector : organizacion.getSectores())
      {
          for(Miembro miembro : sector.getMiembros())
          {
            if(!miembro.getActivo())
              listaMiembros.add(ParserJSONMiembro.miembroSolicitudToJSON(miembro));
          }
      }
      response.status(200);
      return listaMiembros;
  }

    public Object respuestaAceptarMiembro(Request request, Response response) throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(request.body());
        String dniPersona =  jsonObject.get("dni").toString();
        String id_sector =  jsonObject.get("id_sector").toString();
        String idSesion = request.cookie("idSesion");

        String username = SesionManager.get().obtenerAtributos(idSesion).get("username").toString();

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorNroDocumento(dniPersona);

        Optional<Sector> sector = organizacion.getSectores().stream().filter(
                s -> s.getId_sector() == Integer.parseInt(id_sector)
        ).findAny();

        Optional<Miembro> miembro = sector.get().getMiembros().stream().filter(
                m -> m.getPersona().equals(persona)
        ).findAny();

        miembro.get().setActivo(true);
        repositorioPersonasDB.modificar(persona);

        response.type("text/javascript");
        response.status(200);

        return "window.alert(\"Operacion ejecutada exitosamente\")";
    }

    public Object respuestaRechazarMiembro(Request request, Response response) throws ParseException {
        //String username = request.cookie("username");

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(request.body());
        String idPersona = jsonObject.get("dni").toString();
        String idSector = jsonObject.get("id_sector").toString();
        String idSesion = request.cookie("idSesion");

        String username = SesionManager.get().obtenerAtributos(idSesion).get("username").toString();

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorNroDocumento(idPersona);

        Optional<Sector> sector = organizacion.getSectores().stream().filter(
                s -> s.getId_sector() == Integer.parseInt(idSector)
        ).findAny();

        Optional<Miembro> miembro = sector.get().getMiembros().stream().filter(
                m -> m.getPersona().equals(persona)
        ).findAny();

        //TODO: probar
        persona.getMiembros().remove(miembro.get());
        sector.get().getMiembros().remove(miembro.get());

        Miembro miembro1 = miembro.get();

        miembro1.setPersona(null);
        miembro1.setSector(null);
        miembro1 = null;

        repositorioOrganizacionesDB.modificar(organizacion);
        repositorioPersonasDB.modificar(persona);

        response.type("text/javascript");
        response.status(200);

        return "window.alert(\"Operacion ejecutada exitosamente\")";
    }

    public Object respuestaCalcularHC(Request request, Response response) throws IOException, ParseException {


        String idSesion = request.cookie("idSesion");

        String username = SesionManager.get().obtenerAtributos(idSesion).get("username").toString();
        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(request.body());
        String mesDesde = jsonObject.get("mesDesde").toString();
        String añoDesde = jsonObject.get("añoDesde").toString();
        String mesHasta = jsonObject.get("mesHasta").toString();
        String añoHasta = jsonObject.get("añoHasta").toString();

        Integer MesDesde = Integer.parseInt(mesDesde);
        Integer AñoDesde = Integer.parseInt(añoDesde);
        Integer MesHasta = Integer.parseInt(mesHasta);
        Integer AñoHasta = Integer.parseInt(añoHasta);

        LocalDate fechaDesde = LocalDate.of(AñoDesde, MesDesde, 01);
        LocalDate fechaHasta = LocalDate.of(AñoHasta, MesHasta, 01);

        Double resultado = calculadorHC.calcularHcPeriodo(organizacion, fechaDesde, fechaHasta);

        response.type("application/json");

        JSONObject calculo = new JSONObject();
        calculo.put("resultado", resultado.toString());

        response.status(200);
        response.body(calculo.toString());
        return calculo;
    }

    public Object respuestaGenerarReporte(Request request, Response response) throws IOException, ParseException {


        String idSesion = request.cookie("idSesion");

        String username = SesionManager.get().obtenerAtributos(idSesion).get("username").toString();

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(request.body());
        String mesDesde = jsonObject.get("mesDesde").toString();
        String añoDesde = jsonObject.get("añoDesde").toString();
        String mesHasta = jsonObject.get("mesHasta").toString();
        String añoHasta = jsonObject.get("añoHasta").toString();
        TipoDeReporte tipo = TipoDeReporte.valueOf(jsonObject.get("tipo").toString());

        Integer MesDesde = Integer.parseInt(mesDesde);
        Integer AñoDesde = Integer.parseInt(añoDesde);
        Integer MesHasta = Integer.parseInt(mesHasta);
        Integer AñoHasta = Integer.parseInt(añoHasta);

        LocalDate fechaDesde = LocalDate.of(AñoDesde, MesDesde, 01);
        LocalDate fechaHasta = LocalDate.of(AñoHasta, MesHasta, 01);

        Reporte reporte = null;
        if(tipo.equals(TipoDeReporte.COMPOSICION)){
            reporte = GeneradorReportes.getInstance().reporteCompHC_Org(organizacion, fechaDesde, fechaHasta);
        }
        else{
            reporte = GeneradorReportes.getInstance().reporteEvolucionHC_Org(organizacion, fechaDesde, fechaHasta);
        }

        response.status(200);

        return new Gson()
                .toJson(new StandardResponse(StatusResponse.SUCCESS,"generacion existosa"));
    }

    public Object cargarMediciones(Request request, Response response) throws ServletException, IOException {
        String idSesion = request.cookie("idSesion");

        String username = SesionManager.get().obtenerAtributos(idSesion).get("username").toString();

        Path pathArchivo = Paths.get("src/main/java/Domain/Utils/" + username + ".xls");
        if(Files.exists(pathArchivo))
            Files.delete(pathArchivo);

        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        InputStream is = request.raw().getPart("file").getInputStream();
        Files.copy(is, pathArchivo);

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        organizacion.cargarMedicionesInternas(username + ".xls");

        repositorioOrganizacionesDB.modificar(organizacion);

        response.type("text/javascript");
        response.status(200);
        response.body("window.alert(\"Operacion realizada exitosamente\")");
        return new Gson()
                .toJson(new StandardResponse(StatusResponse.SUCCESS,"carga existosa"));
    }


    public ModelAndView mostrarReportes(Request request, Response response)
    {
        HashMap<String, Object> params = new HashMap<>();
        if (!chequearCookie(request,response)){
            return new ModelAndView(params,"index.html");
        }

        JSONArray listaReportes = new JSONArray();

        String idSesion = request.cookie("idSesion");


        Optional<String> username = Optional.ofNullable(SesionManager.get().obtenerAtributos(idSesion).get("username").toString());

        //TODO para hacer pruebas
        //username = Optional.of("usuarioNormal");

        //TODO podemos mostrar otra parte sino en vez de Recomendaciones
        if ( !username.isPresent() || username.get().equals("") ) response.redirect("/recomendaciones");//return new ModelAndView(params, "Recomendaciones.hbs");


        //buscar si es agente
        //else

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username.get());

        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        //TODO borrar
        //RepositorioReportesDB repositorioReportesDB = new RepositorioReportesDB();
        //List<Reporte> reporte = repositorioReportesDB.buscarTodos();

        List<ReporteComposicion> listaComposicion = organizacion.getReportes().stream().filter(
                r -> r instanceof ReporteComposicion
        ).map(r -> (ReporteComposicion) r).collect(Collectors.toList());

        List<ReporteEvolucion> listaEvolucion = (List<ReporteEvolucion>) organizacion.getReportes().stream().filter(
                r -> r instanceof ReporteEvolucion
        ).map(r -> (ReporteEvolucion) r).collect(Collectors.toList());

        List<ReporteTotal> listaTotal = (List<ReporteTotal>) organizacion.getReportes().stream().filter(
                r -> r instanceof ReporteTotal
        ).map(r -> (ReporteTotal) r).collect(Collectors.toList());

        params.put("composicion", listaComposicion);
        params.put("evolucion", listaEvolucion);
        params.put("total", listaTotal);

        return new ModelAndView(params, "Reporte.hbs");
    }

    public ModelAndView listarRecomendaciones(Request request, Response response){



        HashMap<String, Object> params = new HashMap<>();

        if (!chequearCookie(request,response)){
            return new ModelAndView(params,"index.html");
        }

        String idSesion = request.cookie("idSesion");

        Optional<String> username = Optional.ofNullable(SesionManager.get().obtenerAtributos(idSesion).get("username").toString());

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username.get());

        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        ArrayList<String> listaRecomendaciones = new ArrayList<>();

        for(Recomendacion recomendacion : organizacion.getRecomendaciones()){
            listaRecomendaciones.add(recomendacion.getRecomendacion());
        }

        params.put("recomendaciones", listaRecomendaciones);

        return new ModelAndView(params, "Recomendaciones.hbs");
    }

/*
    public ModelAndView listarRecomendaciones(Request request, Response response){
        HashMap<String, Object> params = new HashMap<>();

        ArrayList<String> listaRecomendaciones = new ArrayList<>();

        listaRecomendaciones.add("recomendacion 1");
        listaRecomendaciones.add("recomendacion 2");
        listaRecomendaciones.add("recomendacion 3");

        params.put("recomendaciones", listaRecomendaciones);

        return new ModelAndView(params, "Recomendaciones.hbs");
    }

 */

    public ModelAndView menuOrganizacion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
//        response.cookie("idSesion",request.cookie("idSesion"));
//        response.cookie("username",request.cookie("username"));
//        response.cookie("organizacion",request.cookie("organizacion"));

        if (!chequearCookie(request,response)){
            return new ModelAndView(parametros,"index.html");
        }
        //System.out.println(atributos.toString());


        return new ModelAndView(parametros,"Menu_Organizacion.html");
    }

    public ModelAndView solicitudesHTML (Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        if (!chequearCookie(request,response)){
            return new ModelAndView(parametros,"index.html");
        }

//        response.cookie("username",request.cookie("username"));
//        response.cookie("organizacion",request.cookie("organizacion"));
        return new ModelAndView(parametros,"Aceptar_Miembros.html");
    }

    public ModelAndView calcularHCorg (Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (!chequearCookie(request,response)){
            return new ModelAndView(parametros,"index.html");
        }
//        response.cookie("username",request.cookie("username"));
//        response.cookie("organizacion",request.cookie("organizacion"));
        return new ModelAndView(parametros,"Calcular_HC.html");
    }

    public ModelAndView registrarMedicionesHTML (Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (!chequearCookie(request,response)){
            return new ModelAndView(parametros,"index.html");
        }
//        response.cookie("username",request.cookie("username"));
//        response.cookie("organizacion",request.cookie("organizacion"));
        return new ModelAndView(parametros,"Registrar_Mediciones.html");
    }

    public ModelAndView generarReporteHTML (Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (!chequearCookie(request,response)){
            return new ModelAndView(parametros,"index.html");
        }
//        response.cookie("username",request.cookie("username"));
//        response.cookie("organizacion",request.cookie("organizacion"));
        return new ModelAndView(parametros,"Generar_Reporte.html");
    }

    public String getAllOrganizaciones(Request request, Response response) {
        response.type("application/json");

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();

        Optional<List<Organizacion>> organizaciones = Optional.ofNullable(repositorioOrganizacionesDB.getOrganizaciones());


        if(organizaciones.isPresent()){
            JsonElement organizacionesJson = ParserJSONOrganizacion.organizacionesJsonElement(organizaciones.get());

            response.body(new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,"envio organizaciones",organizacionesJson )));
            response.status(200);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,"envio organizaciones",organizacionesJson ));
        }


        response.body(new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,"no hay ninguna organizacion",null)));
        response.status(200);

        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,"no hay ninguna organizacion",null));
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
