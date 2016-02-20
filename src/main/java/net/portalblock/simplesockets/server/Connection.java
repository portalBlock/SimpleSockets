package net.portalblock.simplesockets.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by portalBlock on 2/20/2016.
 */
public class Connection implements Runnable {

    private Socket client;

    //This is the socket from the server, it represents the client.
    public Connection(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        //Implement an existing protocol or make your own here. Use streams accessible by the client.
        try{
            /*
            There are many ways to manipulate data here, read up on working with streams.
            ObjectStreams are Java only and not recommended.
             */
            InputStream inputStream = client.getInputStream(); //This is the input to the server, read bytes here.
            OutputStream outputStream = client.getOutputStream(); //This is the output to the client, write bytes here.
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
