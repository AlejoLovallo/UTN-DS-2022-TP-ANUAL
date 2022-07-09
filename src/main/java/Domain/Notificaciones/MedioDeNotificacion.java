package Domain.Notificaciones;
import Domain.Usuarios.Contacto;

public abstract class MedioDeNotificacion {

    public abstract void enviarNotificacion(Contacto contacto, Notificar notificar);
}
