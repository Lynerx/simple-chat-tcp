package org.academiadecodigo.gitbusters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {

        int portNumber = 5000;
        BufferedReader bufferedReader = null;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {

            System.out.println("Binding to port " + portNumber + ", please wait  ...");
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server started: " + serverSocket.getInetAddress() + " : " + serverSocket.getLocalPort());

            System.out.println("Waiting for a client ...");
            clientSocket = serverSocket.accept();
            System.out.println("Client accepted: " + clientSocket.getLocalAddress() + " : " + clientSocket.getPort());

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String thisLine = null;

            while ((thisLine = bufferedReader.readLine()) != null) {
                System.out.println(thisLine);
                out.println(thisLine);
            }


        } catch (IOException ioException) {

            ioException.printStackTrace();

        } finally {

            try {

                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}
