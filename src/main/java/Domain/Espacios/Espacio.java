package Domain.Espacios;

public class Espacio {
  private String calle;
  private Integer altura;
  private TipoEspacio tipoEspacio;

  //////////////////////////////////  CONSTRUCTORES
  public Espacio(String _calle, Integer _altura, TipoEspacio _tipoEspacio ){
      this.calle = _calle;
      this.altura = _altura;
      this.tipoEspacio = _tipoEspacio;
  }

  //////////////////////////////////  GETTERS
  public String getCalle(){return this.calle;}

  public Integer getAltura(){return this.altura;}

  public TipoEspacio getTipoEspacio(){return this.tipoEspacio;}

  @Override
  public String toString(){
    return this.tipoEspacio.toString() + ": " +  this.calle + " " + this.altura.toString();
  }

  //////////////////////////////////  SETTERS
  public void setCalle(String _calle){
    this.calle = _calle;
  }

  public void setAltura(Integer _altura){
    this.altura = _altura;
  }

  public void setTipoEspacio(TipoEspacio _tipoEspacio){
    this.tipoEspacio = _tipoEspacio;
  }
  //////////////////////////////////  INTERFACE


}
