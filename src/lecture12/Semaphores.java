package lecture12;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Semaphores {
    public static void main(String[] args) throws Exception {

        Semaphore sem = new Semaphore(1);

        sem.release(1); // "+" add permits
        sem.acquire();  // "-" subtract permits, it will wait if there will be no permits

        System.out.println("Available permits: " + sem.availablePermits());

        //----------------------------------------

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
