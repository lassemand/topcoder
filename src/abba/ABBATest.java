package abba;

import org.junit.Assert;
import org.junit.Test;

public class ABBATest {

    private ABBA abba = new ABBA();

    @Test
    public void test0() {
        Assert.assertEquals("Possible", abba.canObtain("B", "ABBA"));
    }

    @Test
    public void test1() {
        Assert.assertEquals("Impossible", abba.canObtain("AB", "ABB"));
    }

    @Test
    public void test2() {
        Assert.assertEquals("Impossible", abba.canObtain("BBAB", "ABABABABB"));
    }

    @Test
    public void test3() {
        Assert.assertEquals("Possible", abba.canObtain("BBBBABABBBBBBA", "BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"));
    }

    @Test
    public void test4() {
        Assert.assertEquals("Impossible", abba.canObtain("A", "BB"));
    }

    @Test
    public void test10() {
        Assert.assertEquals("Possible", abba.canObtain("AB", "AABAB"));
    }
}
