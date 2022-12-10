package com.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer 
{
    private ServerSocket server;

    public void listen() {
        try {
            server = new ServerSocket(8080);
        } catch (IOException e ) {
            System.out.println("Coluld not listen on port 8080");
            System.exit(-1);
        }
        while (true) {
            try {
                System.out.println("Waiting for connection");
                final Socket socket = server.accept();

                final InputStream inputStream = socket.getInputStream();
                final InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(streamReader);

                String line = null;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                
            } catch (IOException e) {
                    System.out.println("Accept failed: 8080");
                    System.exit(-1);
            } 
        }
    }

    public static void main(String[] args) {
        new SocketServer().listen();
    }

}
