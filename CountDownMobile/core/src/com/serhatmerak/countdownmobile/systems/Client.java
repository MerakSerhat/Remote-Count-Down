package com.serhatmerak.countdownmobile.systems;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.serhatmerak.countdownmobile.MainScreen;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import sun.applet.Main;

public class Client {

    private PrintWriter pr;
    private FileHandle fileHandle;
    public final static int FILE_SIZE = 6022386; // file size temporary hard coded

    public static final int portFile = 6666;
    public static final int portMessage = 6667;

    private MainScreen mainScreen;

    public String FILE_TO_RECEIVED;

    public boolean connected = false;

    public static String IPv4 = "192.168.1.103";


    private void executeFileSocket() throws IOException {
        final Socket s = new Socket(IPv4,portFile);
        System.out.println("Connected to file server");

        connected = true;
        mainScreen.setClientInfo(true);

        readFile(s);

        s.close();
        executeFileSocket();

    }

    private void executeMessageSocket() throws IOException {
        final Socket s = new Socket(IPv4,portMessage);
        System.out.println("Connected to message server");

        readString(s);

        s.close();
        executeMessageSocket();

    }

    private void readString(Socket s) throws IOException {
        InputStreamReader ir = new InputStreamReader(s.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(ir);
        String str = bufferedReader.readLine();
        if(str.equals("Pause"))
            mainScreen.setPaused(true);


        if (str.equals("Play"))
            mainScreen.setPaused(false);

        System.out.println("server: " + str);
    }

    private void readFile(Socket s) throws IOException {
        FileOutputStream fos;
        BufferedOutputStream bos;

        int bytesRead;
        int current = 0;

//            InputStreamReader ir = new InputStreamReader(s.getInputStream());
//            BufferedReader bufferedReader = new BufferedReader(ir);
//            String str = bufferedReader.readLine();
//            System.out.println("server: " + str);

        System.out.println("1...");
        byte[] mybytearray = new byte[FILE_SIZE];
        InputStream is = s.getInputStream();
        System.out.println("2");
        fos = new FileOutputStream(FILE_TO_RECEIVED);
        bos = new BufferedOutputStream(fos);
        System.out.println("3");
        bytesRead = is.read(mybytearray, 0, mybytearray.length);
        current = bytesRead;
        System.out.println("4");
        do {
            System.out.println(bytesRead);
            bytesRead =
                    is.read(mybytearray, current, (mybytearray.length - current));
            if (bytesRead >= 0) current += bytesRead;
        } while (bytesRead > -1);
        System.out.println("5");
        bos.write(mybytearray, 0, current);
        bos.flush();
        System.out.println("File " + FILE_TO_RECEIVED
                + " downloaded (" + current + " bytes read)");
        mainScreen.startCountDown();
    }

    public Client(MainScreen mainScreen){

        FileHandle fileHandle = Gdx.files.local("countdownset.txt");
        FILE_TO_RECEIVED = fileHandle.file().getPath();

        this.mainScreen = mainScreen;
    }

    public void execute(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    executeFileSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                    connected = false;
                    mainScreen.setClientInfo(false);
                }

            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    executeMessageSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                    connected = false;
                    mainScreen.setClientInfo(false);
                }

            }
        }.start();

    }


}
