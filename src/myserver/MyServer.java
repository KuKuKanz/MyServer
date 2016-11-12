/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myserver;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * s
 *
 * @author kukulkan
 */
public class MyServer {

    /**
     */
    static public void OpenServer() throws IOException {
        
        ServerSocket server = new ServerSocket(8080);
            System.out.println("Open server ......");

        try {
            Socket client = server.accept();
            
            System.out.println("Open server is not OK");
            
            BufferedReader bufReader = new BufferedReader(new  InputStreamReader( client.getInputStream()));
            
            String inputLine = "";
            
            while ((inputLine = bufReader.readLine()) != null){
                System.out.print(inputLine);
            }
            
            
        }catch (IOException e){
            System.out.print(e);
        }

    }

}
