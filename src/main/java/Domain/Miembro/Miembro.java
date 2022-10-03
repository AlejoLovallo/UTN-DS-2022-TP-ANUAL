package Domain.Miembro;
import Domain.CalculadorHC.CalculadorHC;
import Domain.Miembro.Excepciones.MiembroNoPerteneceAOrganizacionException;
import Domain.Miembro.Excepciones.UnicoSectorPorOrganizacionException;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.Sector;
import Domain.Trayecto.Trayecto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="miembro")
public class Miembro {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_miembro;

  @Transient
  //@Column(name = "rol")
  private String nombre;

  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name = "id_sector", referencedColumnName = "id_sector")
  private Sector sector;

  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
  private Persona persona;

  // FALTA DEFINIR LA PARTICION DE CLASE
  @OneToMany(cascade=CascadeType.ALL , mappedBy = "miembro")
  private List<Trayecto> trayectos;

  @Transient
  private CalculadorHC calculadorHC = CalculadorHC.getInstance();


  //////////////////////////////////  CONSTRUCTOR
  private Miembro(){

  }

  public Miembro(String nombre, Sector _sector){
    this.nombre = nombre;
    this.sector = _sector;
  }

  public Miembro(Sector _sector){
    this.sector = _sector;
  }

  //////////////////////////////////  GETTERS

  public List<Trayecto> getTrayectos() {
    return trayectos;
  }

  public Sector getSector() { return sector; }
  //////////////////////////////////  SETTERS


  public void setPersona(Persona persona) {
    this.persona = persona;
  }


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


  public static String toString(Miembro miembro) {
    if(miembro == null) return "el miembro es nulo";

    return "Miembro{" +
        "id_miembro=" + miembro.id_miembro +
        ", nombre='" + miembro.nombre + '\'' +
        ", sector=" + miembro.sector +
        ", persona=" + miembro.persona +
        ", trayectos=" + miembro.trayectos +
        ", calculadorHC=" + miembro.calculadorHC +
        '}';
  }

  public static String toString(List<Miembro> miembros) {
    if(miembros == null) return "los miembros son nulo";

    for (Miembro mi : miembros) {
     return Miembro.toString(mi);
    }

    return "default";
  }
}
