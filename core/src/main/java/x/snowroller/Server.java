package x.snowroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //https://dzone.com/articles/simple-http-server-in-java
        //https://netty.io/
        int portNumber = 4004;

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println("Hello World");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}