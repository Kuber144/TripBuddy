package mainCode.tripBuddy;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("echo")
public class EchoService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String echoMessage(
        @QueryParam("sourceCity") String sourceCity,
        @QueryParam("destinationCity") String destinationCity) {
        try {
        // Fetch weather information for source and destination cities
            System.out.println("Backend api get call invoked");
            System.out.println("Fetching weather details for source and destination");
            WeatherInfo sourceWeather = WeatherService.getWeatherInfo(sourceCity);
            WeatherInfo destinationWeather = WeatherService.getWeatherInfo(destinationCity);
            System.out.println("Weather fetched");
        // Calculate the time zone difference between both
            System.out.println("Calculating time zone difference");
            double timeZoneDifference = Math.abs(sourceWeather.getTimezone()-destinationWeather.getTimezone());
            System.out.println("Time zone difference caluclated");
        //Fetch the interesting activities for both

            System.out.println("Fetching interesting activities for the source and destination");
            Set<String> interestingActivitiesSource = GetLocations.getUniqueKinds(sourceWeather.getlat(),sourceWeather.getlon());
            Set<String> interestingActivitiesDestination= GetLocations.getUniqueKinds(destinationWeather.getlat(),destinationWeather.getlon());

            System.out.println("All details fetched creating a json and sending response");
        // Create and return EchoMessage object with trip details
            EchoMessage echoMessage= new EchoMessage(sourceCity,destinationCity,sourceWeather,destinationWeather,timeZoneDifference,interestingActivitiesSource,interestingActivitiesDestination);
            return echoMessage.toJsonString();
        } catch (Exception e) {
        // Handle the exception, e.g., log it or return an error message
            System.out.println("An error ocurred: "+e.getMessage());
            e.printStackTrace();
            return "An error occurred. Please try again with the correct details";
        }
    }
}
