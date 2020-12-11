package ru.geekbains.DZ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
                System.out.println("Socket info: " + socket);
            in = new DataInputStream(socket.getInputStream());
                System.out.println(in);
            out = new DataOutputStream(socket.getOutputStream());
                System.out.println(out);
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(in.readUTF());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                out.writeUTF(scanner.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
