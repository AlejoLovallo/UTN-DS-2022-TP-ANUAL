package Domain.Trayecto;

import Domain.CalculadorDistancia.ServicioAPI;
import Domain.CalculadorDistancia.ServicioManual;
import Domain.Espacios.Espacio;
import Domain.CalculadorDistancia.ServicioDistancia;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.TransportePublico;
import Domain.MediosDeTransporte.VehiculoParticular;

import java.io.IOException;
import javax.persistence.*;

@Entity
@Table(name="tramo")
public class Tramo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_tramo;

  @Transient
  private ServicioDistancia estrategia;
  @OneToOne
  @JoinColumn(name = "puntoPartida",referencedColumnName = "id_espacio")
  private Espacio puntoPartida;
  @OneToOne
  @JoinColumn(name="puntoLlegada",referencedColumnName = "id_espacio")
  private Espacio puntoLLegada;
  @ManyToOne
  @JoinColumn(name="id_transporte",referencedColumnName = "id_transporte")
  private MedioDeTransporte medio;

  //////////////////////////////////  CONSTRUCTOR
  public Tramo(){

  }

  public Tramo(Espacio puntoPartida, Espacio puntoLLegada, MedioDeTransporte medio) {
    this.puntoPartida = puntoPartida;
    this.puntoLLegada = puntoLLegada;
    this.medio = medio;
    if (medio instanceof TransportePublico){
      this.estrategia = new ServicioManual();
    } else if(medio instanceof VehiculoParticular){
      this.estrategia = ServicioAPI.getInstance();
    }

  }

  //////////////////////////////////  GETTERS
  public Espacio getPuntoPartida(){
    return this.puntoPartida;
  }

  public Espacio getPuntoLlegada(){
    return this.puntoLLegada;
  }

  public MedioDeTransporte getMedioTransporte(){
    return this.medio;
  }


  //////////////////////////////////  SETTERS

  public void setPuntoPartida(Espacio puntoPartida) {
    this.puntoPartida = puntoPartida;
  }

  public void setPuntoLLegada(Espacio puntoLLegada) {
    this.puntoLLegada = puntoLLegada;
  }

  public void setMedio(MedioDeTransporte medio) {
    this.medio = medio;
  }


  //////////////////////////////////  INTERFACE

  public Double determinarDistancia() throws IOException {
    return estrategia.calcularDistancia(medio, puntoPartida, puntoLLegada);
  }
}
