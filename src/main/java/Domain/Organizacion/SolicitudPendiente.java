package Domain.Organizacion;

import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;

public class SolicitudPendiente {

    private Persona persona;
    private Sector sector;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
}
