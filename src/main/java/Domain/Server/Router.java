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

    //TODO hacer funcion de loginController(menu_login) para mandar el HTML
    Spark.get("/menu_login", loginController::menu_login, Router.engine);

    //TODO este post recibe los datos del json
    Spark.post("/menu_login", loginController::loguear, Router.engine);
    */

  }
}