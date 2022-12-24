package com.example;

public class Controller {
    public static void main(String[] args) throws Exception {
    
    SocketClient client = new SocketClient(); 
    client.client(); // connect to NodeJS Server

    Object result[] = Geocoding.geoCode(); // convert location to long and lat
    
    String WeatherKey = APIKeys.WeatherApiKey;
    Object lng = result[0]; // long
    Object lat = result[1]; // lat
    WeatherRequest.getRequest("https://api.openweathermap.org/data/3.0/onecall?lat="+lat+"&lon="+lng+"&appid="+WeatherKey+"&units=metric"); // get temp
   
    }
    
}
