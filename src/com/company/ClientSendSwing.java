package com.company;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by kruczjak on 11.05.14.
 */
public class ClientSendSwing {
    private final PrintWriter out;
    private final String user;

    public ClientSendSwing(PrintWriter out, String user) {
        this.out = out;
        this.user = user;
        send(user);
    }


    public void send(String message) {
        out.println(message);
        out.flush();
    }
}
