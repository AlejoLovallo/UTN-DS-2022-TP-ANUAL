package Miembro;

import Domain.Miembro.Persona;
import Domain.Miembro.RepositorioPersonas;
import Domain.Miembro.TipoDocumento;
import Domain.Usuarios.Usuario;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepoPersonasTest {
    protected RepositorioPersonas repoPersonas;

    private void initializePersona(){
        this.repoPersonas = RepositorioPersonas.getInstance();
    }

    @BeforeEach
    public void initialize(){
        initializePersona();
    }

    @AfterEach
    public void clean(){}

    //probar cuando se traiga todos los users de la DB
/*    @Test
    public void buscarPersonaxUser(){
        Usuario user= Common.getUsuario();

        Assertions.assertEquals("UNA PERSONA",this.repoPersonas.buscarPersonaPorUsuario(user));
    }*/

    @Test
    public void crearPersona(){
        Assertions.assertNotNull(this.repoPersonas.crearPersona("Alberto","Gonzalez",TipoDocumento.DNI,"123456",Common.getUsuario()));
    }
}
