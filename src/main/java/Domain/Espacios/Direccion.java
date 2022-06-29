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

  //////////////////////////////////  CONSTRUCTOR

  public Direccion(String pais, String provincia, String municipio, String localidad, String calle, Integer altura, TipoDireccion tipoDireccion) {
    this.pais = pais.toUpperCase();
    this.provincia = provincia.toUpperCase();
    this.municipio = municipio.toUpperCase();
    this.localidad = localidad.toUpperCase();
    this.calle = calle;
    this.altura = altura;
    this.tipoDireccion = tipoDireccion;
  }

  //////////////////////////////////  GETTERS
  public String getPais() {
    return pais;
  }
  public String getProvincia() {
    return provincia;
  }
  public String getMunicipio() {
    return municipio;
  }
  public String getLocalidad() {
    return localidad;
  }
  public String getCalle(){return this.calle;}
  public Integer getAltura(){return this.altura;}
  public TipoDireccion getTipoDireccion(){return this.tipoDireccion;}
  @Override
  public String toString(){
    return this.tipoDireccion.toString() + ": " +  this.calle + " " + this.altura.toString();
  }

  //////////////////////////////////  SETTERS
  public void setPais(String pais) {
    this.pais = pais;
  }
  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }
  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }
  public void setLocalidad(String localidad) {
    this.localidad = localidad;
  }
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
