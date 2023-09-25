package org;


import org.hash.AboutHashMap;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        AboutHashMap aboutHashMap = new AboutHashMap();
        int[] array = new int[]{1000000000,1000000000,1000000000,1000000000};

        System.out.println(aboutHashMap.fourSum(array,-294967296));

    }
}