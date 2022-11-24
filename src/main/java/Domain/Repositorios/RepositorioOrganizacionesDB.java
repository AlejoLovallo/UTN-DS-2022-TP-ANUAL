package Domain.Repositorios;

import Domain.Notificaciones.MailSender;
import Domain.Notificaciones.Notificar;
import Domain.Notificaciones.NotificarGuiaDeRecomendaciones;
import Domain.Organizacion.Organizacion;
import Domain.Usuarios.Usuario;

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
}
