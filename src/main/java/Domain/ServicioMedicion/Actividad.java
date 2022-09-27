package Domain.ServicioMedicion;

import Domain.CalculadorHC.RepositorioFactores;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name ="actividad")
public class Actividad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_actividad;

  @Enumerated(EnumType.STRING)
  private TipoDeActividad nombre;
  @Enumerated(EnumType.STRING)
  private TipoDeConsumo tipoDeConsumo;
  @Enumerated(EnumType.STRING)
  private FrecuenciaServicio frecuenciaServicio;
  @Enumerated(EnumType.STRING)
  private UnidadDeConsumo unidadDeConsumo;

  @Column
  private Double consumo;
  @Column
  private Date fechaCarga;

  @Column
  private Date fechaDeBaja;
  @Column
  private Boolean estaActivo;

  //////////////////////////////////  CONSTRUCTORES
  public Actividad(){

  }

  public Actividad(TipoDeActividad _tipoActividad,TipoDeConsumo _tipoDeConsumo,FrecuenciaServicio _frecuenciaServicio){
    this.nombre=_tipoActividad;
    this.tipoDeConsumo=_tipoDeConsumo;
    //this.unidadDeConsumo=_unidadConsumo;
  }

  //////////////////////////////////  GETTERS
  private ArrayList <ConsumoActividad> consumos;

  //getters

  public TipoDeActividad getNombre() {return nombre;}

  public TipoDeConsumo getTipoDeConsumo() {return tipoDeConsumo;}

  public UnidadDeConsumo getUnidadDeConsumo() {return unidadDeConsumo;}

  public ArrayList <ConsumoActividad> getConsumos() {return consumos;}


  //setters

  public void setUnidadDeConsumo(UnidadDeConsumo unidadDeConsumo) {this.unidadDeConsumo = unidadDeConsumo;}

  public void setEstaActivo(Boolean estaActivo) {
    this.estaActivo = estaActivo;
  }

  public void setTipoDeConsumo(TipoDeConsumo tipoDeConsumo) {this.tipoDeConsumo = tipoDeConsumo;}

  public void setNombre(TipoDeActividad nombre) {this.nombre = nombre;}

  public void setConsumos(ArrayList <ConsumoActividad> consumos) {this.consumos = consumos;}

  //METHODS

  public void agregarConsumo(Integer mes, Integer anio, Double consumo){

    Optional<ConsumoActividad> consumoActividad  = this.getConsumos().stream().filter(unConsumo -> mes.equals(unConsumo.getMes()) && anio.equals(unConsumo.getAnio())).findAny();

    ConsumoActividad con = consumoActividad.get();

    if (con != null){
      con.setConsumo(con.getConsumo() + consumo);
    }else{
      ConsumoActividad consu = new ConsumoActividad(mes, anio, consumo);
      this.consumos.add(consu);
    }
    
  }

  public Double encontrarConsumo(Integer mes, Integer anio){

    Double consumo = 0.0;

    Optional<ConsumoActividad> consumoActividad  = this.getConsumos().stream().filter(unConsumo -> mes.equals(unConsumo.getMes()) && anio.equals(unConsumo.getAnio())).findAny();

    ConsumoActividad con = consumoActividad.get();
    if (con != null){
      consumo = con.getConsumo();
    }

    return consumo;
  }
}
