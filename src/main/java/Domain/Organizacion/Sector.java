package Domain.Organizacion;

import Domain.Espacios.Espacio;

public class Sector {
  private String nombre;
  private Espacio espacioDeTrabajo;

///Constructor
  public Sector(String _nombre,Espacio _espacioDeTrabajo) {
    this.nombre=_nombre;
    this.espacioDeTrabajo=_espacioDeTrabajo;
  }
}