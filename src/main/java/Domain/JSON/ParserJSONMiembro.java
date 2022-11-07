package Domain.JSON;

import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.Espacios.Estacion;
import Domain.MediosDeTransporte.*;

import Domain.Repositorios.RepositorioDireccionDB;
import Domain.Repositorios.RepositorioTransportePublicoDB;
import Domain.Repositorios.RepositorioVehiculoParticularDB;
import Domain.Trayecto.Tramo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ParserJSONMiembro {

    //----------Desde JSON-------------//
    public MedioDeTransporte JSONAMedioTransporte(JSONObject obj)
    {
        if(obj.get("tipo").equals("vehiculoParticular"))
        {
            RepositorioVehiculoParticularDB repositorioVehiculoParticularDB = new RepositorioVehiculoParticularDB();
            VehiculoParticular aux = repositorioVehiculoParticularDB.buscarVehiculoParticular((String)obj.get("vehiculo"), (String)obj.get("combustible"), (Integer)obj.get("cantidadCompratido"));
            if(aux != null)
                return aux;
            else{
                VehiculoParticular vehiculo = new VehiculoParticular(
                        TipoVehiculo.valueOf((String)obj.get("vehiculo")),
                        TipoCombustible.valueOf((String)obj.get("combustible")),
                        (Integer) obj.get("cantidadCompratido")
                );

                repositorioVehiculoParticularDB.agregar(vehiculo);
                return vehiculo;
            }
        }
        else if(obj.get("tipo").equals("transportePublico"))
        {
            RepositorioTransportePublicoDB repositorioTransportePublicoDB = new RepositorioTransportePublicoDB();
            TransportePublico transportePublico = repositorioTransportePublicoDB.buscarTransportePublico((Integer)obj.get("id_transporte"));
            return transportePublico;
        }
        else
        {
            return new VehiculoParticular(TipoVehiculo.BiciPie, TipoCombustible.NoConsume, 1);
        }
    }


    public Tramo JSONATramo(JSONObject obj)
    {
        RepositorioDireccionDB repositorioDireccionDB = new RepositorioDireccionDB();

        JSONArray direcciones = new JSONArray();

        JSONObject jsonDirSalida = (JSONObject) direcciones.get(0);
        JSONObject jsonDirLlegada = (JSONObject) direcciones.get(1);

        Direccion direccionSalida = repositorioDireccionDB.buscarDireccion(
                (String)jsonDirSalida.get("pais"),
                (String)jsonDirSalida.get("provincia"),
                (String)jsonDirSalida.get("municipio"),
                (String)jsonDirSalida.get("localidad"),
                (String)jsonDirSalida.get("calle"),
                (Integer)jsonDirSalida.get("altura"),
                (String)jsonDirSalida.get("tipoDireccion")
        );

        Direccion direccionLlegada = repositorioDireccionDB.buscarDireccion(
                (String)jsonDirLlegada.get("pais"),
                (String)jsonDirLlegada.get("provincia"),
                (String)jsonDirLlegada.get("municipio"),
                (String)jsonDirLlegada.get("localidad"),
                (String)jsonDirLlegada.get("calle"),
                (Integer)jsonDirLlegada.get("altura"),
                (String)jsonDirLlegada.get("tipoDireccion")
        );

        Tramo tramo = new Tramo(
                direccionSalida,
                direccionLlegada,
                JSONAMedioTransporte((JSONObject) obj.get("medioTransporte"))
        );

        return tramo;
    }

    //---------------hacia JSON-------------------//
    public JSONObject medioTransporteAJSON(MedioDeTransporte medio)
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

    public JSONObject EspacioAJSONObject(Espacio espacio)
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

    public JSONObject tramoAJSONObject(Tramo tramo)
    {
        JSONObject tramoObj = new JSONObject();
        tramoObj.put("puntoSalida", EspacioAJSONObject(tramo.getPuntoPartida()));
        tramoObj.put("puntoLlegada", EspacioAJSONObject(tramo.getPuntoLlegada()));
        tramoObj.put("medioTransporte", this.medioTransporteAJSON(tramo.getMedioTransporte()));

        return tramoObj;
    }


}
