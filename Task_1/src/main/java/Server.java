// Sockets
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {
    public static void main(String[] args) {

        // Sockets Declaration
        final ServerSocket serverSocket; // ServerSocket is a server socket that waits for request from client socket
        final Socket clientSocket; // Endpoint between two machines.
        final BufferedReader in; // Reads Input
        final PrintWriter out; // Reads Output
        final Scanner input = new Scanner(System.in);


        try {
            serverSocket = new ServerSocket(50002); // Defining Port
            clientSocket = serverSocket.accept(); // Accepts connection
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
                        out.flush(); //
                    }
                }
            });
            sender.start();

            // Starting Receiving Thread
            Thread receive = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    try {
                        msg = in.readLine();

                        while(msg != null) {
                            System.out.println("Client: " + msg);
                            msg = in.readLine();
                        }

                        // Closing Socket
                        System.out.println("Client Disconnected");
                        out.close();
                        clientSocket.close();
                        serverSocket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receive.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
