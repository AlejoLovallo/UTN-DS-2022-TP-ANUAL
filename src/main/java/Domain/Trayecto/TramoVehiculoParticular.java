package Domain.Trayecto;

import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.VehiculoParticular;

public class TramoVehiculoParticular extends Tramo {

    private Direccion puntoPartida;
    private Direccion puntoLLegada;
    private VehiculoParticular vehiculoParticular;

    //////////////////////////////////  CONSTRUCTOR

    public TramoVehiculoParticular(Direccion puntoPartida, Direccion puntoLLegada, VehiculoParticular vehiculoParticular) {
        this.puntoPartida = puntoPartida;
        this.puntoLLegada = puntoLLegada;
        this.vehiculoParticular = vehiculoParticular;
    }

    //////////////////////////////////  GETTERS
    public Espacio getPuntoLLegada() {
        return puntoLLegada;
    }

    public Espacio getPuntoPartida() {
        return puntoPartida;
    }

    public VehiculoParticular getVehiculoParticular() {
        return vehiculoParticular;
    }

    //////////////////////////////////  SETTERS

    public void setPuntoPartida(Direccion puntoPartida) {
        this.puntoPartida = puntoPartida;
    }

    public void setPuntoLLegada(Direccion puntoLLegada) {
        this.puntoLLegada = puntoLLegada;
    }

    public void setVehiculoParticular(VehiculoParticular vehiculoParticular) {
        this.vehiculoParticular = vehiculoParticular;
    }


    //////////////////////////////////  INTERFACE

    public double determinarDistancia(){
        double distanciaTotal = 0;
        return distanciaTotal;
    }
}
