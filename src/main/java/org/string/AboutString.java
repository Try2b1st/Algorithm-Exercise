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










}
