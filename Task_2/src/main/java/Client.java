import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket ("localhost",50001);

            // поток для отправки данных на сервер
            OutputStream os = clientSocket.getOutputStream();
            // поток для получения данных от сервера
            InputStream is = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            Scanner input = new Scanner(System.in);

            while (true) {

                ////////// QUESTION #1 ///////////////
                // read question from console
                System.out.print("What is Your name? ");
                String question1 = input.nextLine();

                // send question to server
                os.write((question1 + "\n").getBytes("UTF-8"));

                // Read response from server
                String response1 = reader.readLine();
                System.out.println(response1);


                ////////// QUESTION #2 ///////////////

                // read question #2 from console
                System.out.print("Ask Question: ");
                String question2 = input.nextLine();

                //1. send question to server
                os.write((question2 + "\n").getBytes("UTF-8"));
                //2. read response from server
                String response2 = reader.readLine();

                System.out.printf("Answer: %s \n", response2);


                ////////// QUESTION #3 ///////////////

            System.out.print("What do you work? ");
                String question3 = input.nextLine();

                //1. send question to server
                os.write((question2 + "\n").getBytes("UTF-8"));
                //2. read response from server
                String response3 = reader.readLine();

                System.out.printf("Answer: %s \n", response3);

                //////////////////////////////////////
                if (question2.equals("exit")) {
                    break;
                }
            }
            input.close();
            os.close();
            clientSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}