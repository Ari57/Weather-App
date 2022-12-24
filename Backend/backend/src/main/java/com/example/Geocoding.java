package com.example;

import java.net.URISyntaxException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Geocoding {
    public static Object[] geoCode() throws URISyntaxException, Exception {

    SocketClient client = new SocketClient();
    client.client();
    
    String AppID = APIKeys.GeocodingApiKey;
    String urlToRead = "https://maps.googleapis.com/maps/api/geocode/json?key="+AppID+"&address="+client.message;

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
        JSONArray results = (JSONArray) jsonObject.get("results");
        JSONObject overallArray = (JSONObject) results.get(0);
        JSONObject geometry = (JSONObject) overallArray.get("geometry");
        JSONObject location = (JSONObject) geometry.get("location");
        Object lng = location.get("lng");
        Object lat = location.get("lat");
        return new Object[] {lng, lat};
    }

    public static void main(String[] args) throws URISyntaxException, Exception {
        Object result[] = geoCode();
        System.out.println(result[0]); // long
        System.out.println(result[1]); // lat

    }
    
}