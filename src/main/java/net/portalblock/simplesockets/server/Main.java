package net.portalblock.simplesockets.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by portalBlock on 2/20/2016.
 */
public class Main {

    private ExecutorService service;
    private ConnectionAcceptor connectionAcceptor;

    private Main(){
        service = Executors.newCachedThreadPool(new ThreadFactory() {
            private final AtomicInteger THREAD_COUNT = new AtomicInteger(0);
            private final String FORMAT = "SimpleSockets-Thread-#%s";
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName(String.format(FORMAT, THREAD_COUNT.getAndIncrement()));
                return t;
            }
        });
        connectionAcceptor = new ConnectionAcceptor(9090, service);
    }

    private void start(){
        service.execute(connectionAcceptor);
    }

    public static void main(String[] args) {
        new Main().start();
    }

}
