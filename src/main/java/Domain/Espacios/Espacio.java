package Domain.Espacios;

import javax.persistence.*;

@Entity
@Table(name="espacio" /*, catalog = "curso"*/)
@Inheritance(strategy=InheritanceType.JOINED)
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Espacio {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) //ANTERIOR
  //@GeneratedValue(strategy = GenerationType.TABLE) //POR HERENCIA
  //@GeneratedValue
  //@Column(name = "id_espacio", unique = true, nullable = false)
  private int id_espacio;
}
