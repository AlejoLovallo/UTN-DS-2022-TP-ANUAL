package Domain.Server;

import Domain.Controllers.LoginController;
import Domain.Middleware.AuthMiddleware;
import Domain.Spark.BooleanHelper;
import Domain.Spark.HandlebarsTemplateEngineBuilder;
import spark.ResponseTransformer;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.Spark;

public class Router {

  private static HandlebarsTemplateEngine engine;
  private static void initEngine() {
    Router.engine = HandlebarsTemplateEngineBuilder
        .create()
        .withDefaultHelpers()
        .withHelper("isTrue", BooleanHelper.isTrue)
        .build();
  }

  public static void init() {
    Router.initEngine();
    Spark.staticFileLocation("/public");
    Router.configure();
  }

  private static void configure(){


    LoginController loginController = new LoginController();
    Spark.before("/", AuthMiddleware::verificarSesion);
/*
    Spark.get("/", loginController::inicio, Router.engine);

    Spark.get("/menu_inicio", loginController::inicio, Router.engine);

    //TODO hacer funcion de loginController(menu_login) para mandar el HTML
    Spark.get("/menu_login", loginController::menu_login, Router.engine);

*/
    Spark.post("/menu_login", loginController::loguear);

    //TODO este post recibe los datos del json

  }
}