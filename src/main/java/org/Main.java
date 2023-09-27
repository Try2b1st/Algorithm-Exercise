package org;


import org.hash.AboutHashMap;
import org.string.AboutString;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        AboutString aboutString =new AboutString();
        String s="F R  I   E    N     D      S      ";

        System.out.println(aboutString.reverseWords(s));

    }
}