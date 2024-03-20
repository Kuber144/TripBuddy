package mainCode.tripBuddy;


public class WeatherInfo {
    private String location;
    private double timezone;
    private String time;
    private double temperature;
    private String description;
    private double humidity;
    private double windSpeed;
    private double windDirection;
    private float lat;
    private float lon;

    public WeatherInfo() {
    }

    public WeatherInfo(String location, double timezone, String time, double temperature, String description, double humidity, double windSpeed, double windDirection,float lat,float lon) {
        this.location = location;
        this.timezone = timezone;
        this.time = time;
        this.temperature = temperature;
        this.description = description;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.lat=lat;
        this.lon=lon;
    }

    public float getlon()
    {
        return lon;
    }
    public float getlat()
    {
        return lat;
    }
    public void setlon(float lon){
        this.lon=lon;
    }
    public void setlat(float lat){
        this.lat=lat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTimezone() {
        return timezone;
    }

    public void setTimezone(double timezone) {
        this.timezone = timezone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }
}
