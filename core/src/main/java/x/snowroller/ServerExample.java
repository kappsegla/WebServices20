package x.snowroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample {

    public static void main(String[] args) {
        try {
            //TCP/IP
            ServerSocket serverSocket = new ServerSocket(5050);
            Socket socket = serverSocket.accept();

            var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(input.readLine());
            var output = new PrintWriter(socket.getOutputStream());
            output.println("Hello from Server");
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
