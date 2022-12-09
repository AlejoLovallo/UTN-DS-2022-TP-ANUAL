package Domain.Controllers;

public enum StatusResponse {
  SUCCESS ("Success"),
  ERROR ("Error");

  private String status;
  // constructors, getters

  StatusResponse(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}