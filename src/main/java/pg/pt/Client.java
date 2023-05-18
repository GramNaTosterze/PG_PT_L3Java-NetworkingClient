package pg.pt;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;
import java.util.Scanner;

public class Client {
    private InetAddress host;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private int port;
    private boolean running;
    public Client() throws IOException, ClassNotFoundException {
        host = InetAddress.getLocalHost();
        port = 8080;
        socket = new Socket(host.getHostName(), port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in  = new ObjectInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);
        //wait for ready
        String responce = (String) in.readObject();
        System.out.println(responce);
        int n = scanner.nextInt();
        out.writeObject(n);

        //wait for ready for msg
        responce = (String) in.readObject();
        System.out.println(responce);
        //send msg
        scanner.nextLine(); // skip eol
        for (int i = 0; i < n; i++) {
            Message msg = new Message(i, scanner.nextLine());
            out.writeObject(msg);
        }

        //wait for finish
        responce = (String) in.readObject();
        System.out.println(responce);
        System.out.println("Closing Client...");

    }
    private void StopClient() {
        running = false;
    }
}
