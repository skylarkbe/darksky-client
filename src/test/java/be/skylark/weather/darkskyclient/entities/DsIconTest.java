package be.skylark.weather.darkskyclient.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DsIconTest {

    @Test
    public void testFindIconByValue() {
        Assertions.assertEquals( DsIcon.CLEAR_DAY , DsIcon.findIconByValue("clear-day") );
        Assertions.assertEquals( DsIcon.UNKNOWN , DsIcon.findIconByValue(null) );
        Assertions.assertEquals( DsIcon.UNKNOWN , DsIcon.findIconByValue("sandstorm") );
    }

}
