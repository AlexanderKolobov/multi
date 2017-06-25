package lecture13;


import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class CallableAndFuture {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        /*executorService.submit(new Runnable() {
            @Override
            public void run() {

                Random random = new Random();
                int duration = random.nextInt(4000);

                System.out.println("Starting ... ");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished. ");
            }
        });*/

        Future<Integer> future = executorService.submit(new Callable<Integer>() { // Integer is the type I want return from running Thread code
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);

                if (duration > 2000) {
                    throw new IOException("Sleeping is too long.");
                }

                System.out.println("Starting ... ");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished. ");

                return duration;
            }
        });

        executorService.shutdown();

        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            IOException ex = (IOException) e.getCause();

            System.out.println(ex.getMessage());
        }
    }
}
