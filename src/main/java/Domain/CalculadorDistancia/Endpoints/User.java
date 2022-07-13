package Domain.CalculadorDistancia.Endpoints;

public class User {
  public String email;
  public User(String _email){
    email = _email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
