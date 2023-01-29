package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class WeatherRequest {
    
    public static Object getRequest(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null;) {
                result.append(line);
            }
        } 

        JSONObject jsonObject = (JSONObject) JSONValue.parse(result.toString());
        JSONObject results = (JSONObject) jsonObject.get("current");
        Object temperature = results.get("temp");
        System.out.println(temperature);
        return temperature;
        // System.out.println(results);
    }

    public static void main(String[] args) throws Exception {
        SocketClient client = new SocketClient();
        client.client();
        
        Object result[] = Geocoding.geoCode(client.message);
        String WeatherKey = APIKeys.WeatherApiKey;
        Object lng = result[0]; // long
        Object lat = result[1]; // lat
        getRequest("https://api.openweathermap.org/data/3.0/onecall?lat="+lat+"&lon="+lng+"&appid="+WeatherKey+"&units=metric");
        
    }
    
}
