package Domain.Server;

import Domain.Controllers.LoginController;
import Domain.Middleware.AuthMiddleware;
import Domain.Spark.BooleanHelper;
import Domain.Spark.HandlebarsTemplateEngineBuilder;
import spark.ResponseTransformer;
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


    Spark.options("/*",
        (request, response) -> {

          String accessControlRequestHeaders = request
              .headers("Access-Control-Request-Headers");
          if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers",
                accessControlRequestHeaders);
          }

          String accessControlRequestMethod = request
              .headers("Access-Control-Request-Method");
          if (accessControlRequestMethod != null) {
            response.header("Access-Control-Allow-Methods",
                accessControlRequestMethod);
          }

          return "OK";
        });

    Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    Spark.before("/", AuthMiddleware::verificarSesion);
    Spark.before("/menu_login", AuthMiddleware::verificarSesion);

    //TODO este post recibe los datos del json

    /*** CONTROLLERS ***/
    LoginController loginController = new LoginController();
    OrganizacionController organizacionController = new OrganizacionController();
    MiembroController miembroController = new MiembroController();

    /*** LOGIN ROUTES ***/
    Spark.get("/menu_login", loginController::loguearHtml,Router.engine);
    Spark.get("/", loginController::loguearHtml,Router.engine);
    Spark.post("/menu_login", loginController::loguear);
    Spark.post("/registrar_org", organizacionController::crearOrganizacion);
    Spark.post("/registrar_persona", miembroController::crearPersona);


    /*** ADMIN ROUTERS ***/

    /*** MIEMBROS ROUTES ***/
    Spark.get("/menu_miembro", miembroController::menu_miembro, Router.engine);
    Spark.get("/registrarTrayectoHTML", miembroController::registrarTrayectoHTML,Router.engine);
    Spark.get("/solicitarVinculacionHTML", miembroController::solicitarVinculacionHTML, Router.engine);
    Spark.get("/visualizar_trayectos", miembroController::visualizarTrayectos);
    Spark.get("/calcularHTMLmiembro", miembroController::calcularHTMLmiembro, Router.engine);
    Spark.get("/miembro/:username/:organizacion", miembroController::getMiembro);
    Spark.post("/enviar_solicitud", miembroController::menuEnviarSolicitud);
    Spark.post("/agregar_trayecto", miembroController::agregarTrayecto);
    Spark.post("/miembro/calcularHC", miembroController::respuestaCalcularHC);
    

    /*** ORGANIZACIONES ROUTES ***/
    Spark.get("/menu_organizacion",organizacionController::menuOrganizacion,Router.engine);
    Spark.get("/organizacion/:nombre",organizacionController::getOrganizacion);
    Spark.get("/sol_miembros", organizacionController::solicitudesHTML, Router.engine);
    Spark.get("/calcularHTMLorg", organizacionController::calcularHCorg, Router.engine);
    Spark.get("/solicitudes_miembro",organizacionController::respuestaListaMiembros);
    Spark.get("/registrarMedicionesHTML", organizacionController::registrarMedicionesHTML,Router.engine);
    Spark.get("/generarReporteHTML", organizacionController::generarReporteHTML, Router.engine);
    Spark.post("/organizacion",organizacionController::crearOrganizacion);
    Spark.post("/aceptar_miembro", organizacionController::respuestaAceptarMiembro);
    Spark.post("/rechazar_miembro", organizacionController::respuestaRechazarMiembro);
    Spark.post("/organizacion/calcularHC", organizacionController::respuestaCalcularHC);
    Spark.post("/organizacion/cargar_mediciones", organizacionController::cargarMediciones);
    Spark.post("/generar_reporte", organizacionController::respuestaGenerarReporte);
    Spark.put("/organizacion/:nombre",organizacionController::modificarOrganizacion);

    Spark.get("/organizacionesGet",organizacionController::getAllOrganizaciones);


    Spark.get("/recomendaciones", organizacionController::listarRecomendaciones, Router.engine);
    Spark.get("/reportes", organizacionController::mostrarReportes, Router.engine);
    Spark.get("/pedidoMenuCalcularHC", organizacionController::pedidoMenuCalcularHC);
    //Spark.get("/cerrar_sesion",loginController::logout);

  }
}