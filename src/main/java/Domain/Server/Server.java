package Domain.Server;

import static spark.Spark.*;

public class Server {
  public static void main(String[] args) {
    port(9000);
    //get("/hello", (req, res) -> "Hello World");
    Router.init();
  }
}