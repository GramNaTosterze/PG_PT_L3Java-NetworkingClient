package pg.pt;

import java.io.IOException;

public class StartServer {
    public static void main(String[] args) throws ClassNotFoundException{
        try {
            new Server();
        } catch (IOException e) {
            System.out.println("Cannot connect to client");
        }
    }
}