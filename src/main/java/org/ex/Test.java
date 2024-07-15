package org.ex;

import java.math.BigInteger;
import java.util.*;

public class Test {

    public static void main(String[] args) {
    }


    static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 31);
        map.put(2, 28);
        map.put(3, 31);
        map.put(4, 30);
        map.put(5, 31);
        map.put(6, 30);
        map.put(7, 31);
        map.put(8, 31);
        map.put(9, 30);
        map.put(10, 31);
        map.put(11, 30);
        map.put(12, 31);
    }

    public static void nextDate(int year, int month, int day) {
        if (year < 1920 || 2050 < year) {
            System.out.println("年份，输入不正确");
            return;
        }
        if (month < 1 || month > 12) {
            System.out.println("月份，输入不正确");
            return;
        }
        if (day < 1 || map.get(month) < day) {
            System.out.println("日期，输入不正确");
            return;
        }

        day++;
        if (day > map.get(month)) {
            month++;
            if (month == 13) {
                month = 1;
                year++;
            }
            day = 1;
        }
        System.out.println("日期是：" + year + "." + month + "." + day);
    }

}
