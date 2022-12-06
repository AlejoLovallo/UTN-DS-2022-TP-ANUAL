package Controllers;


import java.io.File;

public class MiembroController {

    public String htmlToString(String nombreArchivo)
    {
        //TODO: revisar si funciona
        File archivoHTML = new File("src/main/resources/templates/"+nombreArchivo);
        return archivoHTML.toString();
    }


}
