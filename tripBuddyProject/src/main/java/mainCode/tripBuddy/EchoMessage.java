package mainCode.tripBuddy;

import com.google.gson.Gson;

import java.util.Set;

public class EchoMessage {

	private String source;
	private String destination;
	private WeatherInfo sourceWeather;
	private WeatherInfo destinationWeather;
	private double timeZoneDifference;
	private Set<String> locationsSource;
	private Set<String> locationsDestination;
	public EchoMessage() {
	}

	public EchoMessage(String source, String destination, WeatherInfo sourceWeather,WeatherInfo destinationWeather,double timeZoneDifference,Set<String> locationsSource,Set<String> locationsDestination) {
		this.source=source;
		this.destination=destination;
		this.sourceWeather=sourceWeather;
		this.destinationWeather=destinationWeather;
		this.timeZoneDifference=timeZoneDifference;
		this.locationsSource=locationsSource;
		this.locationsDestination=locationsDestination;
	}

	public String getSource() {
		return source;
	}
	public void setdestinationWeather(WeatherInfo destinationWeather)
	{
		this.destinationWeather=destinationWeather;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void sourceWeather(WeatherInfo sourceWeather)
	{
		this.sourceWeather=sourceWeather;
	}
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

// Method to return the object as a JSON string
	public String toJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
