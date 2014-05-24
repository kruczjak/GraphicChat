package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by kruczjak on 23.05.14.
 */
public class Chat {
    Client client;
    private JTextField textField2;
    private JScrollPane scrollMessages;
    private JButton send;
    private JPanel panel;
    private JTextArea textArea1;

    public Chat() {
        init();
    }



    private void init() {
        client = new Client();

        textField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER)
                    send();
            }
        });

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                send();
            }
        });
    }

    private void send() {
        client.getClientSendSwing().send(textField2.getText());
        textField2.setText("");
    }

    public Client getClient() {
        return client;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat");
        Chat chat = new Chat();
        frame.setContentPane(chat.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(250,150));
        frame.pack();
        frame.setVisible(true);

        try {
            chat.getClient().startSwing(chat.getTextArea1());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Błąd", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
