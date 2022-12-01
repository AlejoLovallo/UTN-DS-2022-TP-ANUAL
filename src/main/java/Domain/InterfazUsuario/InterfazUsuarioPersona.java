package Domain.InterfazUsuario;

import Domain.Miembro.Persona;
import Domain.Miembro.TipoDocumento;
import Domain.Repositorios.RepositorioPersonasDB;
import Domain.Repositorios.RepositorioUsuariosDB;
import Domain.Usuarios.Excepciones.UsuarioException;
import Domain.Usuarios.Usuario;



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
            RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
            RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();

            this.usuario = repositorioUsuariosDB.validarLogueoUsuario(username, contra);
            this.persona = repositorioPersonasDB.buscarPersonaPorUsuario(this.usuario);
            devolverUsuarioCorrecto();
        }
        catch (UsuarioException e){
            devolverUsuarioIncorrecto();
        }
    }

    public void crearUsuarioPersona(String username, String mail, String contra, String nombre, String apellido, TipoDocumento tipoDocumento, String documento){
        try {
            RepositorioUsuariosDB repositorioUsuariosDB = new RepositorioUsuariosDB();
            RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();

            this.usuario = repositorioUsuariosDB.crearUsuario(username, mail, contra, false);
            this.persona = repositorioPersonasDB.crearPersona(nombre, apellido, tipoDocumento, documento, this.usuario);
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
