package Domain.Trayecto;

import Domain.Espacios.Espacio;
import Domain.MediosDeTransporte.Estacion;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.TransportePublico;

public class TramoTransportePublico extends Tramo2{

    private Estacion puntoPartida;
    private Estacion puntoLLegada;

    private TransportePublico transportePublico;
    //////////////////////////////////  CONSTRUCTOR

    public TramoTransportePublico(Estacion puntoPartida, Estacion puntoLLegada, MedioDeTransporte medio) {
        this.puntoPartida = puntoPartida;
        this.puntoLLegada = puntoLLegada;
    }

    //////////////////////////////////  GETTERS
    public String getPuntoPartida(){
        return this.puntoPartida.toString();
    }

    public String getPuntoLlegada(){
        return this.puntoLLegada.toString();
    }

    //////////////////////////////////  SETTERS

    public void setPuntoPartida(Estacion puntoPartida) {
        this.puntoPartida = puntoPartida;
    }

    public void setPuntoLLegada(Estacion puntoLLegada) {
        this.puntoLLegada = puntoLLegada;
    }

    //////////////////////////////////  INTERFACE

    public double determinarDistancia(){
        double distanciaTotal = 0.0;
        int estacionInicial = puntoPartida.numeroDeEstacion;
        int estacionFinal = puntoLLegada.numeroDeEstacion;
        for (int i = estacionInicial; i <= estacionFinal; i++){
            distanciaTotal += transportePublico.paradas.get(i).getDistEstPos();
        }
        return distanciaTotal;
    }
}
