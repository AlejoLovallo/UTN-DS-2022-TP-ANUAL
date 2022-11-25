
package CalculadorHC.Cron;

import Domain.Cron.SchedulerCron;
import Domain.Notificaciones.MailSender;
import Domain.Notificaciones.Notificar;
import Domain.Notificaciones.NotificarEstandar;
import Domain.Organizacion.Organizacion;
import Domain.Repositorios.RepositorioOrganizacionesDB;
import Domain.Usuarios.Contacto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    RepositorioOrganizacionesDB repositorioOrganizaciones = new RepositorioOrganizacionesDB();

    repositorioOrganizaciones.agregarOrganizacion(organizacion1);
    repositorioOrganizaciones.agregarOrganizacion(organizacion2);
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

