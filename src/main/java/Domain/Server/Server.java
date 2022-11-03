package Domain.Server;

import static spark.Spark.*;

public class Server {
  public static void main(String[] args) {
    port(9000);
    Router.init();
    // get("/hello", (req, res) -> "Hello World");
  }
}