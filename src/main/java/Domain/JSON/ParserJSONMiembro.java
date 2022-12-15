package Domain.JSON;

import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.Espacios.Estacion;
import Domain.Espacios.TipoDireccion;
import Domain.MediosDeTransporte.*;

import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;
import Domain.Organizacion.Sector;
import Domain.Repositorios.RepositorioDireccionDB;
import Domain.Repositorios.RepositorioEstacionDB;
import Domain.Repositorios.RepositorioTransportePublicoDB;
import Domain.Repositorios.RepositorioVehiculoParticularDB;
import Domain.Trayecto.Tramo;
import Domain.Trayecto.Trayecto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.format.DateTimeFormatter;
import java.util.List;


public class ParserJSONMiembro {

    //----------Desde JSON-------------//
    public static MedioDeTransporte JSONAMedioTransporte(JSONObject obj)
    {
        if(obj.get("tipo").toString().equals("vehiculoParticular"))
        {
            //TODO: revisar que pasa si hay 2 vehiculos con los mismos atributos excepto por 'consumoPorKm'
            RepositorioVehiculoParticularDB repositorioVehiculoParticularDB = new RepositorioVehiculoParticularDB();
            VehiculoParticular aux = repositorioVehiculoParticularDB.buscarVehiculoParticular(obj.get("TipoVehiculo").toString(), obj.get("TipoCombustible").toString(), Integer.parseInt(obj.get("CantidadPasajeros").toString()), Double.parseDouble(obj.get("ConsumoPorKm").toString()));
            if(aux != null)
                return aux;
            else{
                return new VehiculoParticular(
                        TipoVehiculo.valueOf((String)obj.get("TipoVehiculo")),
                        TipoCombustible.valueOf((String)obj.get("TipoCombustible")),
                        Integer.parseInt(obj.get("CantidadPasajeros").toString()),
                        Double.parseDouble(obj.get("ConsumoPorKm").toString()));
                /*return repositorioVehiculoParticularDB.crearVehiculoParticular(
                        TipoVehiculo.valueOf((String)obj.get("TipoVehiculo")),
                        TipoCombustible.valueOf((String)obj.get("TipoCombustible")),
                        Integer.parseInt(obj.get("CantidadPasajeros").toString()),
                        Integer.parseInt(obj.get("ConsumoPorKm").toString())
                );*/
                //return repositorioVehiculoParticularDB.buscarVehiculoParticular((String)obj.get("vehiculo"), (String)obj.get("combustible"), ((Long)obj.get("cantidadPasajeros")).intValue(), Double.parseDouble(obj.get("ConsumoPorKm").toString()) );
            }
        }
        else if(obj.get("tipo").toString().equals("transportePublico"))
        {
            RepositorioTransportePublicoDB repositorioTransportePublicoDB = new RepositorioTransportePublicoDB();
            TransportePublico transportePublico = repositorioTransportePublicoDB.buscarTransportePublicoPorLinea(obj.get("Linea").toString());
            return transportePublico;
        }
        else
        {
            return new VehiculoParticular(TipoVehiculo.BiciPie, TipoCombustible.NoConsume, 1);
        }
    }


    public static Tramo JSONATramo(JSONObject obj)
    {
        RepositorioDireccionDB repositorioDireccionDB = new RepositorioDireccionDB();

        MedioDeTransporte medioDeTransporte = JSONAMedioTransporte(obj);

        if(medioDeTransporte instanceof VehiculoParticular){
            Direccion direccionSalida = repositorioDireccionDB.buscarDireccion(
                    obj.get("PaisSalida").toString(),
                    obj.get("ProvinciaSalida").toString(),
                    obj.get("MunicipioSalida").toString(),
                    obj.get("LocalidadSalida").toString(),
                    obj.get("CalleSalida").toString(),
                    Integer.parseInt(obj.get("AlturaSalida").toString()),
                    obj.get("TipoDireccionSalida").toString()

            );

            Direccion direccionLlegada = repositorioDireccionDB.buscarDireccion(
                    obj.get("PaisLlegada").toString(),
                    obj.get("ProvinciaLlegada").toString(),
                    obj.get("MunicipioLlegada").toString(),
                    obj.get("LocalidadLlegada").toString(),
                    obj.get("CalleLlegada").toString(),
                    Integer.parseInt(obj.get("AlturaLlegada").toString()),
                    obj.get("TipoDireccionLlegada").toString()
            );

            return new Tramo(
                    direccionSalida,
                    direccionLlegada,
                    medioDeTransporte
            );

        }
        else if(medioDeTransporte instanceof TransportePublico){
            RepositorioEstacionDB repositorioEstacionDB = new RepositorioEstacionDB();
            Estacion estacionSalida = repositorioEstacionDB.buscarEstacionPorNombre(obj.get("NombreSalida").toString());
            Estacion estacionLlegada = repositorioEstacionDB.buscarEstacionPorNombre(obj.get("NombreLlegada").toString());

            return new Tramo(
                    estacionSalida,
                    estacionLlegada,
                    medioDeTransporte
            );
        }
        return null;
    }

    //---------------hacia JSON-------------------//
    public static JSONObject medioTransporteAJSON(MedioDeTransporte medio)
    {
        JSONObject obj = new JSONObject();
        if(medio instanceof VehiculoParticular)
        {
            VehiculoParticular aux = (VehiculoParticular) medio;
            obj.put("tipo", "vehiculoParticular");
            obj.put("vehiculo", aux.getTipoVehiculo());
            obj.put("combustible", aux.getTipoCombustible());
            obj.put("cantidadCompartido", aux.getCantPasajeros());
        }
        else if(medio instanceof TransportePublico)
        {
            TransportePublico aux = (TransportePublico) medio;
            obj.put("tipo", "transportePublico");
            obj.put("vehiculo", aux.getTipoTransportePublico());
        }
        else
        {
            obj.put("tipo", "biciPie");
        }

        return obj;
    }

    public static JSONObject EspacioAJSONObject(Espacio espacio)
    {
        JSONObject obj = new JSONObject();

        if(espacio instanceof Direccion)
        {
            Direccion dir = (Direccion) espacio;
            obj.put("pais",dir.getPais());
            obj.put("provincia",dir.getProvincia());
            obj.put("localidad",dir.getLocalidad());
            obj.put("calle",dir.getCalle());
            obj.put("altura",dir.getAltura());
            obj.put("municipio",dir.getMunicipio());
            obj.put("tipoDireccion",dir.getTipoDireccion());
        }
        else
        {
            Estacion estacion = (Estacion) espacio;

            obj.put("nombre", estacion.getNombre());
        }
        return obj;
    }

    public static JSONObject tramoAJSONObject(Tramo tramo)
    {
        JSONObject tramoObj = new JSONObject();
        tramoObj.put("puntoSalida", EspacioAJSONObject(tramo.getPuntoPartida()));
        tramoObj.put("puntoLlegada", EspacioAJSONObject(tramo.getPuntoLlegada()));
        tramoObj.put("medioTransporte", ParserJSONMiembro.medioTransporteAJSON(tramo.getMedioTransporte()));

        return tramoObj;
    }

    public static JSONObject sectorAJSON(Sector sector)
    {
        JSONObject ret = new JSONObject();

        ret.put("organizacion", sector.getOrganizacion().getRazonSocial());
        ret.put("nombreSector", sector.getNombre());
        ret.put("id",sector.getId_sector());

        return ret;
    }

    public static JSONObject personaAJSON(Persona persona)
    {
        JSONObject ret = new JSONObject();

        ret.put("nombre", persona.getNombre());
        ret.put("apellido", persona.getApellido());
        ret.put("dni", persona.getNroDocumento());
        ret.put("tipoDeDocumento", persona.getTipoDocumento().toString());

        return ret;
    }

    public static JSONArray trayectosToJSON(List<Trayecto> trayectos)
    {
        JSONArray trayectosJSON = new JSONArray();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        for(Trayecto trayecto : trayectos)
        {
            JSONObject trayectoObj = new JSONObject();
            trayectoObj.put("frecuenciaSemanal", trayecto.getFrecuenciaSemanal());
            trayectoObj.put("fechaInicio", trayecto.getFechaInicio().format(formato));
            trayectoObj.put("fechaFin", trayecto.getFechaFin().format(formato));

            JSONArray tramos = new JSONArray();
            for(Tramo tramo : trayecto.getTramos())
                tramos.add(ParserJSONMiembro.tramoAJSONObject(tramo));

            trayectoObj.put("tramos", tramos);

            trayectosJSON.add(trayectoObj);
        }

        return trayectosJSON;
    }

    public static JSONObject miembroToJSON(Miembro miembro)
    {
        JSONObject obj = new JSONObject();

        obj.put("persona", ParserJSONMiembro.personaAJSON(miembro.getPersona()));
        obj.put("sector", ParserJSONMiembro.sectorAJSON(miembro.getSector()));
        obj.put("trayectos", trayectosToJSON(miembro.getTrayectos()));

        return obj;
    }
    public static JSONObject miembroSolicitudToJSON(Miembro miembro)
    {
        JSONObject obj = new JSONObject();

        obj.put("persona", ParserJSONMiembro.personaAJSON(miembro.getPersona()));
        obj.put("sector", ParserJSONMiembro.sectorAJSON(miembro.getSector()));

        return obj;
    }
}
