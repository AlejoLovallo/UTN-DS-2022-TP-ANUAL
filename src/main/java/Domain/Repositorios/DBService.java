
package Domain.Repositorios;

import Domain.Repositorios.BusquedaCondicional;

import java.util.List;

public interface DBService<T> {
  List<T> buscarTodos();
  T buscar(int id);
  T buscar(BusquedaCondicional condicional);
  List<T> buscarTodosPorQuery(String query);
  void agregar(Object unObjeto);
  void modificar(Object unObjeto);
  void eliminar(Object unObjeto);
}