package LCR.string;

import java.util.HashMap;
import java.util.Map;

public class Question {

    /**
     * LCR 122. 路径加密
     *
     * @param path
     * @return
     */
    public String pathEncryption(String path) {
        return path.replace(".", "%20");
    }


    /**
     * LCR 167. 招式拆解 I
     *
     * @param arr
     * @return
     */
    public int dismantlingAction(String arr) {
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < arr.length(); end++) {
            char c = arr.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(map.get(c), start);
            }
            max = Math.max(max, end - start + 1);
            map.put(c, end+1);
        }
        return max;
    }
}

















