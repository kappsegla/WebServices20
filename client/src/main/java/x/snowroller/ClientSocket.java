package x.snowroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {


    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 5050);

            var output = new PrintWriter(socket.getOutputStream());
            output.println("Hello from client");
            output.flush();
            var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(input.readLine());

            output.close();
            socket.close();

            //Do other things...

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



