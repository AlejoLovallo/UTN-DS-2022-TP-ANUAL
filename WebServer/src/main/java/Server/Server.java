package Server;

import static spark.Spark.*;

public class Server {
  public static void main(String[] args) {
    port(8080);
    Router.init();
    // get("/hello", (req, res) -> "Hello World");
  }
}