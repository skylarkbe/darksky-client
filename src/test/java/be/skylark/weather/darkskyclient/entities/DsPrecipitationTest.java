package be.skylark.weather.darkskyclient.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DsPrecipitationTest {

    @Test
    public void testFindIconByValue() {
        Assertions.assertEquals( DsPrecipitation.SNOW , DsPrecipitation.findPrecipitationByValue("snow") );
        Assertions.assertEquals( DsPrecipitation.UNKNOWN , DsPrecipitation.findPrecipitationByValue(null) );
        Assertions.assertEquals( DsPrecipitation.UNKNOWN , DsPrecipitation.findPrecipitationByValue("meteor") );
    }

}
