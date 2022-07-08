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

    System.out.println("--------------------------------------------------------------------");
    System.out.println("EJECUTANDO JOB " + jobDetail.getKey());
    System.out.println("Ejecucion Num. " + count);
    System.out.println("Inicio: " + jobContext.getFireTime());
    System.out.println("Info: " + jobDetail.getJobDataMap().getString("ejemplo"));
    System.out.println("Fin: " + jobContext.getJobRunTime());
    System.out.println("Proxima ejecucion: " + jobContext.getNextFireTime());
    System.out.println("--------------------------------------------------------------------");

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
