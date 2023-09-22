package org;


import org.array.Matrix;
import org.array.SlidingWindow;
import org.hash.HashTable;

import java.util.Arrays;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        HashTable hashTable = new HashTable();
        String[] strings = new String[]{"dis","sid","sid"};
        System.out.println(hashTable.findAnagrams("cbaebabacd","abc"));
    }
}