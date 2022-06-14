package Domain.Trayecto;

import Domain.Espacios.Espacio;
import Domain.MediosDeTransporte.Estacion;
import org.apache.poi.ddf.EscherRecord;

public class Tramo {
    //////////////////////////////////  INTERFACE
    public double determinarDistancia(){
        return 0.0;
    }

    Espacio getPuntoPartida() {
        return null;
    }

    Espacio getPuntoLlegada() {
        return null;
    }
}
