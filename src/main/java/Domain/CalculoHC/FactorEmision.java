package Domain.CalculoHC;

/*
* -emisionCombustionFija: int
-emisionCombustionMovil: int
-emisionElectricidad: int
-emisionLogistica: int
*
* */

public class FactorEmision {
  private static FactorEmision instance = null;

  private int emisionCombustionFija;
  private int emisionCombustionMovil;
  private int emisionElectricidad;
  private int emisionLogistica;

  private FactorEmision(){}

  public static FactorEmision getInstance(){
    if(instance == null){
      instance = new FactorEmision();
    }
    return instance;
  }

  public int getEmisionCombustionFija() {
    return emisionCombustionFija;
  }

  public void setEmisionCombustionFija(int emisionCombustionFija) {
    this.emisionCombustionFija = emisionCombustionFija;
  }

  public int getEmisionCombustionMovil() {
    return emisionCombustionMovil;
  }

  public void setEmisionCombustionMovil(int emisionCombustionMovil) {
    this.emisionCombustionMovil = emisionCombustionMovil;
  }

  public int getEmisionElectricidad() {
    return emisionElectricidad;
  }

  public void setEmisionElectricidad(int emisionElectricidad) {
    this.emisionElectricidad = emisionElectricidad;
  }

  public int getEmisionLogistica() {
    return emisionLogistica;
  }

  public void setEmisionLogistica(int emisionLogistica) {
    this.emisionLogistica = emisionLogistica;
  }
}
