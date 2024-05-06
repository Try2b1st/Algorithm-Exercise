package test;

import java.util.Scanner;

public class workTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hour;
        while (true) {
            System.out.println("输入你的月工作时长：");
            hour = scanner.nextInt();
            if (hour >= 0) break;
        }
        int money;
        while (true) {
            System.out.println("输入你的基础时薪：");
            money = scanner.nextInt();
            if (money > 0) break;
        }
        double sum = 0;
        if (hour >= 0 && hour <= 40) {
            sum += hour * money;
        } else if (hour <= 50) {
            sum += 40 * money;
            hour -= 40;
            sum += hour * money * 1.5;
        } else {
            sum += 40 * money;
            hour -= 40;
            sum += 10 * money * 1.5;
            hour -= 10;
            sum += hour * money * 3;
        }
        System.out.println("你这个月的工资为：" + sum + "元");
    }
}
