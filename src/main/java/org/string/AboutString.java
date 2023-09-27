package org.string;

import java.util.Arrays;

/**
 * @author 下水道的小老鼠
 */
public class AboutString {

    /**
     * 344. 反转字符串
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;

        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 541. 反转字符串 II
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i += 2 * k) {
            if (s.length() < i + k) {
                char[] c = s.substring(i).toCharArray();
                reverseString(c);
                return stringBuilder.append(c).toString();
            }
            char[] array = s.substring(i, i + k).toCharArray();
            reverseString(array);
            stringBuilder.append(array);
            if (i + 2 * k <= s.length()) {
                stringBuilder.append(s, i + k, i + 2 * k);
            } else {
                stringBuilder.append(s.substring(i + k));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 151. 反转字符串中的单词
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s = removeSpaces(s);
        char[] c = s.toCharArray();
        reverseString(c);
        char space = ' ';
        int start = 0;
        int end = 0;
        for (; end < c.length; end++) {
            if (c[end] == space) {
                int spaceNum = end--;
                while (start < end) {
                    char temp = c[start];
                    c[start] = c[end];
                    c[end] = temp;
                    start++;
                    end--;
                }
                start = ++spaceNum;
                end = spaceNum;
            }
        }
        end--;
        while (start < end) {
            char temp = c[start];
            c[start] = c[end];
            c[end] = temp;
            start++;
            end--;
        }
        return String.valueOf(c);
    }

    public String removeSpaces(String s) {
        char[] c = s.toCharArray();
        char space = ' ';
        int slow = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] != space) {
                if (slow != 0) {
                    c[slow++] = space;
                }
                while (i < c.length && c[i] != space) {
                    c[slow++] = c[i++];
                }
            }
        }
        return String.valueOf(c).substring(0, slow);
    }

    /**
     * LCR 182. 动态口令
     * 通过局部反转+整体反转 达到左旋转的目的。
     *
     * @param password
     * @param target
     * @return
     */
    public String dynamicPassword(String password, int target) {
        char[] array = password.toCharArray();
        reverseByNumber(array, 0, target - 1);
        reverseByNumber(array, target, array.length - 1);
        reverseByNumber(array, 0, array.length - 1);
        return String.valueOf(array);
    }

    public void reverseByNumber(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }


    /**
     * 28. 找出字符串中第一个匹配项的下标
     * 暴力解法
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        int flag = 0;
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = -1;
                    break;
                }
            }
            if (flag == 0) {
                return i;
            }else{
                flag = 0;
            }
        }
        return -1;
    }

}
