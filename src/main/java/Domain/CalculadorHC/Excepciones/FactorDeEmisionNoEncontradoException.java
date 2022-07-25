package Domain.CalculadorHC.Excepciones;

import Domain.CalculadorHC.FactorEmision;

public class FactorDeEmisionNoEncontradoException extends RuntimeException {
  public FactorDeEmisionNoEncontradoException(FactorEmision factor){
    super("Factor de emision no encontrado " + factor);
  }
}
