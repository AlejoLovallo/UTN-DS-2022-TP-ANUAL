package Domain.Repositorios;

import Domain.Miembro.Persona;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Recomendacion;
import Domain.Organizacion.Sector;
import Domain.Organizacion.SolicitudPendiente;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

public class RepositoriorRecomendacionesDB extends Repositorio{
    public RepositoriorRecomendacionesDB() {
        super(new DBHibernate<Recomendacion>(Recomendacion.class));
    }

    public Recomendacion crearRecomendacion(Organizacion organizacion, String recomendacion){
        Recomendacion recomendacion1 = new Recomendacion();
        recomendacion1.setOrganizacion(organizacion);
        recomendacion1.setRecomendacion(recomendacion);

        this.dbService.agregar(recomendacion1);
        return recomendacion1;
    }

    public Recomendacion buscarRecomendaciones(Organizacion organizacion){
        return (Recomendacion) this.dbService.buscar(condicionRecomendacion(organizacion));
    }

    private BusquedaCondicional condicionRecomendacion(Organizacion organizacion){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Recomendacion> recomendacionCriteriaQuery = criteriaBuilder.createQuery(Recomendacion.class);

        Root<Recomendacion> root = recomendacionCriteriaQuery.from(Recomendacion.class);

        Predicate condicionPorOrganizacion = criteriaBuilder.like(root.get("id_organizacion"), "%"+ organizacion.getId() +"%");

        Predicate condicionExisteRecomendacion = criteriaBuilder.and(condicionPorOrganizacion);

        recomendacionCriteriaQuery.where(condicionExisteRecomendacion);

        return new BusquedaCondicional(null, recomendacionCriteriaQuery);
    }
}
