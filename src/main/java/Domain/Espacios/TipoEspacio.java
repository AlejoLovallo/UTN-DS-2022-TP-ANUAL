package Domain.Espacios;

public abstract class TipoEspacio {
  private String calle;
  private Integer altura;

  protected String getCalle(){
      return this.calle;
  }

  protected void setCalle(String calle){
      this.calle = calle;
  }

  protected Integer getAltura(){
    return this.altura;
  }

  protected void setAltura(Integer altura){
    this.altura = altura;
  }
}
