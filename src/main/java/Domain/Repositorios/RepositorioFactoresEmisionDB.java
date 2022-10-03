package Domain.Repositorios;

import Domain.CalculadorHC.FactorEmision;

public class RepositorioFactoresEmisionDB extends Repositorio<FactorEmision>{


  public RepositorioFactoresEmisionDB() {
    super(new DBHibernate<FactorEmision>(FactorEmision.class));
  }
}
