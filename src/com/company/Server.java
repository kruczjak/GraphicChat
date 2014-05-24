package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Server {
    public static ServerSocket serverSocket;
    public static List<ServerOneThread> clients =  new ArrayList<ServerOneThread>();


    public static void main(String[] args) throws IOException {
        Server server = new Server();
        serverSocket = new ServerSocket(8080);
        while(true) {
            Socket client = serverSocket.accept();
            System.out.println("Client connected");
            ServerOneThread c = new ServerOneThread(server,client);
            server.add(c);
            c.start();
        }
    }

    public synchronized void send(String message, String user)   {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String date = sdf.format(cal.getTime()) + " ";

        for (ServerOneThread client : clients)   {
//            if (client.getUser().equals(user)) continue;
            client.getPrintWriter().println(date + user + ": " + message);
            client.getPrintWriter().flush();
        }
    }

    public synchronized void add(ServerOneThread serverOneThread)    {
        clients.add(serverOneThread);
    }

    public synchronized void remove(ServerOneThread serverOneThread)    {
        clients.remove(serverOneThread);
    }

}
