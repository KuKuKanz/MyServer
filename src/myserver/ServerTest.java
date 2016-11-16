/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * s
 *
 * @author kukulkan
 */
public class ServerTest {
        public static void main(String[] args) {
            Thread thr = new Thread(new SocketThread());
            thr.start();
        }
    }

    class SocketThread implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("Server has opened...");
                ServerSocket server = new ServerSocket(8888);
                while (true) {
                    new SocketConnection(server.accept()).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class SocketConnection extends Thread {
        InputStream input;
        PrintWriter output;
        Socket socket;

        public SocketConnection(Socket socket) {
            super("Thread 1");
            this.socket = socket;
            System.out.println("A socket has been connected");
            try {
                input = socket.getInputStream();
                output = new PrintWriter(new OutputStreamWriter(
                        socket.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    do {
                        byte array[] = new byte[1024];

                        int readed = input.read(array);
                        System.out.println("readed == " + readed + " "
                                + new String(array).trim());
                        String sendString = new String(
                                "Hello Ukraine!".getBytes(),
                                Charset.forName("UTF-8"));
                        output.write(sendString);
                        output.flush();
                        
                        if (readed == -1){
                            return;
                        }
                    } while (input.available() != 0);
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
