package Domain.Organizacion;

public class Recomendacion {

    private Organizacion organizacion;

    private String recomendacion;

    // CONSTRUCTOR

    public  Recomendacion(){}
    public Recomendacion(Organizacion organizacion, String recomendacion) {
        this.organizacion = organizacion;
        this.recomendacion = recomendacion;
    }

    // GETTERS

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    // SETTERS

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }
}
