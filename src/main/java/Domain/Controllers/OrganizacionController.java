package Domain.Controllers;

import Domain.CalculadorHC.CalculadorHC;
import Domain.JSON.ParserJSONMiembro;
import Domain.JSON.ParserJSONOrganizacion;
import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Organizacion.SolicitudPendiente;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Repositorios.RepositorioSolicitudesDB;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import Domain.Usuarios.Usuario;
import com.mchange.v2.io.FileUtils;
import org.eclipse.jetty.util.ajax.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.Request;
import spark.Response;

import javax.servlet.ServletException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrganizacionController {

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
      res.body(resOrganizacion.toJSONString());

      return null;
  }

 public Object crearOrganizacion(Request req, Response res) throws ParseException{
  return null;
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
              listaMiembros.add(ParserJSONMiembro.miembroToJSON(miembro));
          }
      }

      response.body(listaMiembros.toString());
      return null;
  }

    public Object respuestaAceptarMiembro(Request request, Response response)
    {
        String username = request.cookie("username");

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        String idPersona = request.queryParams("Persona");
        String idSector = request.queryParams("Sector");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorNroDocumento(idPersona);

        Optional<Sector> sector = organizacion.getSectores().stream().filter(
                s -> s.getId_sector() == Integer.parseInt(idSector)
        ).findAny();

        Optional<Miembro> miembro = sector.get().getMiembros().stream().filter(
                m -> m.getPersona().equals(persona)
        ).findAny();

        miembro.get().setActivo(true);

        //TODO: devolver mensaje confirmando ejecucion de operacion
        return null;
    }

  /*public void respuestaListaAceptarMiembro(Request request, Response response)
  {
      String username = request.cookie("username");
      //TODO:buscar por nombre usuario
      //Organizacion organizacion = RepositorioOrganizacion.buscarPorID(idOrg);
      Organizacion organizacion;

        //TODO: buscar solicitudes por organizacion
        List<SolicitudPendiente> solMiembros = RepositorioSolicitudesDB.buscarSolicitudesOrganizacion(organizacion);
        response.type("application/json");

        JSONArray listaSolicitudes = new JSONArray();
        for(SolicitudPendiente sol : solMiembros)
        {
            JSONObject solicitudJSON = new JSONObject();
            solicitudJSON.put("persona", ParserJSONMiembro.personaAJSON(sol.getPersona()));
            solicitudJSON.put("sector", ParserJSONMiembro.sectorAJSON(sol.getSector()));
            listaSolicitudes.add(solicitudJSON);
        }

        response.body(listaSolicitudes.toString());
        return;
    }

    public void respuestaAceptarMiembro(Request request, Response response)
    {
        String username = request.cookie("username");
        //TODO:buscar por nombre usuario
        //Organizacion organizacion = RepositorioOrganizacion.buscarPorID(idOrg);
        Organizacion organizacion = null;
        String idPersona = request.queryParams("Persona");
        String idSector = request.queryParams("Sector");

        Sector sectorElegido = null;
        for(Sector sector : organizacion.getSectores())
        {
            if(sector.getNombre().equals(idSector))
            {
                sectorElegido = sector;
                break;
            }
        }
        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorNroDocumento(idPersona);

        Miembro miembro = new Miembro(persona.getNombre(), sectorElegido);
        sectorElegido.getMiembros().add(miembro);
        persona.getMiembros().add(miembro);

        //buscar solicitud
        RepositorioSolicitudesDB repositorioSolicitudesDB = new RepositorioSolicitudesDB();
        SolicitudPendiente solicitudAEliminar = repositorioSolicitudesDB.buscarSolicitudPendiente(persona, sectorElegido);
        repositorioSolicitudesDB.eliminar(solicitudAEliminar);
        //eliminar solicitud

        //RECALCULAR MIEMBROS PARA MOSTRAR LISTA ACTUALIZADA
        respuestaListaAceptarMiembro(request, response);
        return;
    }*/

    public void respuestaCalcularHC(Request request, Response response) throws IOException {
        String username = request.cookie("username");

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        String mes = request.queryParams("Mes");
        String anio = request.queryParams("Anio");

        Double resultado = organizacion.calcularHC(Integer.parseInt(mes), Integer.parseInt(anio));

        response.type("application/json");
        response.body("{ \"resultado\":" + resultado.toString() + "}" );
            return;
    }

    public Object cargarMediciones(Request request, Response response) throws ServletException, IOException {
        String username = request.cookie("username");

        File mediciones = new File("src/main/Domain/Utils/" + username + ".xls");

        InputStream is = request.raw().getPart("archivo_mediciones").getInputStream();
        Files.copy(is, mediciones.toPath());

        RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
        Usuario usuario = repositorioUsuariosDB.buscarUsuario(username);

        RepositorioOrganizacionesDB repositorioOrganizacionesDB = new RepositorioOrganizacionesDB();
        Organizacion organizacion = repositorioOrganizacionesDB.buscarOrganizacionPorUsuario(usuario);

        organizacion.cargarMedicionesInternas(username + ".xls");
        return null;
    }

}
