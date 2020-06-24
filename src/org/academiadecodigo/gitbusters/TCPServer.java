package org.academiadecodigo.gitbusters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private final int portNumber;
    private BufferedReader bufferedReader;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public TCPServer(int portNumber) {
        this.portNumber = portNumber;
        bufferedReader = null;
        serverSocket = null;
        clientSocket = null;
    }

    public static void main(String[] args) {

        TCPServer tcpServer = new TCPServer(5000);

        tcpServer.initSockets();

        tcpServer.readMessage();

        tcpServer.closeConnections();

    }

    public void initSockets() {
        try {

            System.out.println("Binding to port " + portNumber + ", please wait  ...");
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server started: " + serverSocket.getInetAddress() + " : " + serverSocket.getLocalPort());

            System.out.println("Waiting for a client ...");
            clientSocket = serverSocket.accept();
            System.out.println("Client accepted: " + clientSocket.getLocalAddress() + " : " + clientSocket.getPort());

        } catch (IOException ioException) {

            System.out.println(ioException.getMessage());
        }
    }


    public void readMessage() {
        try {

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String thisLine = null;

            while ((thisLine = bufferedReader.readLine()) != null) {
                System.out.println(thisLine);
                out.println(thisLine);
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
            if (serverSocket != null) {
                serverSocket.close();
            }

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }


}
