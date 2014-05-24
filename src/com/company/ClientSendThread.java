package com.company;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by kruczjak on 11.05.14.
 */
public class ClientSendThread extends Thread {
    private final PrintWriter out;
    private final String user;
    public ClientSendThread(PrintWriter out, String user) {
        this.out = out;
        this.user = user;
    }

    @Override
    public void run() {
        out.println(user);
        out.flush();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String message = scanner.nextLine();
            out.println(message);
            out.flush();
        }
    }
}
