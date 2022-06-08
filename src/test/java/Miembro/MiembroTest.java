package Miembro;

import Domain.Miembro.Miembro;
import Domain.Miembro.TipoDocumento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

public class MiembroTest {
    protected Miembro elMiembro;

    private void initializeMiembro(){
        this.elMiembro=new Miembro("Ester","Exposito", TipoDocumento.DNI,"456789546");
    }

    @BeforeEach
    public void initialize(){this.initializeMiembro();}

    @AfterEach
    public void clean(){}

    @Test
    public void miembroCreadoCorrectamente(){
        Assertions.assertEquals("Ester",this.elMiembro.getNombre());
        Assertions.assertEquals("Exposito",this.elMiembro.getApellido());
        Assertions.assertEquals(TipoDocumento.DNI,this.elMiembro.getTipoDocumento());
        Assertions.assertEquals("456789546",this.elMiembro.getDocumento());
        Assertions.assertEquals(Arrays.asList(),this.elMiembro.getTrayectos());
        Assertions.assertNull(this.elMiembro.getSectorPorOrganizacion());
    }

    @Test
    public void setNombre() {
        String nombreActual=this.elMiembro.getNombre();
        String nombrenuevo="Juana";

        this.elMiembro.setNombre(nombrenuevo);

        Assertions.assertEquals("Ester",nombreActual);
        Assertions.assertEquals(nombrenuevo,this.elMiembro.getNombre());
    }

    @Test
    public void setApellido(){
        String apellidoActual=this.elMiembro.getApellido();
        String apellidonuevo="Gonzalez";

        this.elMiembro.setApellido(apellidonuevo);

        Assertions.assertEquals("Exposito",apellidoActual);
        Assertions.assertEquals(apellidonuevo,this.elMiembro.getApellido());
    }




}
