package Domain.ServicioMedicion;

import Domain.CalculadorHC.RepositorioFactores;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Actividad {
  private TipoDeActividad nombre;
  private TipoDeConsumo tipoDeConsumo;
  private UnidadDeConsumo unidadDeConsumo;
  private FrecuenciaServicio frecuenciaServicio;
  private Date fechaCarga;

  private Date fechaDeBaja;
  private Double consumo;
  private Boolean estaActivo;

  //CONSTRUCTOR
  public Actividad(TipoDeActividad _tipoActividad,TipoDeConsumo _tipoDeConsumo,FrecuenciaServicio _frecuenciaServicio, Date _fechaCarga,UnidadDeConsumo _unidadConsumo){
    this.nombre = _tipoActividad;
    this.tipoDeConsumo = _tipoDeConsumo;
    this.unidadDeConsumo = _unidadConsumo;
    this.frecuenciaServicio = _frecuenciaServicio;
    this.fechaCarga = _fechaCarga;
    this.estaActivo = true;
  }

  //getters
  public TipoDeActividad getNombre() {return nombre;}

  public TipoDeConsumo getTipoDeConsumo() {return tipoDeConsumo;}

  public UnidadDeConsumo getUnidadDeConsumo() {return unidadDeConsumo;}

  public Double getConsumo() {return consumo;}

  public FrecuenciaServicio getFrecuenciaServicio() { return frecuenciaServicio; }

  public Date getFechaCarga() { return fechaCarga; }

  public Boolean getEstaActivo() {
    return estaActivo;
  }

  //setters
  public void setConsumo(Double consumo) {this.consumo = consumo;}

  public void setUnidadDeConsumo(UnidadDeConsumo unidadDeConsumo) {this.unidadDeConsumo = unidadDeConsumo;}

  public void setEstaActivo(Boolean estaActivo) {
    this.estaActivo = estaActivo;
  }

  public void setTipoDeConsumo(TipoDeConsumo tipoDeConsumo) {this.tipoDeConsumo = tipoDeConsumo;}

  public void setNombre(TipoDeActividad nombre) {this.nombre = nombre;}

  public double obtenercantidadHC(int mes, int anio) {
    double cantidadHC = 0.0;

    if(fechaCarga.getYear() + 1900 < anio || (fechaCarga.getYear() + 1900 == anio && fechaCarga.getMonth() + 1 <= mes)){

      Double factorDeEmision = RepositorioFactores.getInstance().getFactorDeEmisionSegunActividad(this.getNombre()).getNumero();

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

    Double factorDeEmision = RepositorioFactores.getInstance().getFactorDeEmisionSegunActividad(this.getNombre()).getNumero();

    LocalDate localDate = Instant.ofEpochMilli(fechaCarga.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate localDate1;

    if(this.estaActivo){
      localDate1 = LocalDate.now();
    }else{
      localDate1 = Instant.ofEpochMilli(fechaDeBaja.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    long haceCuantoExisteLaActividad;

    if(this.getFrecuenciaServicio() == FrecuenciaServicio.MENSUAL){
      haceCuantoExisteLaActividad = localDate.until(localDate1, ChronoUnit.MONTHS);
      cantidadHC = this.getConsumo() * factorDeEmision * haceCuantoExisteLaActividad;
    }
    else{
      haceCuantoExisteLaActividad = localDate.until(localDate1, ChronoUnit.MONTHS);
      cantidadHC = this.getConsumo()/12 * factorDeEmision * haceCuantoExisteLaActividad;
    }


    return cantidadHC;
  }

  public void darDeBaja() {
    this.setEstaActivo(false);
    this.fechaDeBaja = new Date(System.currentTimeMillis());
  }

  public static void main(String[] args){
    Date fecha = new Date();
    fecha.setYear(100);
    fecha.setMonth(03);
    fecha.setDate(15);
    LocalDate localDate = Instant.ofEpochMilli(fecha.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate localDate1 = LocalDate.now();

    long haceCuantoExisteLaActividad = localDate.until(localDate1, ChronoUnit.MONTHS);

    System.out.println(haceCuantoExisteLaActividad);
  }


}
