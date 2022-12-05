package Controllers;

import spark.Request;
import spark.Response;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LoginController {

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
/*
  //GET
  public ModelAndView menu_login(Request request, Response response) {

    HashMap<String, Object> params = new HashMap<>();

    return new ModelAndView(params,"index.html");
  }
  */

  //GET
  //TODO revisar esto
  public Object menu_login(Request request, Response response)
  {

    response.type("text/html");

    response.body(htmlToString("index.html"));
    return response;
  }

}
