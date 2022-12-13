package Domain.Repositorios;

import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.Miembro.Excepciones.PersonaException;
import Domain.Miembro.Persona;
import Domain.Miembro.TipoDocumento;
import Domain.Usuarios.Usuario;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

public class RepositorioPersonasDB extends Repositorio<Persona>{

  public RepositorioPersonasDB() {
    super(new DBHibernate<Persona>(Persona.class));
  }

  public Persona buscarPersonaPorUsuario(Usuario usuarioABuscar){
    return buscarPersonaPorUsername(usuarioABuscar.getUsername());
  }

  public Persona buscarPersonaPorUsername(String username){

    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

    if(!repositorioUsuariosDB.existe(username))
      return null;


    return this.dbService.buscar(condicionPersonaJoinUsuario(username));
  }

  public Persona buscarPersonaPorNroDocumento(String nroDocumento){


    return this.dbService.buscar(condicionPersonaPorNroDocumento(nroDocumento));
  }

  public Boolean existe(String nroDocumento){
    return buscarPersonaPorNroDocumento(nroDocumento)!=null;
  }

  public Boolean existe(Persona persona){
    return existe(persona.getNroDocumento());
  }





  public Persona crearPersona(String _nombre, String _apellido, TipoDocumento _tipoDocumento, String _documento, Usuario user){

    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
    RepositorioPersonasDB repositorioPersonasDB=new RepositorioPersonasDB();

    if(user == null) throw new PersonaException("El usuario es nulo");

    if(repositorioUsuariosDB.existe(user.getUsername())) throw new PersonaException("Ya hay un usuario en la DB con ese mismo username");
    if(repositorioPersonasDB.buscarPersonaPorUsuario(user)!=null) throw new PersonaException("Ya hay una persona con ese usuario en la DB");
    if(this.existe(_documento)) throw new PersonaException("Ya hay una persona con el mismo numero de Documento");


    Persona personaNueva = new Persona(_nombre,_apellido,_tipoDocumento,_documento);

    //TODO revisar
    repositorioUsuariosDB.agregarUsuarioUser(user);

    personaNueva.setUsuario(user);

    this.dbService.agregar(personaNueva);

    return personaNueva;
  }

  public Persona agregarPersona(Persona persona){
    Usuario userPersona = persona.getUsuario();

    if(userPersona == null) return null;

    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
    repositorioUsuariosDB.agregar(userPersona);

    this.agregar(persona);

    return persona;
  }

  private BusquedaCondicional condicionPersonaJoinUsuario(String nombreDeUsuario){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Persona> personaCriteriaQuery = criteriaBuilder.createQuery(Persona.class);

    Root<Persona> root = personaCriteriaQuery.from(Persona.class);

    Join<Persona,Usuario> personaUsuarioJoin = root.join("usuario",JoinType.INNER);

    //root.join("persona");

    //Predicate condicionNombreDeUsuario = criteriaBuilder.like(root.get("username"),"%"+ nombreDeUsuario+"%");
    //Predicate condicionContrasenia = criteriaBuilder.equal(root.get("password"), contrasenia);

    //Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

    personaCriteriaQuery.where(criteriaBuilder.like(personaUsuarioJoin.get("username"),"%"+ nombreDeUsuario+"%"));

    return new BusquedaCondicional(null, personaCriteriaQuery);
  }

  private BusquedaCondicional condicionPersonaPorNroDocumento(String nroDocumento){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Persona> personaCriteriaQuery = criteriaBuilder.createQuery(Persona.class);

    Root<Persona> condicionRaiz = personaCriteriaQuery.from(Persona.class);

    //condicionRaiz.join("usuario");

    Predicate condicionNroDocumento = criteriaBuilder.like(condicionRaiz.get("nroDocumento"), "%"+ nroDocumento +"%");
    //Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("password"), contrasenia);

    //Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

    personaCriteriaQuery.where(condicionNroDocumento);

    return new BusquedaCondicional(null, personaCriteriaQuery);
  }
}
