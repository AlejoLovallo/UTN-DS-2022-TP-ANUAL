package Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "Controllers")
public class Server {
  public static void main(String[] args) {
    SpringApplication.run(Server.class,args);
  }
}