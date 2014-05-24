package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.InputMismatchException;

/**
 * Created by kruczjak on 11.05.14.
 */
public class ServerOneThread extends Thread {
    private final Socket s;
    private final Server server;
    private final PrintWriter printWriter;
    private String user;
    public ServerOneThread(Server server, Socket s) throws IOException {
        this.s = s;
        this.server = server;
        this.printWriter = new PrintWriter(s.getOutputStream());
    }

    @Override
    public void run() {
        try {
            readAndSend();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAndSend() throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            user = in.readLine();

            server.send("!!I am connected", user);

            while(true) {
                String message = in.readLine();
                if (message==null) break;
                server.send(message, user);
            }
        } finally {
            server.send("!!Disconnected", user);
            if (in != null) {
                in.close();
            }
            s.close();
            server.remove(this);
        }
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public String getUser() {
        return user;
    }
}
