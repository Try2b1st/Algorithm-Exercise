package test;

public class WorkTime {


    public double SalaryCaculating(double t, double wph) {
        double hour = t;
        double sum = 0;
        if (hour >= 0 && hour <= 40) {
            sum += hour * wph;
        } else if (hour <= 50) {
            sum += 40 * wph;
            hour -= 40;
            sum += hour * wph * 1.5;
        } else {
            sum += 40 * wph;
            hour -= 40;
            sum += 10 * wph * 1.5;
            hour -= 10;
            sum += hour * wph * 3;
        }
        System.out.println("你这个月的工资为：" + sum + "元");
        return sum;
    }
}
