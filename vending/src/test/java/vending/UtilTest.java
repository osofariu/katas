package vending;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilTest {
    @Test
    public void format100() {
        assertEquals("1.00", Util.formatAmount(100));
    }

    @Test
    public void format90() {
        assertEquals("0.90", Util.formatAmount(90));
    }

    @Test
    public void format5() {
        assertEquals("0.05", Util.formatAmount(5));
    }
}
