package juc;

public class AlternatePrintingThreeThread {
    private int currentNumber = 1;
    private final Object lock = new Object();

    public static void main(String[] args) {
        AlternatePrintingThreeThread alternatePrintingThreeThread = new AlternatePrintingThreeThread();

        Thread zero = new Thread(() -> alternatePrintingThreeThread.printfNumber(0));
        Thread one = new Thread(() -> alternatePrintingThreeThread.printfNumber(1));
        Thread two = new Thread(() -> alternatePrintingThreeThread.printfNumber(2));

        zero.start();
        one.start();
        two.start();
    }

    public void printfNumber(int flag) {
        while (currentNumber <= 100) {
            synchronized (lock) {
                while (currentNumber <= 100 && currentNumber % 3 != flag) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }

                if (currentNumber <= 100) {
                    System.out.println("线程 " + flag + " 输出 " + currentNumber);
                    currentNumber++;
                    lock.notifyAll();
                }
            }
        }
    }
}
