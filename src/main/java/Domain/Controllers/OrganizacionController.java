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
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class OrganizacionController {

  public Object getOrganizacion(Request req, Response res) throws ParseException {
      String nombreOg = req.params(":nombre");

      RepositorioOrganizacionesDB repoOrganizacionesDB = new RepositorioOrganizacionesDB();
      Organizacion org = repoOrganizacionesDB.buscarOrganizacion(nombreOg);

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


  public void respuestaListaAceptarMiembro(Request request, Response response)
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
    }

    public void respuestaCalcularHC(Request request, Response response) throws IOException {
        String username = request.cookie("username");
        //TODO:buscar por nombre usuario
        //Organizacion organizacion = RepositorioOrganizacion.buscarPorID(idOrg);
        Organizacion organizacion = null;
        String mes = request.queryParams("Mes");
        String anio = request.queryParams("Anio");

        Double resultado = organizacion.calcularHC(Integer.parseInt(mes), Integer.parseInt(anio));

        response.type("application/json");
        response.body("{ \"resultado\":" + resultado.toString() + "}" );
            return;
    }

    public Object cargarMediciones(Request request, Response response) throws ServletException, IOException {
        String username = request.cookie("username");

        //TODO: verificar como subir archivo
        Path path = Paths.get(request.queryParams("archivo_mediciones"));
        byte[] buffer = java.nio.file.Files.readAllBytes(path);

        File mediciones = new File("../Utils/" + username + ".xls");

        OutputStream outStream = new FileOutputStream(mediciones);
        outStream.write(buffer);

        //TODO:buscar organizacion por nombre usuario
        Organizacion organizacion = null;
        organizacion.cargarMedicionesInternas(username + ".xls");
        return null;
    }

}
