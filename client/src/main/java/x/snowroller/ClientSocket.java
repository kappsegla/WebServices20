package x.snowroller;

import java.io.*;
import java.net.Socket;

public class ClientSocket {


    public static void main(String[] args) {

        try {
            Socket socket = new Socket("178.174.162.51", 5050);

            var output = new PrintWriter(socket.getOutputStream());
            output.println("Hello from client");
            output.flush();
            var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(input.readLine());

            output.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



