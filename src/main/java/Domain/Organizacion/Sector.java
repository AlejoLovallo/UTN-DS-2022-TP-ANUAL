package Domain.Organizacion;

import Domain.Espacios.Espacio;

public class Sector {

  private String nombre;
  private Espacio espacioDeTrabajo;
  private Organizacion organizacion;

///Constructor
  public Sector(String _nombre,Espacio _espacioDeTrabajo) {
    this.setNombre(_nombre);
    this.setEspacioDeTrabajo(_espacioDeTrabajo);
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Espacio getEspacioDeTrabajo() {
    return espacioDeTrabajo;
  }

  public void setEspacioDeTrabajo(Espacio espacioDeTrabajo) {
    this.espacioDeTrabajo = espacioDeTrabajo;
  }

  public Organizacion getOrganizacion() {
    return organizacion;
  }

  public void setOrganizacion(Organizacion organizacion) {
    this.organizacion = organizacion;
  }
}