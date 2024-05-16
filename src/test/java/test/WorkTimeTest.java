package test;

import junit.framework.TestCase;
import org.junit.Assert;

public class WorkTimeTest extends TestCase {
    double epsilon = 0.00001;
    public void testSalaryCaculating1() {
        WorkTime workTime = new WorkTime();
        double result = workTime.SalaryCaculating(20,10);
        Assert.assertEquals(200,result,epsilon);
    }
    public void testSalaryCaculating2() {
        WorkTime workTime = new WorkTime();
        double result = workTime.SalaryCaculating(40,10);
        Assert.assertEquals(400,result,epsilon);
    }
    public void testSalaryCaculating3() {
        WorkTime workTime = new WorkTime();
        double result = workTime.SalaryCaculating(41,10);
        Assert.assertEquals(415,result,epsilon);
    }
    public void testSalaryCaculating4() {
        WorkTime workTime = new WorkTime();
        double result = workTime.SalaryCaculating(50,10);
        Assert.assertEquals(550,result,epsilon);
    }
    public void testSalaryCaculating5() {
        WorkTime workTime = new WorkTime();
        double result = workTime.SalaryCaculating(51,10);
        Assert.assertEquals(580,result,epsilon);
    }
    public void testSalaryCaculating6() {
        WorkTime workTime = new WorkTime();
        double result = workTime.SalaryCaculating(-1,10);
        Assert.assertEquals(0,result,epsilon);
    }
    public void testSalaryCaculating7() {
        WorkTime workTime = new WorkTime();
        double result = workTime.SalaryCaculating(0,10);
        Assert.assertEquals(0,result,epsilon);
    }

}