package be.skylark.weather.darkskyclient.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberUtilsTest {

    @Test
    public void testInclusive() {
        Assertions.assertEquals( true , NumberUtils.isBetween(0, 100, 50, true));
        Assertions.assertEquals( true , NumberUtils.isBetween(0, 100, 0, true));
        Assertions.assertEquals( true , NumberUtils.isBetween(0, 100, 100, true));
        Assertions.assertEquals( true , NumberUtils.isBetween(-10, 100, -1, true));
        Assertions.assertEquals( true , NumberUtils.isBetween(0.0, 100.1, 50, true));
        Assertions.assertEquals( false , NumberUtils.isBetween(0.0, 100.1, 100.11, true));
        Assertions.assertEquals( false , NumberUtils.isBetween(-10, 100.1, -20, true));
    }

    @Test
    public void testExclusive() {
        Assertions.assertEquals( true , NumberUtils.isBetween(0, 100, 50, false));
        Assertions.assertEquals( false , NumberUtils.isBetween(0, 100, 0, false));
        Assertions.assertEquals( false , NumberUtils.isBetween(0, 100, 100, false));
        Assertions.assertEquals( true , NumberUtils.isBetween(-10, 100, -1, false));
        Assertions.assertEquals( true , NumberUtils.isBetween(0.0, 100.1, 50, false));
        Assertions.assertEquals( false , NumberUtils.isBetween(0.0, 100.1, 100.11, false));
        Assertions.assertEquals( false , NumberUtils.isBetween(-10, 100.1, -20, false));
    }

    @Test
    public void testComputeDecimalFromDMS() {
        Assertions.assertEquals( 50.84654 , NumberUtils.computeDecimalFromDMS(50,50, 47.5368 ) );
        Assertions.assertEquals( 4.35279 , NumberUtils.computeDecimalFromDMS(4,21, 10.0548 ) );
        Assertions.assertEquals( 9.25313 , NumberUtils.computeDecimalFromDMS(9,15, 11.2828 ) );
        Assertions.assertEquals( 200.05966 , NumberUtils.computeDecimalFromDMS(200,3, 34.7832 ) );
    }

}
