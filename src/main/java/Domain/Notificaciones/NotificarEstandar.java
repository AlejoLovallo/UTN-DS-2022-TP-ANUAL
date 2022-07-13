package Domain.Notificaciones;


public class NotificarEstandar implements Notificar {
    //////////////////////////////////  VARIABLES
    String asunto = null;
    String cuerpo = null;
    String imagen = null;

    //////////////////////////////////  CONSTRUCTORES
    public NotificarEstandar(){}

    //////////////////////////////////  GETTERS

    //////////////////////////////////  SETTERS
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    //////////////////////////////////  INTERFACE
    public Mail toMail(){
        return new Mail(asunto, cuerpo, imagen);
    }

    public String toSMS(){
        return cuerpo;
    }

    public String toWhatsApp(){
        return cuerpo;
    }

}
