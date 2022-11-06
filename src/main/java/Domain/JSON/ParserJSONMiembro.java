package Domain.JSON;

import Domain.Espacios.Direccion;
import Domain.MediosDeTransporte.*;

import Domain.Repositorios.RepositorioDireccionDB;
import Domain.Repositorios.RepositorioTransportePublicoDB;
import Domain.Repositorios.RepositorioVehiculoParticularDB;
import Domain.Trayecto.Tramo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ParserJSONMiembro {

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
        //buscar Espacio por id

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

}
