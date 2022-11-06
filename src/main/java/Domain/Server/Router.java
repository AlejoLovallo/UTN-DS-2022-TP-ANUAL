package Domain.Server;

import Domain.Spark.BooleanHelper;
import spark.Spark;

public class Router {

  private static void initEngine() {
  }

  public static void init() {
    Router.initEngine();
    Spark.staticFileLocation("/public");
    Router.configure();
  }

  private static void configure(){

   /*
    LoginController loginController = new LoginController();
    Spark.before("/", AuthMiddleware::verificarSesion);

    Spark.get("/", loginController::inicio, Router.engine);

    Spark.get("/menu_inicio", loginController::inicio, Router.engine);

    Spark.get("/menu_login", loginController::menu_login, Router.engine);
    */

  }
}