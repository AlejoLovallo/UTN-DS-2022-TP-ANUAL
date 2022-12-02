package Domain.CalculadorDistancia;

import java.util.Map.Entry;

import Domain.Espacios.Espacio;
import Domain.Espacios.Estacion;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.TransportePublico;

public class ServicioManual extends ServicioDistancia{

    @Override
    public Double calcularDistancia(MedioDeTransporte medioDeTransporte, Espacio puntoPartida, Espacio puntoLLegada){

        Double distanciaTotal = 0.0;

        TransportePublico transportePublico = (TransportePublico) medioDeTransporte;
        Estacion estacionInicial = (Estacion) puntoLLegada;
        Estacion estacionFinal = (Estacion) puntoPartida;

        Boolean flag = false;

        for (Entry<Estacion, Double> entry : transportePublico.getParadas().entrySet()){

            if(entry.getKey().equals(estacionInicial))
                flag = true;
            if(entry.getKey().equals(estacionFinal))
                flag = false;
            if (flag)
                distanciaTotal += transportePublico.getParadas().get(entry.getKey());
        }

        return distanciaTotal;
    }
}
