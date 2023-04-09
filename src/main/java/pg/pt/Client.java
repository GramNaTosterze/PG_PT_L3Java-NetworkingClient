package pg.pt;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

public class Client {
    private InetAddress host;
    private Socket socket;
    private ObjectOutputStream oos;

    private int port;
    private boolean running;
    public Client() throws IOException {
        host = InetAddress.getLocalHost();
        running = true;
        port = 8080;
        socket = new Socket(host.getHostName(), port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        while (running) {
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            System.out.println("Sending to server: " + msg);
            // send to server
            oos.writeObject(msg);
            if (msg.equals("exit"))
                StopClient();
        }
        oos.close();
    }
    private void StopClient() {
        running = false;
    }
}
