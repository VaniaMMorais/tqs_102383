package lab2_2;

import java.util.Locale;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;

public class AddressResolver {
    private ISimpleHttpClient httpClient;

    public AddressResolver(ISimpleHttpClient httpClient) {
      this.httpClient = httpClient;
    }

    public Optional<Address> findAddressForLocation (double latitude, double longitude) {
      // -90 < latitude < 90
      // -180 < longitude < 180
      if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) 
        return Optional.empty();

      String url = "http://open.mapquestapi.com/geocoding/v1/reverse?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&location=40.6318,-8.658&includeRoadMetadata=true";
      
      String json = httpClient.doHttpGet(String.format(Locale.getDefault(), url, latitude, longitude));
      
      JSONObject data;
      String road = "";
      String state = "";
      String cirty = "";
      String zio = "";
      String houseNumber = "";

      try {
        data = new JSONObject(json).getJSONArray("results").getJSONObject(0).getJSONArray("locations").getJSONObject(0);
  
        road = data.getString("street".toString());
        state = data.getString("adminArea3".toString());
        cirty = data.getString("adminArea5".toString());
        zio = data.getString("postalCode".toString());
        houseNumber = data.getString("adminArea6".toString());

      } catch (JSONException e) {
        System.err.println(e);
        System.exit(-1);
      }

      return Optional.of(new Address(road, state, cirty, zio, houseNumber));
    }
}
