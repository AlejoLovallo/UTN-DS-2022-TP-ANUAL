package Domain.Espacios;

import org.apache.poi.ss.formula.functions.T;

public class Direccion extends Espacio {

  private String pais;

  private String provincia;

  private String municipio;

  private String localidad;
  private String calle;
  private Integer altura;
  private TipoDireccion tipoDireccion;

  //////////////////////////////////  CONSTRUCTORES
  public Direccion(String _pais, String _provincia, String _municipio, String _localidad, String _calle, Integer _altura, TipoDireccion tipoDireccion ){
      this.calle = _calle;
      this.altura = _altura;
      this.tipoDireccion = tipoDireccion;
  }

  //////////////////////////////////  GETTERS
  public String getCalle(){return this.calle;}

  public Integer getAltura(){return this.altura;}

  public TipoDireccion getTipoEspacio(){return this.tipoDireccion;}

  @Override
  public String toString(){
    return this.tipoDireccion.toString() + ": " +  this.calle + " " + this.altura.toString();
  }

  //////////////////////////////////  SETTERS
  public void setCalle(String _calle){
    this.calle = _calle;
  }

  public void setAltura(Integer _altura){
    this.altura = _altura;
  }

  public void setTipoEspacio(TipoDireccion _tipoEspacio){
    this.tipoDireccion = _tipoEspacio;
  }
  //////////////////////////////////  INTERFACE


}
