package Domain.Usuarios;
import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.Organizacion.Organizacion;
import org.apache.commons.lang3.Validate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.*;

@Entity
@Table(name="contacto")
public class Contacto {
    //////////////////////////////////  VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_contacto;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private Integer telefono;
    @Column(name = "mail")
    private String email;

    /*
    @OneToOne(cascade = CascadeType.ALL)
    //@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_organizacion",referencedColumnName = "id_organizacion")
    private Organizacion organizacion;
*/


    //////////////////////////////////  CONSTRUCTORES
    private Contacto(){

    }

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
        //update();
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
        //update();
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
        //update();
    }

    public void setEmail(String email) {
        this.email = email;
        //update();
    }

    @Override
    public String toString() {
        return "Contacto{" +
            "id_contacto=" + id_contacto +
            ", nombre='" + nombre + '\'' +
            ", apellido='" + apellido + '\'' +
            ", telefono=" + telefono +
            ", email='" + email + '\'' +
            '}';
    }

    /*
    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
        //update();
    }*/

    //////////////////////////////////  INTERFACE
/*
    public void update(){
        EntityManagerHelper.tranUpdate(this);
    }

    public static Contacto get(int contactoID) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();
        Contacto contacto = em.find(Contacto.class, contactoID);
        em.detach(contacto);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
        return contacto;
    }

    public void insert(){
        EntityManagerHelper.tranPersist(this);
    }*/
}
