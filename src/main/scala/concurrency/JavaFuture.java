package concurrency;

import java.util.concurrent.*;

public class JavaFuture {
    public static void main(String[] args) {
        futures();
        threads();
    }

    public static void threads() {
        final Msg msg = new Msg();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    // No return value. We MUST change someones state!
                    msg.value = "I run in an own Thread objects thread, so I am!";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.interrupt();
                }

            }
        };

        try {
            thread.start();
            System.out.println("Let's get our Thread value");
            // Blocking!!
            thread.join();
            String s = msg.value;
            System.out.println("The Thread value is: '" + s + "'.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void futures() {
        FutureTask<String> future = new FutureTask<>(new Callable<String>() {
            public String call() {
                try {
                    Thread.sleep(4000);
                    return "I run in an own Future thread, so I am!";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return "";
                }
            }
        });

        // We must explicitly execute our future!
        // Hmm. Let's hope, that this code piece is thread safe.
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(future);

        try {
            System.out.println("Let's get our Future value");
            // Blocking!!
            String s = future.get();
            System.out.println("The Future value is: '" + s + "'.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static class Msg {
        public volatile String value = "";
    }
}
