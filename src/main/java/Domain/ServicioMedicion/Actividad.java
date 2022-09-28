package Domain.ServicioMedicion;

import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.CalculadorHC.RepositorioFactores;
import Domain.Usuarios.Usuario;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.persistence.*;

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

  @Column
  private Double consumo;
  @Column
  private Date fechaCarga;

  @Column
  private Date fechaDeBaja;
  @Column
  private Boolean estaActivo;

  //////////////////////////////////  CONSTRUCTORES
  private Actividad(){

  }

  public Actividad(TipoDeActividad _tipoActividad,TipoDeConsumo _tipoDeConsumo,FrecuenciaServicio _frecuenciaServicio, Date _fechaCarga,UnidadDeConsumo _unidadConsumo){
    this.tipoDeActividad = _tipoActividad;
    this.tipoDeConsumo = _tipoDeConsumo;
    this.unidadDeConsumo = _unidadConsumo;
    this.frecuenciaServicio = _frecuenciaServicio;
    this.fechaCarga = _fechaCarga;
    this.estaActivo = true;
  }

  //////////////////////////////////  GETTERS
  public TipoDeActividad getTipoDeActividad() {return tipoDeActividad;}

  public TipoDeConsumo getTipoDeConsumo() {return tipoDeConsumo;}

  public UnidadDeConsumo getUnidadDeConsumo() {return unidadDeConsumo;}

  public Double getConsumo() {return consumo;}

  public FrecuenciaServicio getFrecuenciaServicio() { return frecuenciaServicio; }

  public Date getFechaCarga() { return fechaCarga; }

  public Boolean getEstaActivo() {
    return estaActivo;
  }


  //////////////////////////////////  SETTERS
  public void setConsumo(Double consumo) {
    this.consumo = consumo;
    update();
  }

  public void setUnidadDeConsumo(UnidadDeConsumo unidadDeConsumo) {
    this.unidadDeConsumo = unidadDeConsumo;
    update();
  }

  public void setEstaActivo(Boolean estaActivo) {
    this.estaActivo = estaActivo;
    update();
  }

  public void setTipoDeConsumo(TipoDeConsumo tipoDeConsumo) {
    this.tipoDeConsumo = tipoDeConsumo;
    update();
  }

  public void setTipoDeActividad(TipoDeActividad tipoDeActividad) {
    this.tipoDeActividad = tipoDeActividad;
    update();
  }


  //////////////////////////////////  INTERFACE

  public double obtenercantidadHC(int mes, int anio) {
    double cantidadHC = 0.0;

    if(fechaCarga.getYear() + 1900 < anio || (fechaCarga.getYear() + 1900 == anio && fechaCarga.getMonth() + 1 <= mes)){

      Double factorDeEmision = RepositorioFactores.getInstance().getFactorDeEmisionSegunActividad(this.getTipoDeActividad()).getNumero();

      if(this.getFrecuenciaServicio() == FrecuenciaServicio.MENSUAL){
        cantidadHC = this.getConsumo() * factorDeEmision;
      }else{
        cantidadHC = this.getConsumo()/12 * factorDeEmision;
      }

      return cantidadHC;

    }else{

      return cantidadHC;
    }
  }


  public double obtenercantidadHCTotal() {
    double cantidadHC = 0.0;

    Double factorDeEmision = RepositorioFactores.getInstance().getFactorDeEmisionSegunActividad(this.getTipoDeActividad()).getNumero();

    LocalDate fechaDeInicio = Instant.ofEpochMilli(fechaCarga.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

    LocalDate fechaFinal;

    if(this.estaActivo){
      fechaFinal = LocalDate.now();
    }else{
      fechaFinal = Instant.ofEpochMilli(fechaDeBaja.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    long haceCuantoExisteLaActividad;
    haceCuantoExisteLaActividad = fechaDeInicio.until(fechaFinal, ChronoUnit.MONTHS);

    if(this.getFrecuenciaServicio() == FrecuenciaServicio.MENSUAL){
      cantidadHC = this.getConsumo() * factorDeEmision * haceCuantoExisteLaActividad;
    }
    else{
      cantidadHC = this.getConsumo()/12 * factorDeEmision * haceCuantoExisteLaActividad;
    }


    return cantidadHC;
  }

  public void darDeBaja() {
    this.setEstaActivo(false);
    this.fechaDeBaja = new Date(System.currentTimeMillis());
  }

//  public static void main(String[] args){
//    Date fecha = new Date();
//    fecha.setYear(100);
//    fecha.setMonth(03);
//    fecha.setDate(15);
//    LocalDate localDate = Instant.ofEpochMilli(fecha.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
//    LocalDate localDate1 = LocalDate.now();
//
//    long haceCuantoExisteLaActividad = localDate.until(localDate1, ChronoUnit.MONTHS);
//
//    System.out.println(haceCuantoExisteLaActividad);
//  }


  public void update(){
    EntityManagerHelper.tranUpdate(this);
  }

  public static Actividad get(int actividadID) {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityManagerHelper.beginTransaction();
    Actividad actividad = em.find(Actividad.class, actividadID);
    em.detach(actividad);
    EntityManagerHelper.commit();
    EntityManagerHelper.closeEntityManager();
    return actividad;
  }

  public void insert(){
    EntityManagerHelper.tranPersist(this);
  }

}
