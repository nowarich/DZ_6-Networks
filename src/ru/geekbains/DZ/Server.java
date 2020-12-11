package ru.geekbains.DZ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static ServerSocket serverSocket;
    private static DataOutputStream out;
    private static DataInputStream in;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("Server initiated " + serverSocket);
            Socket client = serverSocket.accept();
            System.out.println("ClientApp joined: " + client);
            in = new DataInputStream(client.getInputStream());
            System.out.println(in);
            out = new DataOutputStream(client.getOutputStream());
            System.out.println(out);


            listen();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                out.writeUTF(scanner.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void listen() {
        new Thread(() -> {
            while (true){
                try {
                    System.out.println(in.readUTF());
                } catch (Exception e) {
                    throw new RuntimeException("SWW", e);
                }
            }
        }).start();
    }
}
