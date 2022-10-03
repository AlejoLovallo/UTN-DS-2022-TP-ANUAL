package Domain.Repositorios;

import Domain.Reportes.Reporte;

public class RepositorioReportesDB extends Repositorio<Reporte>{
  public RepositorioReportesDB() {
    super(new DBHibernate<Reporte>(Reporte.class));
  }
}
