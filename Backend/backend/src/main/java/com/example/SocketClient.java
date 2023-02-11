package com.example;
import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketClient {
    public Object message = "";
    public Socket socket = IO.socket("http://localhost:8080");
    public SocketClient() throws URISyntaxException {}

    public void SendMessage(Object ClientMessage) throws URISyntaxException {
        socket.emit("temperatureMsg", ClientMessage);
    }

    public Object client() throws URISyntaxException, InterruptedException {

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
        public void call(Object... args) {
                System.out.println("Connected to server!");
            }
        });
        socket.on("msg", new Emitter.Listener() {
            
            @Override
            public void call(Object... args) {
                message = args[0];
                System.out.println(message);
            }
            
        });
        socket.connect();
        
        while (message == "") {
            System.out.println("Waiting for message");
            Thread.sleep(3000);
        } 
        return message;
        
    }

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        new SocketClient().client();
    }
}