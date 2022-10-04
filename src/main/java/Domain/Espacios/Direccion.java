package Domain.Espacios;

import javax.persistence.*;


@Entity
@Table(name="direccion")
@PrimaryKeyJoinColumn(name="id_espacio")
public class Direccion extends Espacio {

  //@Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY) //ANTERIOR
  //@GeneratedValue(strategy = GenerationType.TABLE) //POR HERENCIA
  //private int id_direccion;

  @Column
  private String pais;
  @Column
  private String calle;
  @Column
  private Integer altura;

  @Column(name = "tipo_espacio")
  @Enumerated(EnumType.STRING)
  private TipoDireccion tipoDireccion;

  @Column
  private String provincia;
  @Column
  private String municipio;
  @Column
  private String localidad;

  //////////////////////////////////  CONSTRUCTOR

  private Direccion(){

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
    //updateDireccion();
  }
  public void setProvincia(String provincia) {
    this.provincia = provincia;
    //updateDireccion();
  }
  public void setMunicipio(String municipio) {
    this.municipio = municipio;
    //updateDireccion();
  }
  public void setLocalidad(String localidad) {
    this.localidad = localidad;
    //updateDireccion();
  }
  public void setCalle(String _calle){
    this.calle = _calle;
    //updateDireccion();
  }
  public void setAltura(Integer _altura){
    this.altura = _altura;
    //updateDireccion();
  }
  public void setTipoEspacio(TipoDireccion _tipoEspacio){
    this.tipoDireccion = _tipoEspacio;
    //updateDireccion();
  }

  //////////////////////////////////  INTERFACE
/*
  public void updateDireccion(){
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("ds");
    System.out.println("----------------LUEGO DE Crear Entity Manager-------------------");
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      System.out.println("----------------LUEGO DE BEGIN TRAN-------------------");
      em.merge(this);
      System.out.println("----------------LUEGO DE UPDATE TRAN-------------------");
      em.getTransaction().commit();
      System.out.println("----------------LUEGO DE COMMIT-------------------");
    } catch (Exception e) {
      e.getCause();
      e.printStackTrace();
    } finally {
      em.close();
      System.out.println("----------------LUEGO DE CLOSE CON-------------------");
    }
  }

  public Direccion getDireccion(int direccionID) {
    EntityManager em = EntityManagerHelper.getEntityManager();
    Direccion direccion = em.find(Direccion.class, new Long(direccionID));
    em.detach(direccion);
    return direccion;
  }*/

}
