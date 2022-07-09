package Domain.Cron;

import Domain.Usuarios.Excepciones.ArchivoInaccesibleException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Logger {

  private static Logger instance = null;

  private Logger(){  }

  public static Logger getInstance(){
    if(instance == null){
      instance = new Logger();
    }
    return instance;
  }

  public void loggearCron(String log){
    try {
      String rutaLogger = "src/main/java/cron.log";
      BufferedWriter out = new BufferedWriter(new FileWriter(rutaLogger, true));
      out.write(log);
      out.close();
    }
    catch (IOException e) {
      throw new ArchivoInaccesibleException();
    }
  }
}
