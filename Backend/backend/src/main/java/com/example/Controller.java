package com.example;
import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Controller {

    public Socket socket = IO.socket("http://localhost:8080");
    public Controller() throws URISyntaxException {}

    public void client() throws URISyntaxException, InterruptedException {

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
        public void call(Object... args) {
            try {
                System.out.println("Controller running");
                new Controller().Runner();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            }
        });
        socket.connect();
        
    }
    
    public void Runner() throws Exception {
    
        SocketClient client = new SocketClient(); 
    client.client(); // connect to NodeJS Server

    Object result[] = Geocoding.geoCode(client.message); // convert location to long and lat
    
    String WeatherKey = APIKeys.WeatherApiKey;
    Object lng = result[0]; // long
    Object lat = result[1]; // lat
    WeatherRequest.getRequest("https://api.openweathermap.org/data/3.0/onecall?lat="+lat+"&lon="+lng+"&appid="+WeatherKey+"&units=metric"); // get temp

    }

    public static void main(String[] args) throws Exception {
        new Controller().client();
        
    }
    
}

