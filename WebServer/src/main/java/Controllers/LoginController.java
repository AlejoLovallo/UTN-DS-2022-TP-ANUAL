package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Controller
//@CrossOrigin
public class LoginController {

/*
    Spark.get("/", loginController::inicio, Router.engine);

    Spark.get("/menu_inicio", loginController::inicio, Router.engine);

    //TODO hacer funcion de loginController(menu_login) para mandar el HTML
    Spark.get("/menu_login", loginController::menu_login, Router.engine);*/

  @GetMapping(value = "/")
  public ModelAndView index () {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("Menu_Miembro");
    return modelAndView;
  }


  @GetMapping(value = "/menu_login")
  public ModelAndView menu_login () {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("Menu_Miembro");
    return modelAndView;
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
