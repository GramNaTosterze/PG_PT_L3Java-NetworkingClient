package pg.pt;

import java.io.IOException;

public class StartClient {
    public static void main(String[] args) {
        try {
            new Client();
        } catch (IOException e) {
            System.out.println("problem connecting to server");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}