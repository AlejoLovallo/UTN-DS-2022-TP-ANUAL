package Domain.Organizacion;
import Domain.Notificaciones.MailSender;
import Domain.Notificaciones.Notificar;
import Domain.Notificaciones.NotificarGuiaDeRecomendaciones;
import java.util.ArrayList;

/**
 * Singleton pattern
 */

public class RepositorioOrganizaciones {
    //////////////////////////////////  VARIABLES
  private static RepositorioOrganizaciones instance = null;
  private ArrayList<Organizacion> organizaciones;

    //////////////////////////////////  CONSTRUCTORES
  private RepositorioOrganizaciones(){
      this.organizaciones = new ArrayList<>();
  }

  public static RepositorioOrganizaciones GetInstance(){
      if(instance == null){
        instance = new RepositorioOrganizaciones();
      }
      return instance;
  }
    //////////////////////////////////  GETTERS
  public ArrayList<Organizacion> getOrganizaciones() {
    return organizaciones;
}

    //////////////////////////////////  SETTERS

    //////////////////////////////////  INTERFACE

  //TODO REVISAR QUE NO SE PUEDE MODIFIAR LA NOTIFICACION
  public void enviarMailDeRecomendaciones() {
      if(this.organizaciones != null){
          Notificar notificar = new NotificarGuiaDeRecomendaciones();
          String mail = "grupo7dds2022@gmail.com";
          String password = "jczcefspehwrnvqz";
          MailSender mailSender = new MailSender(mail,password);
          this.organizaciones.forEach(organizacion -> mailSender.enviarNotificacion(organizacion.getContacto(), notificar));
      }
   }

  public void agregarOrganizacion(Organizacion organizacion){
      this.organizaciones.add(organizacion);
  }
}
