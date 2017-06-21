package lecture12;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Connection {

    private int connections = 0;

    private static Connection instance = new Connection();

    private Semaphore semaphore = new Semaphore(10, true);

    private Connection() {

    }

    public static Connection getInstance(){
        return instance;
    }

    // safe way to call acquire() and release()
    public void connect(){
        try {
            semaphore.acquire();            // add 1 permit for 1 thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    private void doConnect(){
        /*try {
            semaphore.acquire();            // add 1 permit for 1 thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        synchronized (this){
            connections++;
            System.out.println("Current connections: " + connections);
        }

        try {
            TimeUnit.SECONDS.sleep(2);  // if here will be an exception -> semaphore.release() will never start
                                                // In this case safe to start semaphore.release() in finally block
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this){
            connections--;
        }
/*
        semaphore.release();*/
    }
}
