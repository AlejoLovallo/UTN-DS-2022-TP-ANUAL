package Domain.Notificaciones;

public interface Notificacion {
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
