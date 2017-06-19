package lecture2;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Processor extends Thread{

    private boolean running = true;

    @Override
    public void run() {
        while (running){
            System.out.println("Hi man");

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        running = false;
    }
}

public class App {
    public static void main(String[] args) {
        Processor proc1 = new Processor();

        proc1.start();

        System.out.println("Prees return to stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc1.shutdown();
    }
}
