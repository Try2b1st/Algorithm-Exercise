package org.string;

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
}
