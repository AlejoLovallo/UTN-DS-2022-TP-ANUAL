package Domain.Repositorios;

import Domain.MediosDeTransporte.TipoTransportePublico;
import Domain.MediosDeTransporte.TransportePublico;
import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.Miembro.Miembro;
import Domain.Miembro.Persona;
import Domain.Organizacion.Sector;
import Domain.Organizacion.SolicitudPendiente;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;

public class RepositorioSolicitudesDB extends Repositorio{
    public RepositorioSolicitudesDB() {
        super(new DBHibernate<SolicitudPendiente>(SolicitudPendiente.class));
    }

    public SolicitudPendiente crearSolicitud(Persona persona, Sector sector){
        SolicitudPendiente solicitudPendiente = new SolicitudPendiente();
        solicitudPendiente.setSector(sector);
        solicitudPendiente.setPersona(persona);

        this.dbService.agregar(solicitudPendiente);
        return solicitudPendiente;
    }

    public SolicitudPendiente buscarSolicitudPendiente(Persona persona, Sector sector){
        return (SolicitudPendiente) this.dbService.buscar(condicionSolicitud(persona, sector));
    }

    private BusquedaCondicional condicionSolicitud(Persona persona, Sector sector){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<SolicitudPendiente> solicitudPendienteCriteriaQuery = criteriaBuilder.createQuery(SolicitudPendiente.class);

        Root<SolicitudPendiente> root = solicitudPendienteCriteriaQuery.from(SolicitudPendiente.class);

        Predicate condicionPorPersona = criteriaBuilder.like(root.get("id_persona"), "%"+ persona.getId_persona() +"%");
        Predicate condicionPorSector = criteriaBuilder.like(root.get("id_sector"), "%"+ sector.getId_sector() +"%");

        Predicate condicionExisteSolicitud = criteriaBuilder.and(condicionPorPersona, condicionPorSector);

        solicitudPendienteCriteriaQuery.where(condicionExisteSolicitud);

        return new BusquedaCondicional(null, solicitudPendienteCriteriaQuery);
    }
}
