package threads;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class ReentrantLockTimeoutLockExample {

    private final ReentrantLock lock = new ReentrantLock();

    public void accessResource() {
        try {
            // Try to acquire the lock within 2 seconds
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " acquired the lock");
                    // Simulate work
                    Thread.sleep(3000);
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " released the lock");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock in time");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted");
        }
    }

    public static void main(String[] args) {
        ReentrantLockTimeoutLockExample example = new ReentrantLockTimeoutLockExample();

        Runnable task = example::accessResource;

        Thread t1 = new Thread(task, "Thread-A");
        Thread t2 = new Thread(task, "Thread-B");

        t1.start();
        t2.start();
    }
}
