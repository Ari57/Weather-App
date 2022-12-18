package com.example;

import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketClient {
    
    public void client() throws URISyntaxException {
        
        Socket socket = IO.socket("http://localhost:8080");
        socket.emit("message", "message test");

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                System.out.println("Connected to server!");
            }
        });
        socket.on("message", new Emitter.Listener() {
            
            @Override
            public void call(Object... args) {
                Object message = args[0];
                System.out.println(message);
            }
            
        });
        socket.connect();
    }

    public static void main(String[] args) throws URISyntaxException {
        new SocketClient().client();
    }
}