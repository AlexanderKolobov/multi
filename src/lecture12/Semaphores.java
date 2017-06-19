package lecture12;


import java.util.concurrent.Semaphore;

public class Semaphores {
    public static void main(String[] args) throws Exception {
        Semaphore sem = new Semaphore(1);

        sem.release(1);
        sem.acquire();

        System.out.println("Available permits: " + sem.availablePermits());
    }
}
