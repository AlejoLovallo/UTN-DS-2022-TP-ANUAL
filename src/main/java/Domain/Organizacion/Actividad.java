package Domain.Organizacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Optional;

@Entity
@Table(name ="actividad")
public class Actividad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_actividad;

  @Enumerated(EnumType.STRING)
  private TipoDeActividad tipoDeActividad;
  @Enumerated(EnumType.STRING)
  private TipoDeConsumo tipoDeConsumo;
  @Enumerated(EnumType.STRING)
  private FrecuenciaServicio frecuenciaServicio;
  @Enumerated(EnumType.STRING)
  private UnidadDeConsumo unidadDeConsumo;

  @Enumerated(EnumType.STRING)
  private TipoDeActividad nombre;

  private ArrayList<Consumo> consumosActividad;

  //////////////////////////////////  CONSTRUCTORES
  public Actividad(){

  }

  public Actividad(TipoDeActividad _tipoActividad,TipoDeConsumo _tipoDeConsumo){
    this.nombre=_tipoActividad;
    this.tipoDeConsumo=_tipoDeConsumo;
    //this.unidadDeConsumo=_unidadConsumo;
  }

  //////////////////////////////////  GETTERS
  private ArrayList <Consumo> consumos;

  //getters

  public TipoDeActividad getNombre() {return nombre;}

  public TipoDeConsumo getTipoDeConsumo() {return tipoDeConsumo;}

  public UnidadDeConsumo getUnidadDeConsumo() {return unidadDeConsumo;}

  public ArrayList <Consumo> getConsumos() {return consumos;}


  //setters

  public void setUnidadDeConsumo(UnidadDeConsumo unidadDeConsumo) {this.unidadDeConsumo = unidadDeConsumo;}

  public void setTipoDeConsumo(TipoDeConsumo tipoDeConsumo) {this.tipoDeConsumo = tipoDeConsumo;}

  public void setNombre(TipoDeActividad nombre) {this.nombre = nombre;}

  public void setConsumos(ArrayList <Consumo> consumos) {this.consumos = consumos;}

  //METHODS

  public void agregarConsumo(Integer mes, Integer anio, Double consumo){

    Optional<Consumo> consumoActividad  = this.getConsumos().stream().filter(unConsumo -> mes.equals(unConsumo.getMes()) && anio.equals(unConsumo.getAnio())).findAny();

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

    Optional<Consumo> consumoActividad  = this.getConsumos().stream().filter(unConsumo -> mes.equals(unConsumo.getMes()) && anio.equals(unConsumo.getAnio())).findAny();

    Consumo con = consumoActividad.get();
    if (con != null){
      consumo = con.getConsumo();
    }

    return consumo;
  }
}
