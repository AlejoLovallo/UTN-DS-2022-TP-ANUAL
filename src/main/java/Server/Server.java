package Server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
  public static void main(String[] args) {
    Spark.port(9000);
    DebugScreen.enableDebugScreen();
  }

}