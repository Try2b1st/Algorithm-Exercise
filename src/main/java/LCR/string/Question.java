package LCR.string;

import java.util.*;

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
            map.put(c, end + 1);
        }
        return max;
    }


    /**
     * LCR 157. 套餐内商品的排列顺序
     *
     * @param goods
     * @return
     */
    List<String> list = new ArrayList<>();
    int N;
    char[] arr;

    public String[] goodsOrder(String goods) {
        N = goods.length();
        arr = goods.toCharArray();
        dfsToGoodsOrder(0);
        return list.toArray(new String[0]);
    }

    public void dfsToGoodsOrder(int x) {
        if (x == N - 1) {
            list.add(String.valueOf(arr));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = x; i < N; i++) {
            if (set.contains(arr[i])) {
                continue;
            }
            set.add(arr[i]);
            swap(i, x);
            dfsToGoodsOrder(x + 1);
            swap(i, x);
        }
    }

    public void swap(int a, int b) {
        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    /**
     * LCR 169. 招式拆解 II
     *
     * @param arr
     * @return
     */
    public char dismantlingAction2(String arr) {
        int n = arr.length();
        Map<Character, Boolean> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char c = arr.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, false);
            } else {
                map.put(c, true);
            }
        }
        for (int i = 0; i < n; i++) {
            char c = arr.charAt(i);
            if(map.get(c)){
                return c;
            }
        }
        return ' ';
    }
}

















