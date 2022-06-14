package Domain.Trayecto;

import Domain.Espacios.Espacio;
import Domain.MediosDeTransporte.Estacion;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.TransportePublico;

public class TramoTransportePublico extends Tramo{

    private Estacion puntoPartida;
    private Estacion puntoLLegada;

    private TransportePublico transportePublico;


    //////////////////////////////////  CONSTRUCTOR
    public TramoTransportePublico(Estacion puntoPartida, Estacion puntoLLegada, TransportePublico transportePublico) {
        this.puntoPartida = puntoPartida;
        this.puntoLLegada = puntoLLegada;
        this.transportePublico = transportePublico;
    }

    //////////////////////////////////  GETTERS

    public Estacion getPuntoPartida() {
        return puntoPartida;
    }

    public Estacion getPuntoLLegada() {
        return puntoLLegada;
    }

    public TransportePublico getTransportePublico() {
        return transportePublico;
    }


    //////////////////////////////////  SETTERS

    public void setPuntoPartida(Estacion puntoPartida) {
        this.puntoPartida = puntoPartida;
    }

    public void setPuntoLLegada(Estacion puntoLLegada) {
        this.puntoLLegada = puntoLLegada;
    }

    public void setTransportePublico(TransportePublico transportePublico) {
        this.transportePublico = transportePublico;
    }


    //////////////////////////////////  INTERFACE

    public double determinarDistancia(){
        double distanciaTotal = 0.0;
        int estacionInicial = puntoPartida.numeroDeEstacion;
        int estacionFinal = puntoLLegada.numeroDeEstacion;
        for (int i = 0; i < transportePublico.getParadas().size(); i ++){
            if(transportePublico.paradas.get(i).getNumeroDeEstacion() > estacionInicial
                && transportePublico.getParadas().get(i).getNumeroDeEstacion() < estacionFinal){
                distanciaTotal += transportePublico.getParadas().get(i).getDistEstPos();
            }
        }
        return distanciaTotal;
    }
}
