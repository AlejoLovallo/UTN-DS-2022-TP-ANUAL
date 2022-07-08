package Domain.Notificaciones;

import Domain.Usuarios.Contacto;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class MailSender extends MedioDeNotificacion{
    //////////////////////////////////  VARIABLES
    String email = "grupo7dds2022@gmail.com";
    String password = "jczcefspehwrnvqz";

    //////////////////////////////////  CONSTRUCTORES

    public MailSender(){}

    public MailSender(String email, String password){
        this.email = email;
        this.password = password;
    }

    //////////////////////////////////  GETTERS

    //////////////////////////////////  SETTERS

    //////////////////////////////////  INTERFACE
    public void enviarNotificacion(Contacto contacto, Notificacion notificacion) {
        Session conexion = configurarConexionGmail();
        try {

            MimeMessage message = new MimeMessage(conexion);
            message.setFrom(new InternetAddress(this.email));
            agregarDestinatario(message, contacto.getEmail());

            message.setSubject(notificacion.toMail().getAsunto());
            message.setSentDate(new Date());

            if(notificacion.toMail().getImagen() != null){
                BodyPart texto = new MimeBodyPart();
                texto.setContent(notificacion.toMail().getCuerpo(), "text/html");

                BodyPart imagen = new MimeBodyPart();
                imagen.setDataHandler(new DataHandler(new FileDataSource(notificacion.toMail().getImagen())));
                imagen.setFileName("Imagen.jpg");

                MimeMultipart partes = new MimeMultipart();
                partes.addBodyPart(texto);
                partes.addBodyPart(imagen);

                message.setContent(partes);
            }else {message.setText(notificacion.toMail().getCuerpo());}


            Transport t = conexion.getTransport("smtp");
            t.connect(this.email, this.password);
            t.sendMessage(message, message.getAllRecipients());
            t.close();

        } catch (Exception e) {
            System.out.println("Hubo un problema en el proceso de notificacioon: " +e.getMessage());
            e.printStackTrace();
        }
    }

    private Session configurarConexionGmail() {
        // Propiedades de la conexión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", this.email);
        props.setProperty("mail.smtp.auth", "true");

        return Session.getDefaultInstance(props);
    }

    private void agregarDestinatario(MimeMessage message, String email) {
        try {
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(email));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



/* ENVIAR UN MAIL SIN USAR LAS NOTIFICACIONES, FUNCIONA,
PERO AGREGUE LAS NOTIFICACIONES ASI SE PUEDEN USAR PARA ENVIAR MAILS, SMS Y WHATSAPP

    public void enviarMail(Contacto contacto, Mail mail) {
        Session conexion = configurarConexionGmail();
        try {
            MimeMessage message = new MimeMessage(conexion);
            message.setFrom(new InternetAddress(this.email));
            agregarDestinatario(message, contacto.getEmail());//////////////////////
            //contactos.forEach(contacto -> agregarDestinatario(message, contacto.getEmail()));
            message.setSubject(mail.getAsunto());
            message.setSentDate(new Date());
            message.setText(mail.getCuerpo());

            Transport t = conexion.getTransport("smtp");
            t.connect(this.email, this.pass);
            t.sendMessage(message, message.getAllRecipients());
            t.close();

        } catch (Exception e) {
            System.out.println("Hubo un problema en el proceso de notificación: " +e.getMessage());
            e.printStackTrace();
        }
    }*/
}
