package com.example;

public class Controller {

    public static void main(String[] args) throws Exception{
        SocketClient client = new SocketClient();
        client.client();

        Object result[] = Geocoding.geoCode(client.message);

        String WeatherKey = APIKeys.WeatherApiKey;
        Object lng = result[0]; // long
        Object lat = result[1]; // lat

        Object temperature = WeatherRequest.getRequest("https://api.openweathermap.org/data/3.0/onecall?lat="+lat+"&lon="+lng+"&appid="+WeatherKey+"&units=metric"); // get temp

        client.SendMessage(temperature);

    }
}