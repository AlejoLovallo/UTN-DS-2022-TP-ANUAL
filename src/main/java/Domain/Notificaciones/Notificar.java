package Domain.Notificaciones;

public interface Notificar {
    //////////////////////////////////  VARIABLES
    //////////////////////////////////  CONSTRUCTORES
    //////////////////////////////////  GETTERS

    //////////////////////////////////  SETTERS

    void setAsunto(String asunto);

    void setCuerpo(String cuerpo);

    void setImagen(String imagen);

    //////////////////////////////////  INTERFACE

    public Mail toMail();

    public String toSMS();

    public String toWhatsApp();

}
