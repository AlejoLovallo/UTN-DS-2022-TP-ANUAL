package Domain.Trayecto;

import Domain.Espacios.Espacio;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.VehiculoParticular;

public class TramoVehiculoParticular {

    private Espacio puntoPartida;
    private Espacio puntoLLegada;

    private VehiculoParticular vehiculoParticular;

    //////////////////////////////////  CONSTRUCTOR

    public TramoVehiculoParticular(Espacio puntoPartida, Espacio puntoLLegada) {
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


    public void setPuntoPartida(Espacio puntoPartida) {
        this.puntoPartida = puntoPartida;
    }

    public void setPuntoLLegada(Espacio puntoLLegada) {
        this.puntoLLegada = puntoLLegada;
    }


    //////////////////////////////////  INTERFACE

    public double determinarDistancia(){
        double distanciaTotal = 0;
        return distanciaTotal;
    }
}
