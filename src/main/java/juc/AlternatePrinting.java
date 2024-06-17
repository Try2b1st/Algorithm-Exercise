package juc;

public class AlternatePrinting {
    private int currentNumber = 1;
    private final Object lock = new Object();

    public static void main(String[] args) {
        AlternatePrinting alternatePrinting = new AlternatePrinting();
        Thread oddThread = new Thread(() -> {
            alternatePrinting.printfNumber(false);
        });
        oddThread.start();

        Thread evenThread = new Thread(() -> {
            alternatePrinting.printfNumber(true);
        });
        evenThread.start();
    }

    public void printfNumber(Boolean flag) {
        while (currentNumber <= 100) {
            synchronized (lock) {
                if ((flag && currentNumber % 2 == 1) || (!flag && currentNumber % 2 == 0)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }

                if (currentNumber <= 100) {
                    System.out.println("Thread " + (flag ? "even" : "odd") + "输出" + currentNumber);
                    currentNumber++;
                    lock.notifyAll();
                }
            }
        }
    }
}