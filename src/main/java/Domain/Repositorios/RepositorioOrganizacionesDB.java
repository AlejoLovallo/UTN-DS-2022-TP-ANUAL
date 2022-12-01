package Domain.Repositorios;

import Domain.Notificaciones.MailSender;
import Domain.Notificaciones.Notificar;
import Domain.Notificaciones.NotificarGuiaDeRecomendaciones;
import Domain.Organizacion.ClasificacionOrganizacion;
import Domain.Organizacion.Excepciones.OrganizacionException;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.TipoOrganizacion;
import Domain.Usuarios.Contacto;
import Domain.Usuarios.Excepciones.UsuarioException;
import Domain.Usuarios.Usuario;
import Domain.Organizacion.SolicitudPendiente;

import javax.persistence.criteria.*;
import java.util.List;

public class RepositorioOrganizacionesDB extends Repositorio<Organizacion>{

  public RepositorioOrganizacionesDB() {
    super(new DBHibernate<Organizacion>(Organizacion.class));
  }

  public List<Organizacion> getOrganizaciones() {
    return this.dbService.buscarTodos();
  }

  //////////////////////////////////  SETTERS

  //////////////////////////////////  INTERFACE

  //TODO REVISAR QUE NO SE PUEDE MODIFIAR LA NOTIFICACION
  public void enviarMailDeRecomendaciones() {

    List<Organizacion> organizaciones = this.dbService.buscarTodos();
    if(!organizaciones.isEmpty()){
      Notificar notificar = new NotificarGuiaDeRecomendaciones();
      //El mail tiene el factor de dos pasos
      String mail = "grupo7dds2022@gmail.com";
      String password = "jczcefspehwrnvqz";
      MailSender mailSender = new MailSender(mail,password);

      /*
      for (Organizacion org : organizaciones){
        System.out.println(org.getContacto().toString());
      }
      for (Organizacion org : organizaciones){
        mailSender.enviarNotificacion(org.getContacto(), notificar);
      }*/

      organizaciones.forEach(organizacion -> mailSender.enviarNotificacion(organizacion.getContacto(), notificar));

    }
  }

  public Organizacion buscarOrganizacionPorUsuario(Usuario usuario){
    return buscarOrganizacionPorUsername(usuario.getUsername());
  }

  public Organizacion buscarOrganizacionPorUsername(String username){

    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

    if(!repositorioUsuariosDB.existe(username))
      return null;

    return this.dbService.buscar(condicionOrganizacionJoinUsuario(username));

  }

  public Organizacion buscarOrganizacionPorNombre(String nombre){

    return this.dbService.buscar(condicionOrganizacionPorNombre(nombre));

  }

  private BusquedaCondicional condicionOrganizacionPorNombre(String nombre) {
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Organizacion> organizacionCriteriaQuery = criteriaBuilder.createQuery(Organizacion.class);

    Root<Organizacion> condicionRaiz = organizacionCriteriaQuery.from(Organizacion.class);

    //condicionRaiz.join("usuario");

    Predicate condicionRazonSocial = criteriaBuilder.equal(condicionRaiz.get("razonSocial"),  nombre );
    //Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("password"), contrasenia);

    //Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

    organizacionCriteriaQuery.where(condicionRazonSocial);

    return new BusquedaCondicional(null, organizacionCriteriaQuery);
  }


  private BusquedaCondicional condicionOrganizacionJoinUsuario(String nombreDeUsuario){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Organizacion> organizacionCriteriaQuery = criteriaBuilder.createQuery(Organizacion.class);

    Root<Organizacion> root = organizacionCriteriaQuery.from(Organizacion.class);

    Join<Organizacion,Usuario> personaUsuarioJoin = root.join("usuario", JoinType.INNER);

    //root.join("persona");

    //Predicate condicionNombreDeUsuario = criteriaBuilder.like(root.get("username"),"%"+ nombreDeUsuario+"%");
    //Predicate condicionContrasenia = criteriaBuilder.equal(root.get("password"), contrasenia);

    //Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

    organizacionCriteriaQuery.where(criteriaBuilder.equal(personaUsuarioJoin.get("username"), nombreDeUsuario));

    return new BusquedaCondicional(null, organizacionCriteriaQuery);
  }

  public void agregarOrganizacion(Organizacion organizacion){
    this.dbService.agregar(organizacion);
  }

  public Organizacion crearOrganizacion(String nombre,ClasificacionOrganizacion _clasificacion,TipoOrganizacion _tipo,Contacto contacto, Usuario user){
    RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

    if(user == null) throw new UsuarioException("El usuario es nulo");

    if(repositorioUsuariosDB.existe(user.getUsername())) throw new OrganizacionException("Ya hay un usuario en la DB");

    if(this.buscarOrganizacionPorNombre(nombre) != null) throw new OrganizacionException("Ya hay una organizacion con el mismo nombre");


    Organizacion organizacionNueva = new Organizacion(nombre, _tipo,  _clasificacion,  contacto);
    organizacionNueva.setUsuario(user);


    this.dbService.agregar(organizacionNueva);

    return organizacionNueva;
  }

  public Organizacion buscarOrganizacion(String razonSocial){
    return (Organizacion) this.dbService.buscar(condicionRazonSocial(razonSocial));
  }

  private BusquedaCondicional condicionRazonSocial(String razonSocial){
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Organizacion> organizacionCriteriaQuery = criteriaBuilder.createQuery(Organizacion.class);

    Root<SolicitudPendiente> root = organizacionCriteriaQuery.from(SolicitudPendiente.class);

    Predicate condicionPorRazonSocial = criteriaBuilder.like(root.get("razonSocial"), "%"+ razonSocial +"%");

    organizacionCriteriaQuery.where(condicionPorRazonSocial);

    return new BusquedaCondicional(null, organizacionCriteriaQuery);
  }
}