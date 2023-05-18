package pg.pt;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

public class Client {
    public Client() {

        try {
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = new Socket(host.getHostName(), 8080);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in   = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            //wait for ready
            String response = (String) in.readObject();
            System.out.println(response);
            int n = scanner.nextInt();
            out.writeObject(n);

            //wait for ready for msg
            response = (String) in.readObject();
            System.out.println(response);
            //send msg
            scanner.nextLine(); // skip eol
            for (int i = 0; i < n; i++) {
                Message msg = new Message(i, scanner.nextLine());
                out.writeObject(msg);
            }

            //wait for finish
            response = (String) in.readObject();
            System.out.println(response);
            System.out.println("Closing Client...");

            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("Cannot connect to server");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
