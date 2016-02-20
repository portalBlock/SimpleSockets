package net.portalblock.simplesockets.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

/**
 * Created by portalBlock on 2/20/2016.
 */
//implement Runnable to allow async behavior
public class ConnectionAcceptor implements Runnable {

    private int port;
    private ExecutorService service;
    private ServerSocket serverSocket;

    /*
    Accept a port in so we know what to listen on.
    Accept a service in to execute the incoming connections.
    */
    public ConnectionAcceptor(int port, ExecutorService executorService) {
        this.port = port;
        this.service = executorService;
    }

    //Override the run method to actually do things
    @Override
    public void run() {
        try{
            //Build the new ServerSocket
            this.serverSocket = new ServerSocket(this.port);
            /*
            Accept a connection and pass the new socket to a new "Connection"(Basically a socket wrapper)
            Note: Calling ServerSocket#accept() holds the thread until a new connection is made, this is why the server is wrapped in a
            runnable.
            */
            this.service.execute(new Connection(this.serverSocket.accept()));
        }catch(IOException e){ //Catch any problems
            e.printStackTrace();
        }
    }

}
