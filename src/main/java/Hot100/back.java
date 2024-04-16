package Hot100;

import java.util.ArrayList;
import java.util.List;

public class back {

    /**
     * 22. 括号生成
     *
     * @param n
     * @return
     */
    List<String> ans;

    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        if (n > 0) {
            reToGenerateParenthesis("", n, n);
        }
        return ans;
    }

    private void reToGenerateParenthesis(String s, int left, int right) {
        if (left == 0 && right == 0) {
            ans.add(s);
            return;
        }

        if (left == right) {
            reToGenerateParenthesis(s + "(", left - 1, right);
        } else if (left < right) {
            if (left > 0) {
                reToGenerateParenthesis(s + "(", left - 1, right);
            }
            reToGenerateParenthesis(s + ")", left, right - 1);
        }
    }
}
