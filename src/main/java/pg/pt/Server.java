package pg.pt;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server() throws IOException {
        ServerSocket server = new ServerSocket(8080);


        int threadNumber = 0;
        try {
            while(true) {
                Socket socket = server.accept();
                Thread manClient = new Thread(new ManageClient(socket, threadNumber++));
                manClient.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Shutting down server");
        //close the ServerSocket object
        server.close();
    }
}
