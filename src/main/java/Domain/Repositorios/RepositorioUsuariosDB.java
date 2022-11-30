package Domain.Repositorios;

import Domain.Usuarios.Admin;
import Domain.Usuarios.Excepciones.ContraseniaEsInvalidaException;
import Domain.Usuarios.Excepciones.UsuarioException;
import Domain.Usuarios.Usuario;
import Domain.Repositorios.RepositorioAdminDB;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuariosDB extends Repositorio<Usuario> {

  public RepositorioUsuariosDB() {
    super(new DBHibernate<Usuario>(Usuario.class));
    this.usuariosBuffer = new ArrayList<>();
  }


  private List<Usuario> usuariosBuffer;

  public Boolean existe(String nombreDeUsuario, String contrasenia){
    return buscarUsuario(nombreDeUsuario, contrasenia) != null;
  }

  public Boolean existe(String nombreDeUsuario){
    Usuario usuario = null;
    usuario = buscarUsuario(nombreDeUsuario);
    return  usuario != null;
  }

  public Usuario buscarUsuario(String nombreDeUsuario, String contrasenia){
    return this.dbService.buscar(condicionUsuarioYContrasenia(nombreDeUsuario, contrasenia));
  }

  public Usuario buscarUsuario(String nombreDeUsuario){
    return this.dbService.buscar(condicionUsername(nombreDeUsuario));
  }

  private BusquedaCondicional condicionUsuarioYContrasenia(String nombreDeUsuario, String contrasenia){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

    Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

    Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("username"), nombreDeUsuario);
    Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("contraHasheada"), contrasenia);

    Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

    usuarioQuery.where(condicionExisteUsuario);

    return new BusquedaCondicional(null, usuarioQuery);
  }

  private BusquedaCondicional condicionUsername(String nombreDeUsuario){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

    Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

    Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("username"), nombreDeUsuario);
    //Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("password"), contrasenia);

    //Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

    usuarioQuery.where(condicionNombreDeUsuario);

    return new BusquedaCondicional(null, usuarioQuery);
  }


  //////////////////////////////////  INTERFACE
  public boolean validarUsuario(Usuario usuario, Boolean validacion){
    return validarUsuario(usuario.getUsername(),validacion);
  }

  public boolean validarUsuario(String username, Boolean validacion){
    //Usuario usuarioAValidar = this.usuarios.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    if(existe(username)){
      Usuario usuarioAValidar = buscarUsuario(username);
      usuarioAValidar.setValidado(validacion);
      this.dbService.modificar(usuarioAValidar);
      return true;
    }
    return false;
  }


  public Usuario validarLogueoUsuario(String username, String contra){

    RepositorioAdminDB repositorioAdminDB = new RepositorioAdminDB();
    Usuario usuarioConUsername = null;
    if(buscarUsuario(username) != null) usuarioConUsername = buscarUsuario(username);
    if (repositorioAdminDB.buscarAdmin(username) != null) usuarioConUsername = repositorioAdminDB.buscarAdmin(username);

    if(usuarioConUsername == null) return null;

    //Si no encuentra el usuario por username
    if(!existe(username)){
      return null;
    }

    //Si el usuario esta validado
    if(!usuarioConUsername.isValido()){
      return null;
    }


    //Si el intento de contrasenia es fallido se devolvera nulo
    // lo pongo en los dos lados por si la contrasenia es correcta pero no paso el tiempo del ultimo acceso
    if(usuarioConUsername.isColision(contra)){
      if(usuarioConUsername.intentoAcceso()){
        modificar(usuarioConUsername);
        return usuarioConUsername;
      }
      throw new ContraseniaEsInvalidaException("no paso el tiempo de inicio de sesion");
    }

    //Si el intento de contrasenia es fallido se devolvera nulo
    // lo pongo en los dos lados por si la contrasenia es correcta pero no paso el tiempo del ultimo acceso
    //Y esto esta por la contrasenia es incorrecta se actualizara el intento de acceso
    usuarioConUsername.intentoAcceso();
    modificar(usuarioConUsername);
    throw new ContraseniaEsInvalidaException("la contrasenia no es la misma a la del usuario");
  }

  public Usuario crearUsuario(String username, String email, String contra, boolean validado){
    try{

      if(existe(username))
        throw new UsuarioException("porque ya hay un usuario con ese username");

      Usuario usuarioNuevo = new Usuario(username,email,contra,validado);
      //this.usuarios.add(usuarioNuevo);
      agregar(usuarioNuevo);
      return usuarioNuevo;
    }catch (UsuarioException e){
      return null;
    }
  }

  public Admin crearAdmin(String username, String email, String contra){
    try{

      if(existe(username))
        throw new UsuarioException("porque ya hay un usuario con ese username");

      Admin adminNuevo = new Admin(username,email,contra,true);
      //this.usuarios.add(adminNuevo);
      agregar(adminNuevo);
      return adminNuevo;
    }catch (UsuarioException e){
      return null;
    }
  }




}