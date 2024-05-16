package test;

public class San {
    public int TriangleType(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) return 0;

        boolean flag1 = a + b > c;
        boolean flag2 = a + c > b;
        boolean flag3 = b + c > a;

        if (flag1 && flag2 && flag3) {
            if (a == b) {
                if(a == c){
                    return 3;
                }else{
                    return 2;
                }
            } else if (a == c) {
                return 2;
            } else if (b == c) {
                return 2;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
