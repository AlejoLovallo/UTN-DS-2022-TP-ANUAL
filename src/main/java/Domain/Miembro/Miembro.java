package Domain.Miembro;
import Domain.CalculadorHC.CalculadorHC;
import Domain.Miembro.Excepciones.MiembroNoPerteneceAOrganizacionException;
import Domain.Miembro.Excepciones.UnicoSectorPorOrganizacionException;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Trayecto.Trayecto;

import java.io.IOException;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name="miembro")
public class Miembro {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  @Transient
  private String id;

  @ManyToOne
  @JoinColumn(name = "id_sector", referencedColumnName = "id")
  private Sector sector;

  // FALTA DEFINIR LA PARTICION DE CLASE
  private ArrayList<Trayecto> trayectos;

  @Transient
  private CalculadorHC calculadorHC = CalculadorHC.getInstance();


  //////////////////////////////////  CONSTRUCTOR
  public Miembro(){

  }

  public Miembro(String _id, Sector _sector){
    this.id = _id;
    this.sector = _sector;
  }

  //////////////////////////////////  GETTERS

  public ArrayList<Trayecto> getTrayectos() {
    return trayectos;
  }

  public Sector getSector() { return sector; }
  //////////////////////////////////  SETTERS
  public void setTrayectos(ArrayList<Trayecto> trayectos) {
    this.trayectos = trayectos;
  }

  //////////////////////////////////  INTERFACE

  public void vincularSector(Sector _sector){
    if (this.sector != null)
      throw new UnicoSectorPorOrganizacionException();
    _sector.getOrganizacion().aceptarVinculacion(this, _sector);
    this.sector = _sector;
  }

  public void registrarTrayectos(Organizacion organizacion, ArrayList<Trayecto> trayectos){
    if (!this.sector.getOrganizacion().equals(organizacion))
       throw new MiembroNoPerteneceAOrganizacionException(organizacion.getRazonSocial());
    this.setTrayectos(trayectos);
  }

  public void registrarTrayecto(Organizacion organizacion, Trayecto trayecto){

    if (!this.sector.getOrganizacion().equals(organizacion))
       throw new MiembroNoPerteneceAOrganizacionException(organizacion.getRazonSocial());

    double frecuenciaTotal= 0.0;

    for (Trayecto tray : this.getTrayectos()){
      frecuenciaTotal += tray.getFrecuenciaSemanal();
    }
    frecuenciaTotal += trayecto.getFrecuenciaSemanal();
    if (frecuenciaTotal < 1)
      this.trayectos.add(trayecto);
  }

  //TODO hacer el calculo de un miembro
  public Double calcularHC(Integer mes, Integer anio) throws IOException {
    return calculadorHC.calcularHC(this, mes, anio);
  }

  public void agregarTrayecto(Trayecto trayecto){
        trayectos.add(trayecto);
  }

  public void desvincularTrayecto(Trayecto trayecto){
    this.trayectos.remove(trayecto);
  }

}
