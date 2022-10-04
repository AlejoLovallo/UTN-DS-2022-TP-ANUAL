package Domain.Repositorios;

import Domain.Notificaciones.MailSender;
import Domain.Notificaciones.Notificar;
import Domain.Notificaciones.NotificarGuiaDeRecomendaciones;
import Domain.Organizacion.Organizacion;

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
}
