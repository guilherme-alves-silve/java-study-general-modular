package br.com.guilhermealvessilve.certification.study.concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Alves
 */
public class Thread1Main {
    
    public static void main(String[] args) throws InterruptedException {
        
        for (int i = 0; i < 10; ++i) {
            final Runnable action = () -> {
                var currentThread = Thread.currentThread();
                while (!currentThread.isInterrupted()) {
                    printThreadState(currentThread);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        System.out.println("The thread was interrupted while sleeping!");
                        printThreadState(currentThread);
                        return;
                    }
                }

                System.out.println("The thread was interrupted while running!");
                printThreadState(currentThread);
            };

            final var random = new Random();
            final var thread = new Thread(action, "ThreadWithLoop-" + i);
            printThreadState(thread);
            thread.start();

            boolean mustSleep = random.nextBoolean();
            if (mustSleep) {
                TimeUnit.MILLISECONDS.sleep(50);
            }
            thread.interrupt();
            printThreadState(thread);
        }
    }
    
    private static void printThreadState(Thread thread) {
        System.out.println(thread.getName() + " actual state: " + thread.getState());
    }
}
