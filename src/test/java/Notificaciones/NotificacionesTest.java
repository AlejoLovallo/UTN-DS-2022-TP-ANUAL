package Notificaciones;

import Domain.Notificaciones.*;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.RepositorioOrganizaciones;
import Domain.Usuarios.Contacto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NotificacionesTest {
    private static Organizacion organizacion1;
    private static Organizacion organizacion2;
    List<Organizacion> organizaciones = new ArrayList<>();
    private static MailSender mockNotificadorMail;
    private static Notificacion notificacion;


    @BeforeEach
    public void initialize() {
       mockNotificadorMail = Mockito.mock(MailSender.class);
        organizacion1 = getOrganizacion1();
        organizacion2 = getOrganizacion2();

        organizaciones.add(organizacion1);
        organizaciones.add(organizacion2);
        notificacion = new NotificacionEstandar();
    }

    @AfterEach
    public void clean(){

    }


    @Test
    void enviarNotificacionEstandarAUnaOrganizacion() {
        String asunto = "Un Asunto Estandar";
        String cuerpo = "Un Cuerpo Estandar";
        MailSender mailSender = new MailSender();

        notificacion.setAsunto(asunto);
        notificacion.setCuerpo(cuerpo);
        notificacion.setImagen(null);

        mailSender.enviarNotificacion(organizacion1.getContacto(), notificacion);
    }

    @Test
    void enviarNotificacionEstandarAUnaOrganizacionConImagen() {
        String asunto = "Un Asunto Con Imagen FINAL";
        String cuerpo = "Un Cuerpo Con Imagen";
        String imagen = "docs\\imagenes\\LogoUTN.jpg";

        MailSender mailSender = new MailSender();

        notificacion.setAsunto(asunto);
        notificacion.setCuerpo(cuerpo);
        notificacion.setImagen(imagen);

        mailSender.enviarNotificacion(organizacion1.getContacto(), notificacion);
    }

    @Test
    void enviarNotificacionEstandarAUnaOrganizacionConImagenAVariosDestinatarios() {
        String asunto = "Un Asunto para varios destinatarios";
        String cuerpo = "Un Cuerpo para varios destinatarios";
        String imagen = "docs\\imagenes\\LogoUTN.jpg";

        MailSender mailSender = new MailSender();

        notificacion.setAsunto(asunto);
        notificacion.setCuerpo(cuerpo);
        notificacion.setImagen(imagen);

        organizaciones.forEach(organizacion ->  mailSender.enviarNotificacion(organizacion.getContacto(), notificacion));
    }

    @Test
    void enviarNotificacionDeRecomendacionesAOrganizacones() {
        RepositorioOrganizaciones repo = RepositorioOrganizaciones.GetInstance();

        repo.agregarOrganizacion(organizacion1);
        repo.agregarOrganizacion(organizacion2);

        repo.enviarMailDeRecomendaciones();
    }


    @Test
    void lanzaErrorCuandoIntentoNotificarAUnMailInvalido() {
        Contacto contactoInvalido = new Contacto("contacto", "invalido", 4242, "un-mail-invalido@hotmail.com");
        doThrow(new RuntimeException()).when(mockNotificadorMail).enviarNotificacion(any(), any());
        assertThrows(RuntimeException.class, () -> mockNotificadorMail.enviarNotificacion(contactoInvalido, any()));
    }


    @Test
    void enviarNotificacionEstandarDesdeMailPersonalizado() {
        String asunto = "Un Asunto Estandar";
        String cuerpo = "Un Cuerpo Estandar";
        String mail = "grupo7dds2022@gmail.com";
        String password = "jczcefspehwrnvqz";

        MailSender mailSender = new MailSender(mail, password);

        notificacion.setAsunto(asunto);
        notificacion.setCuerpo(cuerpo);
        notificacion.setImagen(null);

        mailSender.enviarNotificacion(organizacion1.getContacto(), notificacion);
    }




    /*--------------------Metodos usados para crear objetos del test------------------------*/

    public static Organizacion getOrganizacion1() {
        Contacto contacto = new Contacto("Juan", "Perez", 123456789, "Oteroerik52@gmail.com");
        return new Organizacion(null, null, null, contacto);
    }

    public static Organizacion getOrganizacion2() {
        Contacto contacto = new Contacto("Juan", "Perez", 123456789, "Oteroerik15@gmail.com");
        return new Organizacion(null, null, null, contacto);
    }

}

