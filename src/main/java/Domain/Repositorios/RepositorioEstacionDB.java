package Domain.Repositorios;

import Domain.Espacios.Estacion;
import Domain.Organizacion.Organizacion;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioEstacionDB extends Repositorio<Estacion>{
  public RepositorioEstacionDB() {
    super(new DBHibernate<Estacion>(Estacion.class));
  }

  public Estacion buscarEstacionPorNombre(String nombre){

    return this.dbService.buscar(condicionEstacionPorNombre(nombre));

  }

  private BusquedaCondicional condicionEstacionPorNombre(String nombre) {
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Estacion> estacionCriteriaQuery = criteriaBuilder.createQuery(Estacion.class);

    Root<Estacion> condicionRaiz = estacionCriteriaQuery.from(Estacion.class);

    //condicionRaiz.join("usuario");

    Predicate condicionNombre = criteriaBuilder.equal(condicionRaiz.get("nombre"),  nombre );
    //Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("password"), contrasenia);

    //Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

    estacionCriteriaQuery.where(condicionNombre);

    return new BusquedaCondicional(null, estacionCriteriaQuery);
  }
}
