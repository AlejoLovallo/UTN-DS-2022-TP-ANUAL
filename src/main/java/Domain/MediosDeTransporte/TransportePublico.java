package Domain.MediosDeTransporte;

import java.util.HashMap;
import java.util.Map;

import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.Espacios.Estacion;
import javax.persistence.*;

@Entity
@Table(name="transportepublico")
public class TransportePublico extends MedioDeTransporte {

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoTransporte")
  private TipoTransportePublico tipoTransportePublico;
  @Column
  private String linea;

  //TODO: arreglar persistencia
  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "paradas_mapping",
      joinColumns = {@JoinColumn(name = "tranportePublico_id", referencedColumnName = "id_transporte")},
      inverseJoinColumns = {@JoinColumn(name = "id_distancia", referencedColumnName = "id_distancia")})
  @MapKeyJoinColumn(name = "espacio_id")
  //NOTA: double es distancia a la siguiente parada (en la ultima parada el valor es 0)
  private Map<Estacion, DistanciaDouble> paradas = new HashMap<>();


  //////////////////////////////////  CONSTRUCTOR

  private TransportePublico(){

  }

  public TransportePublico(TipoTransportePublico tipoTransportePublico, String linea, Map<Estacion, DistanciaDouble> paradas) {
    this.tipoTransportePublico = tipoTransportePublico;
    this.linea = linea;
    this.paradas = paradas;
  }

  //////////////////////////////////  GETTERS

  public TipoTransportePublico getTipoTransportePublico() {
    return this.tipoTransportePublico;
  }

  public String getLinea() {
    return this.linea;
  }

  public Map<Estacion, DistanciaDouble> getParadas() {
    return this.paradas;
  }

  //////////////////////////////////  SETTERS


  public void setTipoTransportePublico(TipoTransportePublico tipoTransportePublico) {
    this.tipoTransportePublico = tipoTransportePublico;
    //updateTransportePublico();
  }

  public void setLinea(String linea) {
    this.linea = linea;
    //updateTransportePublico();
  }

  public void setParadas(Map<Estacion, DistanciaDouble> paradas) {
    this.paradas = paradas;
    //updateTransportePublico();
  }

  //////////////////////////////////  INTERFACE

  public void darDeAltaParada(Estacion estacion, Double distanciaProx){
    DistanciaDouble distanciaDouble = new DistanciaDouble(distanciaProx);
    this.paradas.put(estacion, distanciaDouble);
    //updateTransportePublico();
  }

  @Override
  public TipoVehiculo getTipoMedio() {
    return null;
  }

  /*

  public void updateTransportePublico(){
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

  public TransportePublico getTransportePublico(int transportePublicoid) {
    EntityManager em = EntityManagerHelper.getEntityManager();
    TransportePublico transportePublico = em.find(TransportePublico.class, new Long(transportePublicoid));
    em.detach(transportePublico);
    return transportePublico;
  }*/

}
