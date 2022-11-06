package Domain.Repositorios;

import Domain.Miembro.Persona;
import Domain.Notificaciones.MailSender;
import Domain.Notificaciones.Notificar;
import Domain.Notificaciones.NotificarGuiaDeRecomendaciones;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Organizacion.SolicitudPendiente;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

  public void agregarOrganizacion(Organizacion organizacion){
    this.dbService.agregar(organizacion);
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
