import Domain.Base;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

public class BaseTest {
  protected Base baseField;

  @BeforeEach
  public void initialize() {
    this.initializeBase();
  }

  private void initializeBase(){
    this.baseField = new Base();
    this.baseField.setCampo_base("BASE VALUE");
  }

  @Test
  public void llegan4PasajerosAMadridEl20DeMayo() {
    Assertions.assertEquals("BASE VALUE", this.baseField.getCampo_base());
  }

}