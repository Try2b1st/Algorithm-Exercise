package org.Track;

import org.string.AboutString;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {

    List<List<String>> result = new ArrayList<>();
    List<String> list = new ArrayList<>();

    /**
     * 131. 分割回文串
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        backtrackingToPartition(s, 0);
        return result;
    }

    public void backtrackingToPartition(String origin, int startIndex) {
        if (startIndex == origin.length()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : list) {
                stringBuilder.append(s);
            }
            if (origin.equals(stringBuilder.toString())) {
                result.add(new ArrayList<>(list));
            }
            return;
        }

        for (int i = startIndex; i < origin.length(); i++) {
            String temp1 = origin.substring(startIndex, i + 1);
            String temp2 = new StringBuffer(temp1).reverse().toString();
            if (temp1.equals(temp2)) {
                list.add(temp1);
            }
            backtrackingToPartition(origin, i + 1);
            if (temp1.equals(temp2)) {
                list.remove(list.size() - 1);
            }
        }
    }


    /**
     * 93. 复原 IP 地址
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        backtrackingToRestoreIpAddresses(s, new StringBuilder(), 0, 0);
        return list;
    }

    public void backtrackingToRestoreIpAddresses(String s, StringBuilder sb, int startIndex, int pointNumber) {
        if (pointNumber == 4) {
            if (sb.length() == s.length() + 4) {
                if(validateIPNumbers(String.valueOf(sb))){
                    sb.deleteCharAt(sb.length() - 1);
                    list.add(String.valueOf(sb));
                    sb.append(".");
                }
            }
            return;
        }
        if (startIndex >= s.length()) {
            return;
        }

        for (int i = 1; i < 4; i++) {
            int min = Math.min(startIndex + i, s.length());
            sb.append(s.substring(startIndex, min));
            sb.append(".");
            backtrackingToRestoreIpAddresses(s, sb, min, pointNumber + 1);
            sb.deleteCharAt(sb.length() - 1);
            for (int j = 0; j < Math.min(i,s.length() - startIndex); j++) {
                sb.deleteCharAt(sb.length() - 1);
            }
            if(min == s.length()){
                break;
            }
        }
    }

    public boolean validateIPNumbers(String ip) {
        String[] numbers = ip.split("\\.");
        for (String number : numbers) {
            try {
                if(number.length() > 1 && number.charAt(0) == '0'){
                    return false;
                }
                int value = Integer.parseInt(number);
                if (value < 0 || value > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

}
