package com.example;

import java.io.*;
import java.net.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class WeatherRequest {
    public static void getRequest(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null;) {
                result.append(line);
            }
        }
        //JSONObject jsonObj = new JSONObject(result.toString());
        JSONObject jsonObject = (JSONObject) JSONValue.parse(result.toString());
        //return result.toString();
        //System.out.println(jsonObject.get("alerts"));
        Object results = jsonObject.get("current");
        System.out.println(results);
    }

    public static void main(String[] args) throws Exception {
        getRequest("https://api.openweathermap.org/data/3.0/onecall?lat=51.5072&lon=0.1276&appid={AppID}&units=metric");
    }
    
}
