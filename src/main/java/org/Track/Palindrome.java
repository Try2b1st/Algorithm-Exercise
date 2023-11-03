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
            for(String s : list){
                stringBuilder.append(s);
            }
            if(origin.equals(stringBuilder.toString())){
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

}
