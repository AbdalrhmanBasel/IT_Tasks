import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;



public class Client {
    public static void main(String[] args) {

        // Sockets Declaration
        final Socket clientSocket; // Socket is endpoint between two machines
        final BufferedReader in; // Reads Input
        final PrintWriter out; // Prints output
        final Scanner input = new Scanner(System.in);

        try { // If no errors, try this code
            clientSocket = new Socket("127.0.0.1",50002);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Starting Sender Thread
            Thread sender = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    while(true) {
                        msg = input.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }
            });
            sender.start();

            // Starting Receiving Thread
            Thread receiver = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    try {
                        msg = in.readLine();

                        while(msg != null) {
                            System.out.println("Server: " + msg);
                            msg = in.readLine();
                        }

                        // Closing Socket
                        System.out.println("Server out of service!");
                        out.close();
                        clientSocket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiver .start();


        } catch (IOException e) { // If there is error, catch error here
            e.printStackTrace();
        }
    }
}
