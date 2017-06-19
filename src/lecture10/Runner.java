package lecture10;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;
    private Lock lock = new ReentrantLock();    // I can use Lock instead of synchronized
    private Condition condition = lock.newCondition();

    private void increment() {                   //I can make this method synchronized to work threads properly
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThred() throws InterruptedException {
        lock.lock();

        System.out.println("Thread 1 waiting...");
        condition.await();

        System.out.println("Woken up!");

        try {
            increment();     //if this throws an exception it will never unlock. So need use finally block
        } finally {
            lock.unlock();
        }
        finished();
    }

    public void secondThred() throws InterruptedException {
        lock.lock();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("Press the return button...");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");

        condition.signal();

        try {
            increment();
        } finally {
            lock.unlock();
        }
        finished();
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}
