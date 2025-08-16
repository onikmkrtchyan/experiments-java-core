package threads;

public class ThreadStates {
    public static void main(String[] args) throws Exception {
        Object lock = new Object();

        // BLOCKED state
        Thread tBlocked1 = new Thread(() -> {
            synchronized (lock) {
                try { Thread.sleep(10000); } catch (InterruptedException e) {}
            }
        }, "Blocked-Holder");

        Thread tBlocked2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Should not print until holder releases lock.");
            }
        }, "Blocked-Blocked");

        // WAITING state
        Object waitLock = new Object();
        Thread tWaiting = new Thread(() -> {
            synchronized (waitLock) {
                try {
                    waitLock.wait(); // will WAIT indefinitely
                } catch (InterruptedException ignored) {}
            }
        }, "WaitingThread");

        // TIMED_WAITING state
        Thread tTimedWaiting = new Thread(() -> {
            try { Thread.sleep(10000); } catch (InterruptedException ignored) {}
        }, "TimedWaitingThread");

        // RUNNABLE state
        Thread tRunnable = new Thread(() -> {
            while (true) {} // Infinite loop to keep RUNNABLE
        }, "RunnableThread");

        // Start threads in a sequence for best effect
        tBlocked1.start();
        Thread.sleep(100); // Let it grab the lock
        tBlocked2.start();

        tWaiting.start();
        tTimedWaiting.start();
        tRunnable.start();

        // Keep main thread alive long enough to observe in VisualVM
        Thread.sleep(20000);
    }
}
