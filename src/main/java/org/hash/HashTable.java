package org.hash;

import org.linkedList.LinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 当我们遇到了要快速判断一个元素是否出现集合里的时候，就要考虑哈希法。
 * 但是哈希法也是牺牲了空间换取了时间，因为我们要使用额外的数组，set或者是map来存放数据，才能实现快速地查找。
 *
 * @author 下水道的小老鼠
 */
public class HashTable {
    /**
     * 242. 有效的字母异位词
     *
     * @param s 字符串
     * @param t 字符串
     * @return boolean
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] array = new int[26];

        for (int i = 0; i < s.length(); i++) {
            array[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            array[t.charAt(i) - 'a']--;
        }
        for (int j : array) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 383. 赎金信
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
//        HashMap<Character, Integer> hashMap = new HashMap<>();
//        for (int i = 0; i < magazine.length(); i++) {
//            if (!hashMap.containsKey(magazine.charAt(i))) {
//                hashMap.put(magazine.charAt(i), 1);
//            } else {
//                hashMap.put(magazine.charAt(i), hashMap.get(magazine.charAt(i)) + 1);
//            }
//        }
//
//        for (int i = 0; i < ransomNote.length(); i++) {
//            if (hashMap.containsKey(ransomNote.charAt(i))) {
//                if (hashMap.get(ransomNote.charAt(i)) == 0) {
//                    return false;
//                } else {
//                    hashMap.put(ransomNote.charAt(i), hashMap.get(ransomNote.charAt(i)) - 1);
//                }
//            } else {
//                return false;
//            }
//        }
//        return true;
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] array = new int[26];

        for (int i = 0; i < magazine.length(); i++) {
            array[magazine.charAt(i) - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (array[ransomNote.charAt(i) - 'a'] > 0) {
                array[ransomNote.charAt(i) - 'a']--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 49. 字母异位词分组
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            return null;
        }

        List<List<String>> lists = new ArrayList<>();
        if (strs.length == 1) {
            List<String> stringList = new ArrayList<>();
            stringList.add(strs[0]);
            lists.add(stringList);
            return lists;
        }

        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            List<String> stringList = new ArrayList<>();
            if (!hashMap.containsKey(strs[i])) {
                hashMap.put(strs[i], 1);
                stringList.add(strs[i]);
                for (int j = i + 1; j < strs.length; j++) {
                    if (isAnagram(strs[i], strs[j])) {
                        if (!hashMap.containsKey(strs[j])) {
                            hashMap.put(strs[j], 1);
                            stringList.add(strs[j]);
                        } else if (isAnagram(strs[i], strs[j])) {
                            stringList.add(strs[j]);
                        }
                    }

                }
                if (stringList.size() > 0) {
                    lists.add(stringList);
                }
            }
        }
        return lists;
    }
}
