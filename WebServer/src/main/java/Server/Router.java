package Server;

import Controllers.LoginController;
import Controllers.MiembroController;
import Controllers.OrganizacionController;
import Middleware.AuthMiddleware;
import Spark.BooleanHelper;
import Spark.HandlebarsTemplateEngineBuilder;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

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
    Spark.staticFileLocation("/static");
    Router.configure();
  }

  private static void configure(){


    Spark.before("/", AuthMiddleware::verificarSesion);
/*
    Spark.get("/", loginController::inicio, Router.engine);

    Spark.get("/menu_inicio", loginController::inicio, Router.engine);

    //TODO hacer funcion de loginController(menu_login) para mandar el HTML
    Spark.get("/menu_login", loginController::menu_login, Router.engine);

*/

    //TODO este post recibe los datos del json

    /*** CONTROLLERS ***/
    LoginController loginController = new LoginController();
    OrganizacionController organizacionController = new OrganizacionController();
    MiembroController miembroController = new MiembroController();

    /*** LOGIN ROUTES ***/
    Spark.get("/", loginController::menu_login,Router.engine);
    Spark.get("/menu_login", loginController::menu_login,Router.engine);

    /*** ADMIN ROUTERS ***/

    /*** MIEMBROS ROUTES ***/
    Spark.get("/menu_registrar_trayecto", miembroController::menuRegistrarTrayectos,Router.engine);
    Spark.get("/miembro/:username/:organizacion", miembroController::getMiembro,Router.engine);

    /*** ORGANIZACIONES ROUTES ***/
    Spark.get("/organizacion/:nombre",organizacionController::getOrganizacion,Router.engine);


    //Cliente Liviano
    //Spark.get("/recomendaciones", organizacionController::listarRecomendaciones, Router.engine);
    //Spark.get("/reportes", organizacionController::mostrarReportes, Router.engine);
  }
}