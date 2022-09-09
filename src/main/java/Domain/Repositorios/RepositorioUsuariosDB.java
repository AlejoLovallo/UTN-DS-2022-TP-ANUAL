package Domain.Repositorios;

import Domain.Usuarios.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioUsuariosDB extends Repositorio<Usuario> {

  public RepositorioUsuariosDB(DBService<Usuario> usuarioService) {
    super(usuarioService);
  }

  public Boolean existe(String nombreDeUsuario, String contrasenia){
    return buscarUsuario(nombreDeUsuario, contrasenia) != null;
  }

  public Usuario buscarUsuario(String nombreDeUsuario, String contrasenia){
    return this.dbService.buscar(condicionUsuarioYContrasenia(nombreDeUsuario, contrasenia));
  }

  private BusquedaCondicional condicionUsuarioYContrasenia(String nombreDeUsuario, String contrasenia){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

    Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

    Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("usuario"), nombreDeUsuario);
    Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("password"), contrasenia);

    Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

    usuarioQuery.where(condicionExisteUsuario);

    return new BusquedaCondicional(null, usuarioQuery);
  }
}