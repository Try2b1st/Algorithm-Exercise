package juc;

/**
 * 为了顺序执行10次打印任务，其中线程A打印1，线程B打印2，线程C打印3，
 * 我们可以使用一个共享的计数器来跟踪当前的打印轮次，并确保每个线程在
 * 正确的轮次中执行。
 */
public class SequentialPrinting {

    /**
     * 当前线程打印的次数
     */
    private int count = 0;
    /**
     * 用于同步锁对象
     */
    private final Object lock = new Object();

    public static void main(String[] args) {
        SequentialPrinting printer = new SequentialPrinting();

        // 创建并启动线程
        Thread tA = new Thread(() -> printer.printNumber(1), "A");
        Thread tB = new Thread(() -> printer.printNumber(2), "B");
        Thread tC = new Thread(() -> printer.printNumber(3), "C");

        tA.start();
        tB.start();
        tC.start();
    }

    private void printNumber(int numberToPrint) {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (count % 3 != numberToPrint - 1) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (count < 30) {
                    System.out.println("Thread " + Thread.currentThread().getName() + ": " + numberToPrint);
                    count++;
                    lock.notifyAll();
                }
            }
        }
    }
}
