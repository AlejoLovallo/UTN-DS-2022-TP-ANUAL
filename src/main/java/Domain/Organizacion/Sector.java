package Domain.Organizacion;

import Domain.CalculadorHC.CalculadorHC;
import Domain.Espacios.Espacio;
import Domain.Miembro.Miembro;

import java.io.IOException;
import java.util.ArrayList;

public class Sector {

  private String nombre;
  private Espacio espacioDeTrabajo;
  private Organizacion organizacion;
  private ArrayList<Miembro> miembros = new ArrayList<>();

  private CalculadorHC calculadorHC;

 // CONSTRUCTOR


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

  public ArrayList<Miembro> getMiembros() {
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