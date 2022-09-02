package Domain.Miembro;

import Domain.Usuarios.Excepciones.UsuarioException;
import Domain.Usuarios.Usuario;

import java.util.ArrayList;

public class RepositorioPersonas {
  //////////////////////////////////  VARIABLES
  private static RepositorioPersonas instance = null;
  private ArrayList<Persona> personas;


  //////////////////////////////////  CONSTRUCTORES
  private RepositorioPersonas(){
    //todo traer todos los usuarios de la DB
    this.personas = new ArrayList<>();
  }

  public static RepositorioPersonas getInstance(){
    if(instance == null){
      instance = new RepositorioPersonas();
    }
    return instance;
  }

  public Persona buscarPersonaPorUsuario(Usuario usuarioABuscar){
    //TODO buscar en la DB de Persona
    return this.personas.stream().filter(persona -> persona.getUsuario().equals(usuarioABuscar)).findFirst().orElse(null);
  }

  public Persona crearPersona(String _nombre, String _apellido, TipoDocumento _tipoDocumento, String _documento, Usuario user){
    Persona personaNueva = new Persona(_nombre,_apellido,_tipoDocumento,_documento);
    personaNueva.setUsuario(user);
    //TODO agregar a DB de Persona
    this.personas.add(personaNueva);
    return personaNueva;
  }
}
