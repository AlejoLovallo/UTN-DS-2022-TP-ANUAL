package Domain.Notificaciones;


public class NotificarGuiaDeRecomendaciones implements Notificar {
    //////////////////////////////////  VARIABLES
    String asunto = "Recomendaciones";
    String cuerpo = "https://www.google.com/";
    String imagen = null;

    //////////////////////////////////////  CONSTRUCTORES
    public NotificarGuiaDeRecomendaciones(){}

    //////////////////////////////////  GETTERS

    //////////////////////////////////  SETTERS
    @Override
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    @Override
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    @Override
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    //////////////////////////////////  INTERFACE

    public Mail toMail() {
        return new Mail(asunto, cuerpo, null);
    }

    public String toSMS(){
        return "Guia De Recomendaciones";
    }

    public String toWhatsApp(){
        return "Guia De Recomendaciones";
    }


}
