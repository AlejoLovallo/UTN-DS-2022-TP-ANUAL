package Domain.Espacios;

import javax.persistence.*;

@Entity
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
public abstract class Espacio {
  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY) //ANTERIOR
  @GeneratedValue(strategy = GenerationType.TABLE) //POR HERENCIA
  private int id_espacio;
}
