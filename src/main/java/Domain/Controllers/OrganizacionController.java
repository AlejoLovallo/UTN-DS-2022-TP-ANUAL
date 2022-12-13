package Domain.Controllers;

import Domain.CalculadorHC.CalculadorHC;
import Domain.JSON.ParserJSONMiembro;
import Domain.JSON.ParserJSONOrganizacion;
import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Organizacion.TipoOrganizacion;
import Domain.Reportes.ReporteComposicion;
import Domain.Reportes.ReporteEvolucion;
import Domain.Reportes.ReporteTotal;
import Domain.Repositorios.*;
import Domain.Usuarios.Usuario;
import com.google.gson.Gson;
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

        response.body(htmlToString("Visualizar_Reporte.html"));
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
     RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();

     String organizacionString = req.body();
     JSONParser jsonParser = new JSONParser();
     JSONObject org = (JSONObject) jsonParser.parse(organizacionString);

     Usuario usuario = new Usuario(
             (String)org.get("username"),
             (String)org.get("mail"),
             (String)org.get("password"),
             true
     );

        repositorioOrganizacionesDB.crearOrganizacion(
                (String)org.get("razon_social"),
                ClasificacionOrganizacion.valueOf((String)org.get("clasificacion")),
                TipoOrganizacion.valueOf((String)org.get("tipo")),
                null,
                usuario
        );

        res.cookie("username", usuario.getUsername());
        res.cookie("organizacion", (String) org.get("razon_social"));

     return new Gson()
             .toJson(new StandardResponse(StatusResponse.SUCCESS,"pantalla organizacion"));
 }

  public Object modificarOrganizacion(Request req, Response res) throws ParseException{
    return null;
  }

  public Object respuestaListaMiembros(Request request, Response response)
  {
      String username = request.cookie("username");

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
              listaMiembros.add(ParserJSONMiembro.miembroToJSON(miembro));
          }
      }
      response.status(200);
      return listaMiembros;
  }

    public Object respuestaAceptarMiembro(Request request, Response response) throws ParseException {
        String username = request.cookie("username");

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(request.body());
        String idPersona = (String) jsonObject.get("Persona");
        String idSector = (String) jsonObject.get("Sector");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorNroDocumento(idPersona);

        Optional<Sector> sector = organizacion.getSectores().stream().filter(
                s -> s.getId_sector() == Integer.parseInt(idSector)
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
        String username = request.cookie("username");

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(request.body());
        String idPersona = (String) jsonObject.get("Persona");
        String idSector = (String) jsonObject.get("Sector");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorNroDocumento(idPersona);

        Optional<Sector> sector = organizacion.getSectores().stream().filter(
                s -> s.getId_sector() == Integer.parseInt(idSector)
        ).findAny();

        Optional<Miembro> miembro = sector.get().getMiembros().stream().filter(
                m -> m.getPersona().equals(persona)
        ).findAny();

        persona.getMiembros().remove(miembro);
        repositorioPersonasDB.modificar(persona);

        response.type("text/javascript");
        response.status(200);

        return "window.alert(\"Operacion ejecutada exitosamente\")";
    }

    public Object respuestaCalcularHC(Request request, Response response) throws IOException, ParseException {
        

        String username = request.cookie("username");

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(request.body());
        String mesDesde = (String) jsonObject.get("MesDesde");
        String añoDesde = (String) jsonObject.get("AñoDesde");
        String mesHasta = (String) jsonObject.get("Mes");
        String añoHasta = (String) jsonObject.get("Anio");

        Integer MesDesde = Integer.parseInt(mesDesde);
        Integer AñoDesde = Integer.parseInt(añoDesde);
        Integer MesHasta = Integer.parseInt(mesHasta);
        Integer AñoHasta = Integer.parseInt(añoHasta);

        LocalDate fechaDesde = LocalDate.of(AñoDesde, MesDesde, 01);
        LocalDate fechaHasta = LocalDate.of(AñoHasta, MesHasta, 01);

        Double resultado = calculadorHC.calcularHcPeriodo(organizacion, fechaDesde, fechaHasta);

        response.type("application/json");

        JSONObject calculo = new JSONObject();
        calculo.put("resultado", resultado);

        response.status(200);
        return calculo;
    }

    public Object cargarMediciones(Request request, Response response) throws ServletException, IOException {
        String username = request.cookie("username");

        Path pathArchivo = Paths.get("src/main/java/Domain/Utils/" + username + ".xls");
        if(Files.exists(pathArchivo))
            Files.delete(pathArchivo);

        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        InputStream is = request.raw().getPart("archivo_mediciones").getInputStream();
        Files.copy(is, pathArchivo);

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        organizacion.cargarMedicionesInternas(username + ".xls");

        repositorioOrganizacionesDB.modificar(organizacion);

        response.type("text/javascript");
        response.status(200);
        return "window.alert(\"Operacion realizada exitosamente\")";
    }


    public ModelAndView mostrarReportes(Request request, Response response)
    {
        HashMap<String, Object> params = new HashMap<>();

        JSONArray listaReportes = new JSONArray();
        Optional<String> username = Optional.ofNullable(request.cookie("username"));

        //TODO para hacer pruebas
        username = Optional.of("usuarioNormal");

        //TODO podemos mostrar otra parte sino en vez de Recomendaciones
        if ( !username.isPresent() ) return new ModelAndView(params, "Recomendaciones.hbs");


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

        ArrayList<String> listaRecomendaciones = new ArrayList<>();

        listaRecomendaciones.add("recomendacion 1");
        listaRecomendaciones.add("recomendacion 2");
        listaRecomendaciones.add("recomendacion 3");

        params.put("recomendaciones", listaRecomendaciones);

        return new ModelAndView(params, "Recomendaciones.hbs");
    }

    public ModelAndView menuOrganizacion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        response.cookie("idSesion",request.cookie("idSesion"));
        response.cookie("username",request.cookie("username"));
        response.cookie("organizacion",request.cookie("organizacion"));


        String idSesion = request.cookie("idSesion");
        SesionManager sesionManager = SesionManager.get();
        Map<String,Object> atributos = sesionManager.obtenerAtributos(idSesion);

        //System.out.println(atributos.toString());


        return new ModelAndView(parametros,"Menu_Organizacion.html");
    }
}
