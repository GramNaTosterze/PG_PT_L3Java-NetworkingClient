package pg.pt;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ManageClient implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int threadNumber;
    public ManageClient(Socket _socket, int _threadNumber) {
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