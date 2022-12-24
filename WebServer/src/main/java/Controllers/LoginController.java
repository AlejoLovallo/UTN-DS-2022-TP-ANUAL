package Controllers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginController {

/*
    Spark.get("/", loginController::inicio, Router.engine);

    Spark.get("/menu_inicio", loginController::inicio, Router.engine);

    //TODO hacer funcion de loginController(menu_login) para mandar el HTML
    Spark.get("/menu_login", loginController::menu_login, Router.engine);*/

  public ModelAndView index (Request request, Response response) {
    Map<String, Object> parametros = new HashMap<>();
    return new ModelAndView(parametros,"index.html");
  }


  public ModelAndView menu_login (Request request, Response response) {
    Map<String, Object> parametros = new HashMap<>();
    return new ModelAndView(parametros,"index.html");
  }
/*
  @RequestMapping(value = "/error", method = RequestMethod.GET)
  public ModelAndView error () {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    return modelAndView;
  }*/


  public String htmlToString(String nombreArchivo)
  {
    //TODO: revisar si funciona
    File archivoHTML = new File("src/main/resources/templates/"+nombreArchivo);

    Scanner sc = null;
    try {
      sc = new Scanner(archivoHTML);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String body = "";


    while (sc.hasNextLine())
      body.concat(sc.nextLine());


    return body;
  }


}
