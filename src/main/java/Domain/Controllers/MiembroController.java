package Domain.Controllers;

import Domain.JSON.ParserJSONMiembro;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.Request;
import spark.Response;

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
        //trayecto.setFechaInicio(LocalDate.format((String)pedido.get("fechaInicio")), formato);
        //trayecto.setFechaFin(LocalDate.format(pedido.get("fechaFin", formato)));

        List<Tramo> tramos = new ArrayList<>();
        ParserJSONMiembro parserJSONMiembro = new ParserJSONMiembro();
        for(Object tramo : (JSONArray)pedido.get("tramos"))
        {
            tramos.add(parserJSONMiembro.JSONATramo((JSONObject)tramo));
        }
    }
}
