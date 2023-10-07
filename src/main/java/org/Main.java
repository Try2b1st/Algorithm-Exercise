package org;


import org.hash.AboutHashMap;
import org.string.AboutString;

import java.util.Arrays;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        AboutString aboutString = new AboutString();
        String s = "asdfasdfasdf";
        System.out.println(Arrays.toString(new boolean[]{aboutString.repeatedSubstringPattern(s)}));

    }
}