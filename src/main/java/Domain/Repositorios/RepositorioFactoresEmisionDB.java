package Domain.Repositorios;

import Domain.CalculadorHC.Excepciones.FactorDeEmisionNoEncontradoException;
import Domain.CalculadorHC.FactorEmision;
import Domain.Organizacion.AgenteSectorial;
import Domain.Organizacion.TipoDeActividad;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioFactoresEmisionDB extends Repositorio<FactorEmision>{


  public RepositorioFactoresEmisionDB() {
    super(new DBHibernate<FactorEmision>(FactorEmision.class));
  }

  public List<FactorEmision> getFactoresDeEmision() {
    return this.dbService.buscarTodos();
  }

  public FactorEmision getFactorDeEmisionSegunActividad(TipoDeActividad tipoDeActividad) {

    Optional<FactorEmision> factor = Optional.ofNullable(this.dbService.buscar(condicionFactorPorTipoActividad(tipoDeActividad)));

    return factor.get();
  }

  public FactorEmision getFactorDeEmision(FactorEmision factor){
    return this.dbService.buscar(factor.getId());
  }


  //////////////////////////////////  SETTERS

  public void setFactoresDeEmision(List<FactorEmision> factoresDeEmision) {
    for (FactorEmision factorEmision:factoresDeEmision) {
      this.dbService.agregar(factorEmision);
    }
  }

  public boolean agregarFactorDeEmision(FactorEmision factor){
    agregar(factor);
    return true;
  }

  public boolean updateFactorDeEmision(FactorEmision factor){
    modificar(factor);
    return true;
  }

  private BusquedaCondicional condicionFactorPorTipoActividad(TipoDeActividad tipoDeActividad){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<FactorEmision> factorEmisionCriteriaQuery = criteriaBuilder.createQuery(FactorEmision.class);

    Root<FactorEmision> root = factorEmisionCriteriaQuery.from(FactorEmision.class);

    Predicate condicionnPorTipoDeActividad = criteriaBuilder.equal(root.get("tipoActividad"), tipoDeActividad );

    factorEmisionCriteriaQuery.where(condicionnPorTipoDeActividad);

    return new BusquedaCondicional(null, factorEmisionCriteriaQuery);
  }
}
