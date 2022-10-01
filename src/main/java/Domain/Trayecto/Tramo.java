package Domain.Trayecto;

import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.CalculadorDistancia.ServicioAPI;
import Domain.CalculadorDistancia.ServicioManual;
import Domain.Espacios.Espacio;
import Domain.CalculadorDistancia.ServicioDistancia;
import Domain.MediosDeTransporte.MedioDeTransporte;
import Domain.MediosDeTransporte.TransportePublico;
import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.Usuarios.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="tramo")
public class Tramo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_tramo;

  @Transient
  private ServicioDistancia estrategia;

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "puntoSalida",referencedColumnName = "id_espacio")
  private Espacio puntoPartida;

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name="puntoLlegada",referencedColumnName = "id_espacio")
  private Espacio puntoLLegada;

  @ManyToOne
  @JoinColumn(name="id_transporte",referencedColumnName = "id_transporte")
  private MedioDeTransporte medio;

  @ManyToOne
  @JoinColumn(name="id_trayecto",referencedColumnName = "id_trayecto")
  private Trayecto trayecto;

  //////////////////////////////////  CONSTRUCTOR
  private Tramo(){

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
   // this.trayectos = new ArrayList<>();

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
    //update();
  }

  public void setPuntoLLegada(Espacio puntoLLegada) {
    this.puntoLLegada = puntoLLegada;
    //update();
  }

  public void setMedio(MedioDeTransporte medio) {
    this.medio = medio;
    //update();
  }


  //////////////////////////////////  INTERFACE

  public Double determinarDistancia() throws IOException {
    return estrategia.calcularDistancia(medio, puntoPartida, puntoLLegada);
  }


  //// DB
  /*
  public void update(){
    EntityManagerHelper.tranUpdate(this);
  }

  public static Tramo get(int tramoID) {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityManagerHelper.beginTransaction();
    Tramo tramo = em.find(Tramo.class, tramoID);
    em.detach(tramo);
    EntityManagerHelper.commit();
    EntityManagerHelper.closeEntityManager();
    return tramo;
  }

  public void insert(){
    EntityManagerHelper.tranPersist(this);
  }*/
}
