package Miembro;

import Domain.Miembro.Persona;
import Domain.Miembro.TipoDocumento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PersonaTest {
    protected Persona unaPersona;

    private void initializePersona(){
      this.unaPersona = new Persona("Ester","Exposito", TipoDocumento.DNI,"456789546");
    }

    @BeforeEach
    public void initialize(){
      initializePersona();
    }

    @AfterEach
    public void clean(){}

  @Test
  public void miembroCreadoCorrectamente(){
    Assertions.assertEquals("Ester",this.unaPersona.getNombre());
    Assertions.assertEquals("Exposito",this.unaPersona.getApellido());
    Assertions.assertEquals(TipoDocumento.DNI,this.unaPersona.getTipoDocumento());
    Assertions.assertEquals("456789546",this.unaPersona.getDocumento());
  }

  @Test
  public void setNombre() {
    String nombreActual=this.unaPersona.getNombre();
    String nombrenuevo="Juana";

    this.unaPersona.setNombre(nombrenuevo);

    Assertions.assertEquals("Ester",nombreActual);
    Assertions.assertEquals(nombrenuevo,this.unaPersona.getNombre());
  }

  @Test
  public void setApellido(){
    String apellidoActual=this.unaPersona.getApellido();
    String apellidonuevo="Gonzalez";

    this.unaPersona.setApellido(apellidonuevo);

    Assertions.assertEquals("Exposito",apellidoActual);
    Assertions.assertEquals(apellidonuevo,this.unaPersona.getApellido());
  }

  @Test
  public void setTipoDoc(){
    TipoDocumento tipodocActual=this.unaPersona.getTipoDocumento();
    TipoDocumento tipodocNuevo=TipoDocumento.PASAPORTE;

    this.unaPersona.setTipoDocumento(tipodocNuevo);

    Assertions.assertEquals(TipoDocumento.DNI,tipodocActual);
    Assertions.assertEquals(tipodocNuevo,this.unaPersona.getTipoDocumento());
  }

  @Test
  public void setDoc(){
    String docActual=this.unaPersona.getDocumento();
    String docNuevo="123456789";

    this.unaPersona.setDocumento(docNuevo);

    Assertions.assertEquals("456789546",docActual);
    Assertions.assertEquals(docNuevo,this.unaPersona.getDocumento());
  }

}
