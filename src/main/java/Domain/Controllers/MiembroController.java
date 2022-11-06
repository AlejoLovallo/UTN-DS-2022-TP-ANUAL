package Domain.Controllers;

import Domain.CalculadorHC.CalculadorHC;
import Domain.JSON.ParserJSONMiembro;
import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MiembroController {

    public void agregarTrayecto(Request request, Response response) throws ParseException {
        //Miembro miembr = TODO buscar miembro

        JSONParser parser = new JSONParser();
        JSONObject pedido = (JSONObject) parser.parse(request.body());

        //TODO: buscar si ya existe
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
    }

    public void menuRegistrarTrayectos(Request request, Response response)
    {
        String username = request.session().attribute("username");

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
    }

    public void respuestaCalcularHC(Request request, Response response) throws IOException {
        String username = request.session().attribute("username");
        String mes = request.queryParams("Mes");
        String anio = request.queryParams("Anio");

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona persona = repositorioPersonasDB.buscarPersonaPorUsername(username);

        Double resultado = 0.0;
        for(Miembro miembro : persona.getMiembros())
            resultado += miembro.calcularHC(Integer.parseInt(mes), Integer.parseInt(anio));

        response.type("application/json");
        response.body("{ \"resultado\":" + resultado.toString() + "}");
        return;
    }
}
