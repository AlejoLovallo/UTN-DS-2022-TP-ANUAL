
package Cron;

import Domain.Cron.SchedulerCron;
import Domain.Notificaciones.MailSender;
import Domain.Notificaciones.Notificar;
import Domain.Notificaciones.NotificarEstandar;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.RepositorioOrganizaciones;
import Domain.Usuarios.Contacto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;


public class CronTest {
  private static Organizacion organizacion1;
  private static Organizacion organizacion2;
  private static MailSender mockNotificadorMail;
  private static Notificar notificacion;


  @BeforeEach
  public void initialize() {
    organizacion1 = getOrganizacion1();
    organizacion2 = getOrganizacion2();

    RepositorioOrganizaciones.GetInstance().agregarOrganizacion(organizacion1);
    RepositorioOrganizaciones.GetInstance().agregarOrganizacion(organizacion2);
    notificacion = new NotificarEstandar();
  }

  @AfterEach
  public void clean(){

  }

  @Test
  void cronEnvioMail() {
    SchedulerCron schedulerCron = new SchedulerCron();
    schedulerCron.comenzar();
  }



  /*--------------------Metodos usados para crear objetos del test------------------------*/

  public static Organizacion getOrganizacion1() {
    Contacto contacto = new Contacto("Tomas", "Casadoumecq", 123456789, "tomas.casa123@gmail.com");
    return new Organizacion(null, null, null, contacto);
  }

  public static Organizacion getOrganizacion2() {
    Contacto contacto = new Contacto("Tomas", "Casadoumecq", 123456789, "tomas.casadoumecq@gmail.com");
    return new Organizacion(null, null, null, contacto);
  }

}

