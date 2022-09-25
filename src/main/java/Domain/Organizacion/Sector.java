package Domain.Organizacion;

import Domain.CalculadorHC.CalculadorHC;
import Domain.Espacios.Espacio;
import Domain.Miembro.Miembro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="sector")
public class Sector {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_sector;
  @Column
  private String nombre;
  @ManyToOne
  @JoinColumn(name = "id_espacio",referencedColumnName = "id_espacio")
  private Espacio espacioDeTrabajo;
  @ManyToOne
  @JoinColumn(name = "id_organizacion", referencedColumnName = "id_organizacion")
  private Organizacion organizacion;

  @OneToMany(mappedBy = "sector")
  private List<Miembro> miembros = new ArrayList<>();

  @Transient
  private CalculadorHC calculadorHC;

 // CONSTRUCTOR
  public Sector(){

  }

  public Sector(String nombre, Espacio espacioDeTrabajo, Organizacion organizacion, ArrayList<Miembro> miembros) {
    this.nombre = nombre;
    this.espacioDeTrabajo = espacioDeTrabajo;
    this.organizacion = organizacion;
    this.miembros = miembros;
  }

  // GETTERS

  public String getNombre() {
    return nombre;
  }

  public Espacio getEspacioDeTrabajo() {
    return espacioDeTrabajo;
  }

  public Organizacion getOrganizacion() {
    return organizacion;
  }

  public List<Miembro> getMiembros() {
    return miembros;
  }


  //SETTERS


  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setEspacioDeTrabajo(Espacio espacioDeTrabajo) {
    this.espacioDeTrabajo = espacioDeTrabajo;
  }

  public void setOrganizacion(Organizacion organizacion) {
    this.organizacion = organizacion;
  }

  public void setMiembros(ArrayList<Miembro> miembros) {
    this.miembros = miembros;
  }

  // METHODS

  public Double calcularHCPromedioSector() throws IOException {
    return calculadorHC.calcularHC(this)/this.getMiembros().size();
  }
}