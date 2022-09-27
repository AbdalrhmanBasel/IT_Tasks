import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            System.out.println("processing new client from " + clientSocket.getInetAddress());
            // поток для получения данных от клиента
            InputStream is = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            // поток для отправления клиенту ответа
            OutputStream os = clientSocket.getOutputStream();

            boolean workStatus = true;

            while (workStatus) {
                System.out.println("1. read question from client");

                ////////// QUESTION #1 ///////////////

                // read question 1 "What Is Your Name? from client"
                String question1 = reader.readLine();
                System.out.println(question1);

                // question 1 response
                String response1 = "";
                String[] elements = question1.split(" ");
                response1 = "Hello " + elements[3];

                // 2. send response to client
                DataOutputStream serverOutput1 = new DataOutputStream(os);
                serverOutput1.write((response1 + "\n").getBytes("UTF-8"));



                ////////// QUESTION #1 ///////////////

                //Read question 2 from client
                String question2 = reader.readLine();
                System.out.println(question2);

                String response2 = "";
                if (question2.equals("exit")) {
                    response2 = "Goodbye!";
                    workStatus = false;
                } else if (question2.equals("How are you?")) {
                    String[] resps = {"Good :)","Bad :(","Excellent! :)))"};
                    response2 = resps[(int)(Math.random()*resps.length)];
                } else {
                    response2 = "Question Is Not understood";
                }

                // 2. send response to client
                DataOutputStream serverOutput = new DataOutputStream(os);
                serverOutput.write((response2 + "\n").getBytes("UTF-8"));

                ////////// QUESTION #3 ///////////////

                String question3 = reader.readLine();
                System.out.println(question2);

                String response3 = "";
                if (question3.equals("exit")) {
                    response3 = "Goodbye!";
                    workStatus = false;
                } else if (question3.equals("Web developer")) {
                    String[] resps3 = {"Amazing!", "Fine!", "Exciting!"};
                    response3 = resps3[(int) (Math.random() * resps3.length)];
                } else if (question3.equals("Mobile developer")) {
                        String[] resps3 = {"Good Luck!"};
                } else {
                    response3 = "I see, good luck.";
                    workStatus = false;
                }


                // 2. send response to client
                DataOutputStream serverOutput3 = new DataOutputStream(os);
                serverOutput.write((response3 + "\n").getBytes("UTF-8"));



            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}