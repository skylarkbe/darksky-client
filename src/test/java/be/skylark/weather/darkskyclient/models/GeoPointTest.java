package be.skylark.weather.darkskyclient.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeoPointTest {

    @Test
    public void testDefaultConstructor() {
        final GeoPoint defaultGeoPoint = new GeoPoint() ;
        Assertions.assertEquals( 0, defaultGeoPoint.getLatitude() );
        Assertions.assertEquals( 0, defaultGeoPoint.getLongitude() );
    }

    @Test
    public void testConstructorFromInt() {
        final GeoPoint geoPointFromINT = new GeoPoint(10, 15) ;
        Assertions.assertEquals( 10d, geoPointFromINT.getLatitude() );
        Assertions.assertEquals( 15d, geoPointFromINT.getLongitude() );
    }

    @Test
    public void testConstructorFromDouble() {
        // test rounding as well
        final GeoPoint geoPointFromDouble = new GeoPoint(10.123456789, 15.987654321) ;
        Assertions.assertEquals( 10.12346, geoPointFromDouble.getLatitude() );
        Assertions.assertEquals( 15.98765, geoPointFromDouble.getLongitude() );
    }

    @Test
    public void testConstructorFromMixed() {
        final GeoPoint geoPointFromMixedA = new GeoPoint(10, 15d) ;
        Assertions.assertEquals( 10d, geoPointFromMixedA.getLatitude() );
        Assertions.assertEquals( 15d, geoPointFromMixedA.getLongitude() );
        final GeoPoint geoPointFromMixedB = new GeoPoint(10d, 15) ;
        Assertions.assertEquals( 10d, geoPointFromMixedB.getLatitude() );
        Assertions.assertEquals( 15d, geoPointFromMixedB.getLongitude() );
    }

    @Test
    public void testConstructorError() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GeoPoint(-200,10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GeoPoint(10,200));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GeoPoint(-200,200));
    }

}
