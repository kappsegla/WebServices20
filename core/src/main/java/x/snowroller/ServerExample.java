package x.snowroller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExample {

    public static void main(String[] args) {

//        File file = new File("web\\index.html");
//        new ServerExample().readFromFile(file);

        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            //TCP/IP
            ServerSocket serverSocket = new ServerSocket(5050);
            System.out.println(Thread.currentThread());

            while (true) {
                Socket socket = serverSocket.accept();

                executorService.execute(() -> handleConnection(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnection(Socket socket) {
        System.out.println(Thread.currentThread());
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String headerLine = input.readLine();
                System.out.println(headerLine);
                if (headerLine.isEmpty())
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
            output.println("Content-Type:text/html");  //application/json
            output.println("");
            output.print(page);

            output.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createJsonResponse() {
        var todos = new Todos();
        todos.todos = new ArrayList<>();
        todos.todos.add(new Todo(1, "Todo 1", false));
        todos.todos.add(new Todo(2, "Todo 2", false));

        JsonConverter converter = new JsonConverter();

        var json = converter.convertToJson(todos);
        System.out.println(json);
    }



    private byte[] readFromFile(File file) {
        byte[] content = new byte[0];
        System.out.println("Does file exists: " + file.exists());
        if (file.exists() && file.canRead()) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                content = new byte[(int)file.length()];
                int count = fileInputStream.read(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}

class Todo {
    int id;
    String title;
    boolean completed;

    public Todo(int id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}

class Todos {
    List<Todo> todos;
}