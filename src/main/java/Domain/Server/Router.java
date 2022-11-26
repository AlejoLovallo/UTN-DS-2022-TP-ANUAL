package Domain.Server;

import Domain.Controllers.LoginController;
import Domain.Controllers.MiembroController;
import Domain.Controllers.OrganizacionController;
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

    /*** CONTROLLERS ***/
    LoginController loginController = new LoginController();
    OrganizacionController organizacionController = new OrganizacionController();
    MiembroController miembroController = new MiembroController();

    /*** ADMIN ROUTERS ***/

    /*** MIEMBROS ROUTES ***/
    Spark.get("/menu_registrar_trayecto", miembroController::menuRegistrarTrayectos);
    Spark.get("/miembro/:username/:organizacion", miembroController::getMiembro);
    Spark.post("/enviar_solicitud", miembroController::menuEnviarSolicitud);
    Spark.post("/menu_registrar_trayecto", miembroController::agregarTrayecto);
    Spark.post("/menu_calcular_hc", miembroController::respuestaCalcularHC);

    /*** ORGANIZACIONES ROUTES ***/
    Spark.get("/organizacion/:nombre",organizacionController::getOrganizacion);
    Spark.post("/organizacion",organizacionController::crearOrganizacion);
    Spark.put("/organizacion/:nombre",organizacionController::modificarOrganizacion);



  }
}