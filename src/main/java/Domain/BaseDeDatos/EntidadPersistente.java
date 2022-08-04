package Domain.BaseDeDatos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

public class EntidadPersistente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  public int getId() {
    return id;
  }
}
