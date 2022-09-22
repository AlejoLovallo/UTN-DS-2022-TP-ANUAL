package Domain.Espacios;

import org.apache.poi.ss.formula.functions.T;
import javax.persistence.*;


@Entity
@Table(name="direccion")
public class Direccion extends Espacio {
  @Column
  private String pais;
  @Column
  private String calle;
  @Column
  private Integer altura;
  @Enumerated(EnumType.STRING)
  private TipoDireccion tipoDireccion;

  /**
   * TODO: Las marco como trasient pero fijarse cuales quedan y cuales no.
   */
  @Transient
  private String provincia;
  @Transient
  private String municipio;
  @Transient
  private String localidad;

  //////////////////////////////////  CONSTRUCTOR

  public Direccion(){

  }

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
