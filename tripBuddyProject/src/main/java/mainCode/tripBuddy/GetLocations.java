package mainCode.tripBuddy;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GetLocations {

    public static Set<String> getUniqueKinds(float latitude, float longitude) {
        // API endpoint URL with latitude and longitude as query parameters
        String apiUrl = "https://api.opentripmap.com/0.1/en/places/radius" +
        "?radius=200000" +
        "&lat=" + latitude +
        "&lon=" + longitude +
        "&apikey=5ae2e3f221c38a28845f05b65974897c94470931dfd90865e9d31630";

        // Create OkHttp client
        OkHttpClient client = new OkHttpClient();

        // Set to store unique kinds
        Set<String> uniqueKinds = new HashSet<>();

        try {
            // Create request object
            Request request = new Request.Builder()
            .url(apiUrl)
            .build();

            // Send the request asynchronously
            Response response = client.newCall(request).execute();

            // Check if request was successful
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }

            // Get the response body as a string
            String responseBody = response.body().string();

            // Parse JSON response
            JSONObject jsonObject = new JSONObject(responseBody);
            JSONArray features = jsonObject.getJSONArray("features");
            // System.out.println(jsonObject);
            // Iterate through features
            for (int i = 0; i < features.length(); i++) {
                JSONObject feature = features.getJSONObject(i);
                JSONObject properties = feature.getJSONObject("properties");

                // Extract kinds from properties
                String kinds = properties.getString("kinds");

                // Split kinds by comma and add to the set
                String[] kindsArray = kinds.split(",");
                for (String kind : kindsArray) {
                    uniqueKinds.add(kind.trim());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return uniqueKinds;
    }
}
