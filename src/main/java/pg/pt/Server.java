package pg.pt;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public Server() throws IOException {
        ServerSocket server = new ServerSocket(8080);

        int threadNumber = 0;
        try {
            while(true) {
                Socket socket = server.accept();
                Thread manClient = new Thread(new ManClient(socket, threadNumber++));
                manClient.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        System.out.println("Shutting down server");
        //close the ServerSocket object
        server.close();
    }
    class ManClient implements Runnable {
        private Socket socket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private int threadNumber;
        public ManClient(Socket _socket, int _threadNumber) {
            try {
                socket = _socket;
                out = new ObjectOutputStream(_socket.getOutputStream());
                in   = new ObjectInputStream( _socket.getInputStream());
                threadNumber = _threadNumber;
            } catch (IOException e) {
                System.out.println("Cannot create streams");
            }
        }
        @Override
        public void run() {
            System.out.printf("Client %d connected\n", threadNumber);
            try {
                //send ready
                System.out.printf("Client %d - ready\n", threadNumber);
                out.writeObject("ready");
                //wait for resp
                int n = (Integer) in.readObject();
                System.out.printf("%d Num: %d\n", threadNumber, n);
                //send ready for msg
                out.writeObject("ready for message");
                System.out.printf("Client %d - ready for msg\n", threadNumber);

                //wait for resp
                for (int i = 0; i < n; i++) {
                    Message msg = (Message) in.readObject();
                    System.out.printf("Client %d - %s\n", threadNumber, msg);
                }
                //send finished
                System.out.printf("Client %d - finished\n", threadNumber);
                out.writeObject("finished");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
