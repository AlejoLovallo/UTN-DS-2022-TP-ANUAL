package Domain.Server;

import Domain.Controllers.LoginController;
import Domain.Middleware.AuthMiddleware;
import Domain.Spark.BooleanHelper;
import Domain.Spark.HandlebarsTemplateEngineBuilder;
import spark.template.handlebars.HandlebarsTemplateEngine;
import Domain.Controllers.MiembroController;
import Domain.Controllers.OrganizacionController;
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
    Spark.post("/menu_login", loginController::loguear);

    /*** ADMIN ROUTERS ***/

    /*** MIEMBROS ROUTES ***/
    Spark.get("/visualizar_trayectos", miembroController::visualizarTrayectos);
    Spark.get("/miembro/:username/:organizacion", miembroController::getMiembro);
    Spark.post("/enviar_solicitud", miembroController::menuEnviarSolicitud);
    Spark.post("/agregar_trayecto", miembroController::agregarTrayecto);
    Spark.post("/calcularHC", miembroController::respuestaCalcularHC);

    /*** ORGANIZACIONES ROUTES ***/
    Spark.get("/organizacion/:nombre",organizacionController::getOrganizacion);
    Spark.post("/organizacion",organizacionController::crearOrganizacion);
    Spark.post("/organizacion/aceptar_miembro", organizacionController::respuestaAceptarMiembro);
    Spark.post("/organizacion/calcular_hc", organizacionController::respuestaCalcularHC);
    Spark.put("/organizacion/:nombre",organizacionController::modificarOrganizacion);


    Spark.get("/recomendaciones", organizacionController::listarRecomendaciones, Router.engine);
    Spark.get("/reportes", organizacionController::mostrarReportes, Router.engine);
  }
}