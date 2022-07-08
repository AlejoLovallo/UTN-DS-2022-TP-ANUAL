package Domain.Cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledNotificador {

  //Todos los Lunes a las 7:00 AM
  @Scheduled(cron="0 7 * * 1")
  public void ejecutarNotificador(){
    //TODO Cambiar por el envio de Mail
    long now = System.currentTimeMillis() / 1000;
    System.out.println("Aviso de Notificador a las - " + now);
  }
}
