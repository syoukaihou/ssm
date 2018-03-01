package com.snsprj.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TestBIO {

    public void m(){
        try {

            ServerSocket serverSocket = new ServerSocket(8888);

            Socket clientSocket = serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String request, response;

            while ((request = in.readLine()) != null){
                if ("Done".equals(request)){
                    break;
                }
//                response = processRequest(request);
                response = "response : " + request;
                out.println(response);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
