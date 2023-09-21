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
        System.out.println(hashTable.isAnagram("anagram","nagaram"));
    }
}