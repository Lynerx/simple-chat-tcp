package org.academiadecodigo.gitbusters;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {


        int portNumber = 5000;
        String hostName = "localhost";
        Socket clientSocket = null;
        BufferedReader bufferedReader = null;
        Scanner scanner = new Scanner(System.in);

        try {

            clientSocket = new Socket(hostName, portNumber);
            System.out.println("Connected: " + clientSocket.getLocalAddress() + " : " + clientSocket.getPort());

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message = "";

            while (!message.equals("/quit")) {

                message = scanner.nextLine().trim();
                out.println(message);

                String echo = "";
                if ((echo = bufferedReader.readLine()) != null) {
                    System.out.println(echo);
                }
            }


        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());

        } finally {

            try {

                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }

            } catch (IOException ioException) {

                ioException.printStackTrace();
            }
        }


    }
}

