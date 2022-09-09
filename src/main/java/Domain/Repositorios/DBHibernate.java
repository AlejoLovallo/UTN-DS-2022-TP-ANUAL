package Domain.Repositorios;

import Domain.BaseDeDatos.EntityManagerHelper;
import org.jetbrains.annotations.NotNull;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DBHibernate<T> implements DBService<T> {
  private Class<T> type;

  public DBHibernate(Class<T> type){
    this.type = type;
  }

  public List<T> buscarTodos() {
    CriteriaBuilder builder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
    CriteriaQuery<T> critera = builder.createQuery(this.type);
    critera.from(type);
    List<T> entities = EntityManagerHelper.getEntityManager().createQuery(critera).getResultList();
    return entities;
  }

  public T buscar(int id) {
    return EntityManagerHelper.getEntityManager().find(type, id);
  }

  public List<T> buscarTodosPorQuery(String query) {
    try {
      return (List<T>) EntityManagerHelper.getEntityManager()
          .createQuery(query);
    }
    catch (NoResultException ex){
      return null;
    }
  }

  public T buscar(@NotNull BusquedaCondicional condicional) {
    try {
      return (T) EntityManagerHelper.getEntityManager()
          .createQuery(condicional.getCondicionCritero())
          .getSingleResult();
    }
    catch (NoResultException ex){
      return null;
    }
  }

  public void agregar(Object unObjeto) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    EntityManagerHelper.getEntityManager().persist(unObjeto);
    EntityManagerHelper.getEntityManager().getTransaction().commit();
  }

  public void modificar(Object unObjeto) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    EntityManagerHelper.getEntityManager().merge(unObjeto);
    EntityManagerHelper.getEntityManager().getTransaction().commit();
  }

  public void eliminar(Object unObjeto) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    EntityManagerHelper.getEntityManager().remove(unObjeto);
    EntityManagerHelper.getEntityManager().getTransaction().commit();
  }
}