package mainCode.tripBuddy;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("web")
public class EchoWebform {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getEchoForm(
        @DefaultValue("") @QueryParam("sourceCity") String source,
        @DefaultValue("") @QueryParam("destinationCity") String destination) {

        if (source.isEmpty() || destination.isEmpty()) {
            // Display the form if source or destination is not provided
            return "<html><body><h2>Welcome to TripBuddy</h2>" +
            "<form action=\"web\" method=\"get\">" +
            "Enter Source City: <input type=\"text\" name=\"sourceCity\"><br>" +
            "Enter Destination City: <input type=\"text\" name=\"destinationCity\"><br>" +
            "<input type=\"submit\" value=\"Submit\">" +
            "</form></body></html>";
        } else {
            try {
                // Case when input param is passed. Call REST service.
                Client c = ClientBuilder.newClient();
                WebTarget target = c.target(EchoWebformLauncher.restBaseUri);
                String jsonResponse = target.path("echo")
                .queryParam("sourceCity", source)
                .queryParam("destinationCity", destination)
                .request()
                .get(String.class);

                // Parse JSON response manually
                JSONObject response = new JSONObject(jsonResponse);
                JSONObject sourceWeather = response.getJSONObject("sourceWeather");
                JSONObject destinationWeather = response.getJSONObject("destinationWeather");
                double timeZoneDifference = response.getDouble("timeZoneDifference");
                JSONArray locationsSource = response.getJSONArray("locationsSource");
                JSONArray locationsDestination = response.getJSONArray("locationsDestination");

                // Construct HTML response
                StringBuilder htmlResponse = new StringBuilder("<html><body>");
                htmlResponse.append("<h2>Weather Details</h2>");
                htmlResponse.append("<h3>Source: ").append(source).append("</h3>");
                htmlResponse.append(formatWeatherInfo(sourceWeather));
                htmlResponse.append("<h3>Destination: ").append(destination).append("</h3>");
                htmlResponse.append(formatWeatherInfo(destinationWeather));

                htmlResponse.append("<h2>Timezone Difference</h2>");
                htmlResponse.append("<p>Timezone difference between ").append(source)
                .append(" and ").append(destination).append(": ")
                .append(timeZoneDifference).append(" hours</p>");

                htmlResponse.append("<h2>Points of Interest (POIs)</h2>");
                htmlResponse.append("<h3>Source: ").append(source).append("</h3>");
                htmlResponse.append(formatPOIs(locationsSource));
                htmlResponse.append("<h3>Destination: ").append(destination).append("</h3>");
                htmlResponse.append(formatPOIs(locationsDestination));

                htmlResponse.append("</body></html>");
                return htmlResponse.toString();
            } catch (Exception e) {
                // If an error occurs, display an error page
                return "<html><body><h2>Error</h2><p>An error occurred: " + e.getMessage() + "</p></body></html>";
            }
        }
    }

    private String formatWeatherInfo(JSONObject weatherInfo) {
        StringBuilder formattedWeather = new StringBuilder();
        formattedWeather.append("<table border=\"1\">");
        formattedWeather.append("<tr><th>Location</th><td>").append(weatherInfo.getString("location")).append("</td></tr>");
        formattedWeather.append("<tr><th>Time</th><td>").append(weatherInfo.getString("time")).append("</td></tr>");
        formattedWeather.append("<tr><th>Temperature</th><td>").append(weatherInfo.getDouble("temperature")).append(" degree celsius </td></tr>");
        formattedWeather.append("<tr><th>Description</th><td>").append(weatherInfo.getString("description")).append("</td></tr>");
        formattedWeather.append("<tr><th>Humidity</th><td>").append(weatherInfo.getDouble("humidity")).append(" percent </td></tr>");
        formattedWeather.append("<tr><th>Wind Speed</th><td>").append(weatherInfo.getDouble("windSpeed")).append(" km/h </td></tr>");
        formattedWeather.append("<tr><th>Wind Direction</th><td>").append(weatherInfo.getDouble("windDirection")).append(" degrees </td></tr>");
        formattedWeather.append("</table>");
        return formattedWeather.toString();
    }

    private String formatPOIs(JSONArray pois) {
        StringBuilder formattedPOIs = new StringBuilder();
        formattedPOIs.append("<ul>");
        for (int i = 0; i < pois.length(); i++) {
            formattedPOIs.append("<li>").append(pois.getString(i)).append("</li>");
        }
        formattedPOIs.append("</ul>");
        return formattedPOIs.toString();
    }
}
