package com.serhatmerak.countdowndesktop.services;


import com.serhatmerak.countdowndesktop.MainScreen;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private MainScreen mainScreen;

    public static final int portFile = 6666;
    private ServerSocket fileServerSocket;
    public Socket fileSocket;

    public static final int portMessage = 6667;
    private ServerSocket messageServerSocket;
    private Socket messageSocket;

    private PrintWriter printWriter;



    public Server(MainScreen mainScreen){
        this.mainScreen = mainScreen;
    }


    public void runServer() {
        Thread fileThread = new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    fileServerSocket = new ServerSocket(portFile);

                    System.out.println("server started at socket: " + portFile);
                    mainScreen.serverStateChanged(true);

                    while (true) {
                        fileSocket = fileServerSocket.accept();
                        mainScreen.clientStateChanged(true);
//                        printWriter = new PrintWriter(fileSocket.getOutputStream());
                        System.out.println("client connected to file server");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        fileThread.start();

        Thread messageThread = new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    messageServerSocket = new ServerSocket(portMessage);

                    System.out.println("server started at socket: " + portMessage);
                    mainScreen.serverStateChanged(true);

                    while (true) {
                        messageSocket = messageServerSocket.accept();
                        printWriter = new PrintWriter(messageSocket.getOutputStream());
                        System.out.println("client connected to message server");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        messageThread.start();

    }

    public void writeToServer(String text){
        if (printWriter != null){
            System.out.println("message sent");
            printWriter.println(text);
            printWriter.flush();
        }
    }

    public void sendFile(File file) throws IOException {
        byte [] mybytearray  = new byte [(int)file.length()];
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read(mybytearray,0,mybytearray.length);
        OutputStream os = fileSocket.getOutputStream();
        System.out.println("Sending " + file.getPath() + "(" + mybytearray.length + " bytes)");
        os.write(mybytearray,0,mybytearray.length);
        os.flush();
        System.out.println("Done.");

        bis.close();
        os.close();


    }
    public void closeServer() {
        try {
            fileServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
