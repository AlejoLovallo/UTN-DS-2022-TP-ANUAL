package Domain.Repositorios;

import Domain.BaseDeDatos.EntityManagerHelper;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class Repositorio<T> {
  protected DBService<T> dbService;

  public Repositorio(DBService<T> dbService) {
    this.dbService = dbService;
  }

  public void setDao(DBService<T> dbService) {
    this.dbService = dbService;
  }

  public void agregar(Object unObjeto){
    if(!unObjeto.getClass().getSimpleName().equalsIgnoreCase("bitacora") ) {
      this.dbService.agregar(unObjeto);
    }
  }

  public void modificar(Object unObjeto){
    if(!unObjeto.getClass().getSimpleName().equalsIgnoreCase("bitacora") ) {
      this.dbService.modificar(unObjeto);
    }
  }

  public void eliminar(Object unObjeto){

    this.dbService.eliminar(unObjeto);
  }

  public List<T> buscarTodos(){
    return this.dbService.buscarTodos();
  }

  public List<T> buscarTodosPorQuery(String query){
    return this.dbService.buscarTodosPorQuery(query);
  }

  public T buscar(int id){

    return this.dbService.buscar(id);
  }

  public CriteriaBuilder criteriaBuilder(){
    return EntityManagerHelper.getEntityManager().getCriteriaBuilder();
  }
}