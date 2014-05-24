package com.company;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by kruczjak on 11.05.14.
 */
public class Client {

    private ClientSendSwing clientSendSwing = null;

    public static void main(String [] args)   {
        try {
            loop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loop() throws IOException {
        PrintWriter out = null;
        BufferedReader in = null;
        Socket s = null;

        try {
            s = new Socket("127.0.0.1",8080);
            out = new PrintWriter(s.getOutputStream());
            new ClientSendThread(out,readUserName()).start();

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            while (true)   {
                String message = in.readLine();
                if (message==null) break;
                System.out.println(message);
            }

        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (s != null) {
                s.close();
            }
        }
        System.exit(0);
    }

    private static String readUserName() {
        System.out.println("Podaj nazwe uzytkownika: ");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    public void startSwing(JTextArea jTextArea) throws IOException {
        PrintWriter out = null;
        BufferedReader in = null;
        Socket s = null;

        try {
            s = new Socket("127.0.0.1",8080);
            out = new PrintWriter(s.getOutputStream());
            this.clientSendSwing = new ClientSendSwing(out,dialogUserName());

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            while (true)   {
                String message = in.readLine();
                if (message==null) break;
                jTextArea.append(message + "\n");
            }

        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (s != null) {
                s.close();
            }
        }
        System.exit(0);
    }

    private static String dialogUserName() {
        String str = JOptionPane.showInputDialog(null, "Enter your username : ",
                "", 1);
        if (str==null)
            System.exit(0);
        return str;
    }

    public ClientSendSwing getClientSendSwing() {
        return clientSendSwing;
    }
}
