package Domain.Organizacion;

import Domain.Espacios.Espacio;

public class Sector {
  private Espacio espacioDeTrabajo;
  public Espacio getEspacioDeTrabajo() {
    return espacioDeTrabajo;
  }

  public void setEspacioDeTrabajo(Espacio espacioDeTrabajo) {
    this.espacioDeTrabajo = espacioDeTrabajo;
  }
}
