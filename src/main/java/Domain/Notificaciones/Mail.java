package Domain.Notificaciones;

public class Mail {
    //////////////////////////////////  VARIABLES

    String asunto = null;
    String cuerpo = null;
    String imagen = null;

    //////////////////////////////////  CONSTRUCTORES


    public Mail (String asunto, String cuerpo){
        this.asunto = asunto == null ? "" : asunto;
        this.cuerpo = cuerpo == null ? "" : cuerpo;
    }

    public Mail (String asunto, String cuerpo, String imagen){
        this.asunto = asunto == null ? "" : asunto;
        this.cuerpo = cuerpo == null ? "" : cuerpo;
        this.imagen = imagen /*== null ? "" : imagen*/;
    }

    //////////////////////////////////  GETTERS

    public String getAsunto(){
        return this.asunto;
    }

    public String getCuerpo(){
        return this.cuerpo;
    }

    public String getImagen(){
        return this.imagen;
    }

    //////////////////////////////////  SETTERS
    //////////////////////////////////  INTERFACE
}
