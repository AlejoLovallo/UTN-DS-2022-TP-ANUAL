package Domain.Cron;

import org.quartz.*;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class SchedulerCron {
  private final CountDownLatch contadorSincronico = new CountDownLatch(4);


  public void comenzar()  {

    // Creacion del scheduler
    SchedulerFactory schedFactory = new org.quartz.impl.StdSchedulerFactory();
    Scheduler schedulerCron;
    try {
      schedulerCron = schedFactory.getScheduler();

      // registro de un listener propio
      schedulerCron.getListenerManager().addSchedulerListener(new LogSchedulerListenerImpl(schedulerCron));

      // Construccion de JobDetail
      JobBuilder jobBuilder = JobBuilder.newJob(CronEnvioMail.class);
      JobDataMap data = new JobDataMap();
      data.put("contadorSincronico", contadorSincronico);
      JobDetail jobDetail = jobBuilder
          .withIdentity("unJob")
          .usingJobData(data)
          .usingJobData("ejemplo", "enviando mail")
          .build();


      System.out.println("Hora actual: " + new Date());

      // Construccion de Trigger

      String cronCada1Hora = "0 0 0/1 1/1 * ? *";

      String cron5Seg = "0/5 * * ? * * *";

      String cron1Seg = "* * * ? * * *";

      Trigger trigger = TriggerBuilder.newTrigger()
          .withIdentity("unTrigger")
          .startAt(new Date())
          .withSchedule(CronScheduleBuilder.cronSchedule(cronCada1Hora))
          .build();

      // Asignacion del job y el trigger a la inst de scheduler
      schedulerCron.scheduleJob(jobDetail, trigger);
      schedulerCron.start();

      // Para que el proceso principal espere a los calendarizados.
      // Porque en Java cuando el hilo principal muere, todos los sub-hilos también.
      contadorSincronico.await(); // esperando fin de las ejecuciones
      schedulerCron.shutdown();

      Logger.getInstance().loggearCron("----------------------FIN DE CRON----------------------");

    } catch (SchedulerException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
