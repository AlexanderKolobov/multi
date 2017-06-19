package lecture10;


public class ReEntrantLocks {
    public static void main(String[] args) throws InterruptedException {

        final  Runner runner = new Runner();

        Thread thread1 = new Thread(() -> {
            try {
                runner.firstThred();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                runner.secondThred();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        runner.finished();
    }
}
