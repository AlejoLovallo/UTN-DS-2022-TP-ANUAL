package Domain.Repositorios;

import Domain.Usuarios.Admin;
import Domain.Usuarios.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioAdminDB extends Repositorio<Admin> {
  public RepositorioAdminDB() {
    super(new DBHibernate<Admin>(Admin.class));
  }

  public Usuario buscarAdmin(String nombreDeUsuario){
    return this.dbService.buscar(condicionUsername(nombreDeUsuario));
  }

  private BusquedaCondicional condicionUsername(String nombreDeUsuario){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Admin> usuarioQuery = criteriaBuilder.createQuery(Admin.class);

    Root<Admin> condicionRaiz = usuarioQuery.from(Admin.class);

    Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("username"), nombreDeUsuario);
    //Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("password"), contrasenia);

    //Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

    usuarioQuery.where(condicionNombreDeUsuario);

    return new BusquedaCondicional(null, usuarioQuery);
  }
}