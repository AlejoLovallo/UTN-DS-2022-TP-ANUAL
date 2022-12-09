package Domain.Controllers;

import com.google.gson.JsonElement;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StandardResponse {

  private StatusResponse status;
  private String message;
  private JsonElement data;

  public StandardResponse(StatusResponse status) {
    this.status = status;
  }
  public StandardResponse(StatusResponse status, String message) {
    this.status = status;
    this.message = message;
  }
  public StandardResponse(StatusResponse status, JsonElement data) {
    this.status = status;
    this.data = data;
  }

  public JSONObject toJson(){
    Map mapADevolver = new HashMap();

    mapADevolver.put("status",this.status.getStatus());
    if (this.data != null)
      mapADevolver.put("data",this.data);
    if (this.message != null)
      mapADevolver.put("message",this.message);

    return new JSONObject(mapADevolver);
  }

  // getters and setters
}