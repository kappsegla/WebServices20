package x.snowroller;

import x.snowroller.fileutils.FileReader;
import x.snowroller.models.Todo;
import x.snowroller.models.Todos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExample {

    public static void main(String[] args) {
        //http://178.174.162.51/
        ExecutorService executorService = Executors.newCachedThreadPool();

        try(ServerSocket serverSocket = new ServerSocket(5050)) {
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
            BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
            var dataOut = new BufferedOutputStream(socket.getOutputStream());

            Request request = readRequest(input);

            Response response = createResponse(request);

            sendResponse(dataOut, response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendResponse(BufferedOutputStream output, Response response) throws IOException {
        String header = "HTTP/1.1 " + response.status.statusCode() + "\r\n";

        if (response.getLength() > 0)
            header += "Content-Type:" + response.contentType + "\r\n";
        header += "Content-Length: " + response.getLength() + "\r\n";
        header += "\r\n";
        output.write(header.getBytes(StandardCharsets.UTF_8));
        output.write(response.content);
        output.flush();
    }

    private static Response createResponse(Request request) throws IOException {
        Response response = new Response();

        if (request.isGetRoute("/todos")) {
            response.setContent(createJsonResponse().getBytes(StandardCharsets.UTF_8));
            response.contentType = "application/json";
        } else if (request.isPostRoute("/todos")) {
            response.contentType = "application/json";
            response.setContent(request.getBody());
        } else if (request.isGetRoute("/urlparams")) {
            var urlParams = request.getUrlParams().entrySet();
            response.contentType = "text/plain";
            String answer = urlParams.toString();
            response.setContent(answer.getBytes(StandardCharsets.UTF_8));
        } else if (request.isGetRoute("/coffee")) {
            response.setStatus(Status.HTTP_TEAPOT);
        } else {
            File file = new File("web" + File.separator + request.getUrl());
            if (file.exists()) {
                response.contentType = Files.probeContentType(file.toPath());
                response.setContent(FileReader.readFromFile(file));
            } else {
                response.setStatus(Status.HTTP_404);
            }
        }
        return response;
    }

    private static Request readRequest(BufferedInputStream input) throws IOException {
        var request = new Request();

        String headerLine = readLine(input);
        if (headerLine.startsWith("GET") || headerLine.startsWith("POST") || headerLine.startsWith("HEAD")) {
            request.setRequestType(headerLine.split(" ")[0]);
            request.setRequestUrl(headerLine.split(" ")[1]);
        }

        while (true) {
            headerLine = readLine(input);
            System.out.println(headerLine);

            if (headerLine.isEmpty())
                break;

            var header = headerLine.split(":");
            request.addHeader(header[0], header[1].trim());
        }

        if (request.getContentLength() > 0) {
            request.setBody(input.readNBytes(request.getContentLength()));
        }
        return request;
    }

    public static String readLine(BufferedInputStream inputStream) throws IOException {
        final int MAX_READ = 4096;
        byte[] buffer = new byte[MAX_READ];
        int bytesRead = 0;
        while (bytesRead < MAX_READ) {
            buffer[bytesRead++] = (byte) inputStream.read();
            if (buffer[bytesRead - 1] == '\r') {
                buffer[bytesRead++] = (byte) inputStream.read();
                if (buffer[bytesRead - 1] == '\n')
                    break;
            }
        }
        return new String(buffer, 0, bytesRead - 2, StandardCharsets.UTF_8);
    }

    private static String createJsonResponse() {
        JsonConverter converter = new JsonConverter();

        var todos = new Todos();
        todos.todos = new ArrayList<>();
        todos.todos.add(new Todo("1", "Todo 1", false));
        todos.todos.add(new Todo("2", "Todo 2", false));

        return converter.convertToJson(todos);
    }
}

