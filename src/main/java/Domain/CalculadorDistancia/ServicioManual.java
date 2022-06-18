package Domain.CalculadorDistancia;

import Domain.Espacios.Espacio;
import Domain.Espacios.Estacion;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.TransportePublico;

public class ServicioManual extends ServicioDistancia{

    @Override
    public Double calcularDistancia(MedioDeTransporte medioDeTransporte, Espacio puntoPartida, Espacio puntoLLegada){

    Double distanciaTotal = 0.0;

    TransportePublico transportePublico = (TransportePublico) medioDeTransporte;
    Estacion estacionInicial = (Estacion) puntoPartida;
    Estacion estacionFinal = (Estacion) puntoLLegada;
    Integer numeroEstacionInicial = estacionInicial.getNumeroDeEstacion();
    Integer numeroEstacionFinal = estacionFinal.getNumeroDeEstacion();

    if(numeroEstacionFinal < numeroEstacionInicial) {
        Integer aux = numeroEstacionFinal;
        numeroEstacionFinal = numeroEstacionInicial;
        numeroEstacionInicial = aux;
    }

    for (int i = numeroEstacionInicial; i < numeroEstacionFinal; i++){
        distanciaTotal += transportePublico.paradas.get(i).getDistEstPos();
    }

    return distanciaTotal;
  }
}
