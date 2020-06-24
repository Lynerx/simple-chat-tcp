package org.academiadecodigo.gitbusters;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    private final int serverPort;
    private String serverAddress;
    private Socket clientSocket;
    private BufferedReader bufferedReader;

    public TCPClient(String serverAddress, int serverPort) {
        this.serverPort = serverPort;
        this.serverAddress = serverAddress;
        clientSocket = null;
        bufferedReader = null;
    }


    public static void main(String[] args) {

        TCPClient tcpClient = new TCPClient("localhost", 5000);

        tcpClient.initConnections();

        tcpClient.sendMessage();

        tcpClient.closeConnections();
    }

    public void initConnections() {
        try {

            clientSocket = new Socket(serverAddress, serverPort);
            System.out.println("Connected: " + clientSocket.getLocalAddress() + " : " + clientSocket.getPort());

        } catch (IOException unknownHostException) {

            System.out.println(unknownHostException.getMessage());
        }
    }

    public void sendMessage() {
        try {

            Scanner scanner = new Scanner(System.in);
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
        }
    }

    public void closeConnections() {
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




