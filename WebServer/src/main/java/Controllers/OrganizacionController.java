package Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.io.File;


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

  public Object getOrganizacion(Request req, Response res)  {


      return null;
  }

 public Object crearOrganizacion(Request req, Response res) {
  return null;
 }

  public Object modificarOrganizacion(Request req, Response res) {
    return null;
  }

  public Object respuestaListaMiembros(Request request, Response response)
  {
      return null;
  }

    public Object respuestaAceptarMiembro(Request request, Response response)
    {

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

    public void respuestaCalcularHC(Request request, Response response)  {

    }

    public Object cargarMediciones(Request request, Response response)  {

        return null;
    }


    public ModelAndView mostrarReportes(Request request, Response response)
    {
        return null;
    }


    public ModelAndView listarRecomendaciones(Request request, Response response){
        return null;
    }
}
