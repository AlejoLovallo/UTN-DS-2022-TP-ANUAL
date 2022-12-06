package Domain.Organizacion;

import Domain.CalculadorHC.FactorEmision;
import Domain.Reportes.ReporteComposicion;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import Domain.Organizacion.Consumo;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name ="actividad")
public class Actividad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_actividad;

  @Enumerated(EnumType.STRING)
  private TipoDeActividad tipoActividad;
  @Enumerated(EnumType.STRING)
  private TipoDeConsumo tipoConsumo;
  @Enumerated(EnumType.STRING)
  private FrecuenciaServicio frecuenciaServicio;
  @Enumerated(EnumType.STRING)
  private UnidadDeConsumo unidad_Consumo;

  @OneToOne(cascade = CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name="id_factor",referencedColumnName = "id_factor")
  private FactorEmision factorEmision;

  @ManyToOne(cascade = CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name="id_organizacion",referencedColumnName = "id_organizacion")
  private Organizacion organizacion;

  @ManyToMany(mappedBy = "actividades")
  private List<ReporteComposicion> reporteComposicions;

/*
  @Enumerated(EnumType.STRING)
  private TipoDeActividad nombre;*/

  @OneToMany(mappedBy = "actividad",cascade = CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  private List<Consumo> consumosActividad;

  //////////////////////////////////  CONSTRUCTORES
  private Actividad(){

  }


  public Actividad(TipoDeActividad _tipoActividad,TipoDeConsumo _tipoDeConsumo, Organizacion _organizacion, FactorEmision _factorEmision){
    this.tipoActividad=_tipoActividad;
    this.tipoConsumo =_tipoDeConsumo;
    //this.unidadDeConsumo=_unidadConsumo;
    this.organizacion = _organizacion;
    this.factorEmision = _factorEmision;
    this.consumosActividad = new ArrayList<>();
  }

  //TODO: revisar si generar reporte necesita factor de emision
  public Actividad(TipoDeActividad _tipoActividad,TipoDeConsumo _tipoDeConsumo, Organizacion _organizacion){
    this.tipoActividad=_tipoActividad;
    this.tipoConsumo =_tipoDeConsumo;
    //this.unidadDeConsumo=_unidadConsumo;
    this.organizacion = _organizacion;
    this.consumosActividad = new ArrayList<>();
  }

  //////////////////////////////////  GETTERS

  //getters

  public TipoDeActividad getNombre() {return tipoActividad;}

  public TipoDeConsumo getTipoConsumo() {return tipoConsumo;}

  public UnidadDeConsumo getUnidad_Consumo() {return unidad_Consumo;}

  public List <Consumo> getConsumos() {return consumosActividad;}

  public FactorEmision getFactorEmision() {
    return factorEmision;
  }

  public void setFactorEmision(FactorEmision factorEmision) {
    this.factorEmision = factorEmision;
  }

  //setters


  public void setOrganizacion(Organizacion organizacion) {
    this.organizacion = organizacion;
  }

  public void setUnidad_Consumo(UnidadDeConsumo unidad_Consumo) {this.unidad_Consumo = unidad_Consumo;}

  public void setTipoConsumo(TipoDeConsumo tipoConsumo) {this.tipoConsumo = tipoConsumo;}

  public void setNombre(TipoDeActividad nombre) {this.tipoActividad = nombre;}

  public void setConsumos(ArrayList <Consumo> consumos) {this.consumosActividad = consumos;}

  //METHODS

  public void agregarConsumo(Integer mes, Integer anio, Double consumo){

    try{
      Optional<Consumo> consumoActividad  = this.consumosActividad.stream().filter(unConsumo -> mes.equals(unConsumo.getMes()) && anio.equals(unConsumo.getAnio())).findAny();
      Consumo con = consumoActividad.get();
      con.setConsumo(con.getConsumo() + consumo);
    }
    catch (NoSuchElementException e)
    {
      Consumo consu = new Consumo(mes, anio, consumo, this);
      this.consumosActividad.add(consu);
    }
    
  }

  public Double encontrarConsumo(Integer mes, Integer anio){

    Optional<Consumo> consumoActividad  = this.consumosActividad.stream().filter(unConsumo -> mes.equals(unConsumo.getMes()) && anio.equals(unConsumo.getAnio())).findAny();

    try{
      Consumo con = consumoActividad.get();
      return con.getConsumo();
    }
    catch(NoSuchElementException e){
      return 0.0;
    }
  }
}
