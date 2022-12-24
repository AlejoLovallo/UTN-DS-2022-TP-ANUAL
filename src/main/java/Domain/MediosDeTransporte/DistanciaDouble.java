package Domain.MediosDeTransporte;

import Domain.Espacios.Estacion;

import javax.persistence.*;
import java.lang.Double;

@Entity
@Table(name = "distancia")
public class DistanciaDouble {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // ESTO ES POR LA HERENCIA
  private Integer id_distancia;

  @Column
  private Double distancia;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "espacio_id")
  private Estacion espacio;

  public DistanciaDouble(Double value) {
    this.distancia= value;
  }
  public DistanciaDouble(){}

  public double getDistancia() {
    return distancia;
  }

  public void setDistancia(Double distancia) {
    this.distancia = distancia;
  }
}
