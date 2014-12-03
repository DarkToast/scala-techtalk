package pittfalls.concurrency;

import java.util.concurrent.*;

public class JavaFuture {
    public static void main(String[] args) {
        futures();
        threads();
    }

    public static void threads() {
        final ThreadSafeMessage threadSafeMessage = new ThreadSafeMessage();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    // No return value. We MUST change someones state!
                    threadSafeMessage.setValue("I run in an own Thread objects thread, so I am!");
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
            String s = threadSafeMessage.getValue();
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

    // We have to define an mutable container for our thread.
    // Cause we are in a concurrent environment, changes on the internal
    // state must be synchronized.
    public static class ThreadSafeMessage {
        private String messageState = "";

        public synchronized void setValue(final String value) {
            messageState = value;
        }

        public synchronized String getValue() {
            return messageState;
        }
    }
}
