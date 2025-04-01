package Laba1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Philosopher extends Thread {
    private final Lock lf;  // левая вилка
    private final Lock rf; // правая вилка
    private final int id;         // номер философа
    private final Semaphore table; // семафор

    public Philosopher(int id, Lock leftFork, Lock rightFork, Semaphore table) {
        this.id = id;
        this.lf = leftFork;
        this.rf = rightFork;
        this.table = table;
    }

    // метод для размышлений
    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " размышляет...");
        Thread.sleep((long) (Math.random() * 1000)); // размышляет..
    }

    // метод для еды
    private void eat() throws InterruptedException {
        System.out.println("Философ " + id + " ест.");
        Thread.sleep((long) (Math.random() * 1000)); // ест
    }

    @Override
    public void run() {
        try {
            while (true) { // запуск бесконечного цикла - едят и думают
                think(); //

                table.acquire(); // запрос места за столом, до 4 философов одновременно

                lf.lock();
                System.out.println("Философ " + id + " взял левую вилку.");

                if (!rf.tryLock()) {  // если не получилось взять правую вилку, отпустить левую и попробовать заново
                    lf.unlock();
                    continue;
                }

                System.out.println("Философ " + id + " взял правую вилку.");

                eat(); // ест

                rf.unlock();
                System.out.println("Философ " + id + " положил правую вилку.");

                lf.unlock();
                System.out.println("Философ " + id + " положил левую вилку.");

                table.release(); // освобождение места
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class EatingPhilosophers {
    public static void main(String[] args) {
        int nump = 5;
        Lock[] forks = new Lock[nump];
        Philosopher[] philosophers = new Philosopher[nump];
        Semaphore table = new Semaphore(nump - 1); // cемафор на 4 философа

        // блокировки для вилок
        for (int i = 0; i < nump; i++) {
            forks[i] = new ReentrantLock();
        }

        for (int i = 0; i < nump; i++) {
            Lock lf = forks[i];
            Lock rf = forks[(i + 1) % nump]; // оследний философ берёт 0 вилку

            philosophers[i] = new Philosopher(i, lf, rf, table);
            philosophers[i].start();
        }
    }
}
