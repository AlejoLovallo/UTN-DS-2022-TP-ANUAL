package Domain.Miembro;

import java.util.List;

public class Persona {
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private String documento;
    private List<Miembro> listaMiembro;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void agregarMiembro(Miembro miembro)
    {
        listaMiembro.add(miembro);
    }
}
