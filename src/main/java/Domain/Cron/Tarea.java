package Domain.Cron;

import Domain.Organizacion.RepositorioOrganizaciones;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.CountDownLatch;

public class Tarea implements Job {
  private static int count = 0;

  @Override
  public void execute(JobExecutionContext jobContext) throws JobExecutionException {

    JobDetail jobDetail = jobContext.getJobDetail();
    count++;

    Logger.getInstance().loggearCron("--------------------------------------------------------------------");
    Logger.getInstance().loggearCron("EJECUTANDO JOB " + jobDetail.getKey());
    Logger.getInstance().loggearCron("Ejecucion Num. " + count);
    Logger.getInstance().loggearCron("Inicio: " + jobContext.getFireTime());
    Logger.getInstance().loggearCron("Info: " + jobDetail.getJobDataMap().getString("ejemplo"));
    Logger.getInstance().loggearCron("Fin: " + jobContext.getJobRunTime());
    Logger.getInstance().loggearCron("Proxima ejecucion: " + jobContext.getNextFireTime());
    Logger.getInstance().loggearCron("--------------------------------------------------------------------");

    //TODO agregar el envio de mail
    RepositorioOrganizaciones.GetInstance().enviarMailDeRecomendaciones();

    //aca uso el jobdatamap con mis objetos de negocio
    CountDownLatch contadorSincronico = (CountDownLatch) jobDetail.getJobDataMap().get("contadorSincronico");
    contadorSincronico.countDown();
    if (count == 2) {
      throw new RuntimeException("RuntimeException!");
    }
    if (count == 4) {
      throw new JobExecutionException("JobExecutionException!");
    }

  }
}
