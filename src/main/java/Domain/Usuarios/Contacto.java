package Domain.Usuarios;
import org.apache.commons.lang3.Validate;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Contacto {
    //////////////////////////////////  VARIABLES
    private String nombre;
    private String apellido;
    private Integer telefono;
    private String email;

    //////////////////////////////////  CONSTRUCTORES

    public Contacto(String nombre, String apellido, Integer telefono, String email) {
        Validate.notNull(nombre);
        Validate.notNull(apellido);
        Validate.notNull(telefono);
        Validate.notNull(email);

        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        }
        catch (AddressException ex) {
            throw new RuntimeException("Debe ingresar una direccion de email valida");
        }

        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }


    //////////////////////////////////  GETTERS


    public String getEmail() {
        return this.email;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getTelefono() {
        return this.telefono.toString();
    }


    //////////////////////////////////  SETTERS

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //////////////////////////////////  INTERFACE
}
