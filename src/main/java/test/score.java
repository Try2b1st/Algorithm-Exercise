package test;

import java.util.Scanner;

public class score {
    public static void main(String[] args) {
        float[] scores = new float[100]; // 假设最多有100个成绩
        int length = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入成绩（输入-1结束）:");

        // 从用户输入中读取成绩
        for (int i = 0; i < scores.length; i++) {
            float input = scanner.nextFloat();
            if (input == -1) {
                break;
            }
            scores[i] = input;
            length++;
        }

        scanner.close();

        score_processing(scores,length);
    }

    public static void score_processing(float[] score, int length) {
        int count = 0;
        float sum = 0;

        for (int i = 0; i < 10 && i < length; i++) {
            if (score[i] >= 0 && score[i] <= 100) {
                count++;
                sum += score[i];
            }
        }

        float average = count > 0 ? sum / count : 0;

        System.out.println("有效成绩个数: " + count);
        System.out.println("有效成绩总和: " + sum);
        System.out.println("有效成绩平均值: " + average);
    }
}

