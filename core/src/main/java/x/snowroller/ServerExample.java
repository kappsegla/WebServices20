package x.snowroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            //TCP/IP
            ServerSocket serverSocket = new ServerSocket(5050);
            System.out.println(Thread.currentThread());

            while (true) {
                Socket socket = serverSocket.accept();

                executorService.execute(()-> handleConnection(socket));
//                Thread thread = new Thread(() -> handleConnection(socket));
//                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnection(Socket socket) {
        System.out.println(Thread.currentThread());
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true) {
                String headerLine = input.readLine();
                System.out.println(headerLine);
                if( headerLine.isEmpty())
                    break;
            }
            var output = new PrintWriter(socket.getOutputStream());
            String page = """
                    <html>
                    <head>
                        <title>Hello World!</title>
                    </head>
                    <body>
                    <h1>Hello there</h1>
                    <div>First page</div>
                    </body>                    
                    </html>""";

            output.println("HTTP/1.1 200 OK");
            output.println("Content-Length:" + page.getBytes().length);
            output.println("Content-Type:text/html");
            output.println("");
            output.print(page);

            output.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}