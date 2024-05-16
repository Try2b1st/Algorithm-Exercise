package test;

import junit.framework.TestCase;
import org.junit.Assert;

public class SanTest extends TestCase {

    public void testTriangleType1() {
        San san = new San();
        int result = san.TriangleType(-1, 5, 6);
        Assert.assertEquals(0, result);
    }

    public void testTriangleType2() {
        San san = new San();
        int result = san.TriangleType(5, 0, 6);
        Assert.assertEquals(0, result);
    }

    public void testTriangleType3() {
        San san = new San();
        int result = san.TriangleType(5, 5, 5);
        Assert.assertEquals(3, result);
    }

    public void testTriangleType4() {
        San san = new San();
        int result = san.TriangleType(5, 5, 7);
        Assert.assertEquals(2, result);
    }

    public void testTriangleType5() {
        San san = new San();
        int result = san.TriangleType(3, 4, 5);
        Assert.assertEquals(1, result);
    }

    public void testTriangleType6() {
        San san = new San();
        int result = san.TriangleType(1, 2, 3);
        Assert.assertEquals(0, result);
    }

    public void testTriangleType7() {
        San san = new San();
        int result = san.TriangleType(10, 2, 7);
        Assert.assertEquals(0, result);
    }
    public void testTriangleType8() {
        San san = new San();
        int result = san.TriangleType(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        Assert.assertEquals(3, result);
    }
}