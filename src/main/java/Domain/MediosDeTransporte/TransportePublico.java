package Domain.MediosDeTransporte;

import java.util.List;

import Domain.BaseDeDatos.EntityManagerHelper;
import Domain.Espacios.Estacion;
import javax.persistence.*;

@Entity
@Table(name="transportepublico")
public class TransportePublico extends MedioDeTransporte {

  @Enumerated(EnumType.STRING)
  private TipoTransportePublico tipoTransportePublico;
  @Column
  private String linea;

  @Transient
  public List<Estacion> paradas;

  //////////////////////////////////  CONSTRUCTOR

  private TransportePublico(){

  }

  public TransportePublico(TipoTransportePublico tipoTransportePublico, String linea, List<Estacion> paradas) {
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

  public List<Estacion> getParadas() {
    return this.paradas;
  }

  //////////////////////////////////  SETTERS


  public void setTipoTransportePublico(TipoTransportePublico tipoTransportePublico) {
    this.tipoTransportePublico = tipoTransportePublico;
    updateTransportePublico();
  }

  public void setLinea(String linea) {
    this.linea = linea;
    updateTransportePublico();
  }

  public void setParadas(List<Estacion> paradas) {
    this.paradas = paradas;
    updateTransportePublico();
  }

  //////////////////////////////////  INTERFACE
  public void darDeAltaParada(Estacion estacion){
    this.paradas.add(estacion.getNumeroDeEstacion(),estacion);
    updateTransportePublico();
  }

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
  }

}
