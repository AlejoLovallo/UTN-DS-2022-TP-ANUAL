package Domain.Organizacion;

import Domain.CalculadorHC.FactorEmision;
import Domain.Reportes.ReporteComposicion;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import Domain.Organizacion.Consumo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
  public Actividad(){

  }

  public Actividad(TipoDeActividad _tipoActividad,TipoDeConsumo _tipoDeConsumo){
    this.tipoActividad=_tipoActividad;
    this.tipoConsumo =_tipoDeConsumo;
    //this.unidadDeConsumo=_unidadConsumo;
  }

  //////////////////////////////////  GETTERS
  private ArrayList <Consumo> consumos;

  //getters

  public TipoDeActividad getNombre() {return tipoActividad;}

  public TipoDeConsumo getTipoConsumo() {return tipoConsumo;}

  public UnidadDeConsumo getUnidad_Consumo() {return unidad_Consumo;}

  public List <Consumo> getConsumos() {return consumos;}

  public FactorEmision getFactorEmision() {
    return factorEmision;
  }

  public void setFactorEmision(FactorEmision factorEmision) {
    this.factorEmision = factorEmision;
  }

  //setters

  public void setUnidad_Consumo(UnidadDeConsumo unidad_Consumo) {this.unidad_Consumo = unidad_Consumo;}

  public void setTipoConsumo(TipoDeConsumo tipoConsumo) {this.tipoConsumo = tipoConsumo;}

  public void setNombre(TipoDeActividad nombre) {this.tipoActividad = nombre;}

  public void setConsumos(ArrayList <Consumo> consumos) {this.consumos = consumos;}

  //METHODS

  public void agregarConsumo(Integer mes, Integer anio, Double consumo){

    Optional<Consumo> consumoActividad  = this.consumos.stream().filter(unConsumo -> mes.equals(unConsumo.getMes()) && anio.equals(unConsumo.getAnio())).findAny();

    Consumo con = consumoActividad.get();

    if (con != null){
      con.setConsumo(con.getConsumo() + consumo);
    }else{
      Consumo consu = new Consumo(mes, anio, consumo);
      this.consumos.add(consu);
    }
    
  }

  public Double encontrarConsumo(Integer mes, Integer anio){

    Double consumo = 0.0;

    Optional<Consumo> consumoActividad  = this.consumos.stream().filter(unConsumo -> mes.equals(unConsumo.getMes()) && anio.equals(unConsumo.getAnio())).findAny();

    Consumo con = consumoActividad.get();
    if (con != null){
      consumo = con.getConsumo();
    }

    return consumo;
  }
}
