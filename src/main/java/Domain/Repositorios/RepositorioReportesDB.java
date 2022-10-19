package Domain.Repositorios;

import Domain.Organizacion.AgenteSectorial;
import Domain.Reportes.Reporte;
import Domain.Reportes.TipoDeReporte;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RepositorioReportesDB extends Repositorio<Reporte>{
  public RepositorioReportesDB() {
    super(new DBHibernate<Reporte>(Reporte.class));
  }

  public List<Reporte> getReportes() {

    return this.dbService.buscarTodos();
  }

  //////////////////////////////////  SETTERS

  //////////////////////////////////  INTERFACE

  public void agregarReporte(Reporte reporte){
    this.dbService.agregar(reporte);
  }

  public Boolean existeReporte(String nombre, TipoDeReporte tipoDeReporte, LocalDate fechaDesde, LocalDate fechaHasta){

    return conseguirReporte(nombre, tipoDeReporte, fechaDesde, fechaHasta).isPresent();

  }

  public Optional<Reporte> conseguirReporte(String nombre, TipoDeReporte tipoDeReporte, LocalDate fechaDesde, LocalDate fechaHasta){

    return Optional.ofNullable(this.dbService.buscar(condicionReportePor(nombre, tipoDeReporte, fechaDesde, fechaHasta)));
  }


  private BusquedaCondicional condicionReportePor(String nombre,TipoDeReporte tipoDeReporte, LocalDate fechaDesde, LocalDate fechaHasta ){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Reporte> reporteCriteriaQuery = criteriaBuilder.createQuery(Reporte.class);

    Root<Reporte> root = reporteCriteriaQuery.from(Reporte.class);

    Predicate condicionnPorNombre = criteriaBuilder.equal(root.get("nombre"),  nombre);

    Predicate condicionnPorTipoReporte = criteriaBuilder.equal(root.get("tipoDeReporte"),  tipoDeReporte);

    Predicate condicionnPorFechaDesde = criteriaBuilder.equal(root.get("fechaDesde"),  fechaDesde);

    Predicate condicionnPorFechaHasta = criteriaBuilder.equal(root.get("fechaHasta"),  fechaHasta);

    Predicate condicionA = criteriaBuilder.and(condicionnPorNombre,condicionnPorTipoReporte);

    Predicate condicionB = criteriaBuilder.and(condicionnPorFechaDesde,condicionnPorFechaHasta);

    Predicate condicionFinal = criteriaBuilder.and(condicionA,condicionB);

    reporteCriteriaQuery.where(condicionFinal);

    return new BusquedaCondicional(null, reporteCriteriaQuery);
  }

}
