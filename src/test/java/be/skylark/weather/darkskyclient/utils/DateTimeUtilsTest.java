package be.skylark.weather.darkskyclient.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;

public class DateTimeUtilsTest {

    @Test
    public void testGetDateTimeNull() {
        Assertions.assertNull( DateTimeUtils.getDateTime( null, "Europe/Brussels" ) );
    }

    @Test
    public void testGetDateTimeError() {
        Assertions.assertThrows(DateTimeException.class, () -> DateTimeUtils.getDateTime( Long.valueOf(1557340948), "Pacific/R'lyeh" ) );
    }

    @Test
    public void testGetDateTime() {
        // 8 / 05 / 2019 - 20:42:28 GMT+02:00 DST
        LocalDateTime brusselsTime = DateTimeUtils.getDateTime( Long.valueOf(1557340948) , "Europe/Brussels" ) ;
        Assertions.assertNotNull( brusselsTime );
        Assertions.assertEquals( 8 , brusselsTime.getDayOfMonth() );
        Assertions.assertEquals(Month.MAY , brusselsTime.getMonth() );
        Assertions.assertEquals(2019, brusselsTime.getYear() );
        Assertions.assertEquals( 20, brusselsTime.getHour() );
        Assertions.assertEquals( 42, brusselsTime.getMinute() );
        Assertions.assertEquals( 28, brusselsTime.getSecond() );
    }
}
