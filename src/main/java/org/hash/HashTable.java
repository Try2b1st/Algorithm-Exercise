package org.hash;

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
     * @param s
     * @param t
     * @return
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
}
