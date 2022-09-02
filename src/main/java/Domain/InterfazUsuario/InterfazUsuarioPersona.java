package Domain.InterfazUsuario;

import Domain.Miembro.Persona;
import Domain.Miembro.RepositorioPersonas;
import Domain.Miembro.TipoDocumento;
import Domain.Usuarios.Contacto;
import Domain.Notificaciones.MedioDeNotificacion;
import Domain.Usuarios.Excepciones.UsuarioException;
import Domain.Usuarios.RepositorioUsuarios;
import Domain.Usuarios.Usuario;

import java.util.List;


public class InterfazUsuarioPersona {
    private Usuario usuario;
    private Persona persona;

    public Usuario getUsuario() {
        return usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void ingresarUsuario(String username, String contra){
        try{
            this.usuario = RepositorioUsuarios.getInstance().validarLogueoUsuario(username, contra);
            this.persona = RepositorioPersonas.getInstance().buscarPersonaPorUsuario(this.usuario);
            devolverUsuarioCorrecto();
        }
        catch (UsuarioException e){
            devolverUsuarioIncorrecto();
        }
    }

    public void crearUsuarioPersona(String username, String mail, String contra, String nombre, String apellido, TipoDocumento tipoDocumento, String documento){
        try {
            this.usuario = RepositorioUsuarios.getInstance().crearUsuario(username, mail, contra, false);
            this.persona = RepositorioPersonas.getInstance().crearPersona(nombre, apellido, tipoDocumento, documento, this.usuario);
            devolverUsuarioCorrecto();
        }
        catch (UsuarioException e){
            devolverUsuarioIncorrecto();
        }
    }

    public void devolverUsuarioIncorrecto(){
        System.out.println("USUARIO INCORRECTO");
        //TODO devolver por pantalla
    }

    public void devolverUsuarioCorrecto(){
        System.out.println("USUARIO CORRECTO");
        //TODO devolver por pantalla
    }

}
