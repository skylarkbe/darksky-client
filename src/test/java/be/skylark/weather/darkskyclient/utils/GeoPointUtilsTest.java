package be.skylark.weather.darkskyclient.utils;

import be.skylark.weather.darkskyclient.models.GeoPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeoPointUtilsTest {

    @Test
    public void testBuildGeoPointFromDMS() {
        final GeoPoint brussels = GeoPointUtils.buildGeoPointFromDMS('N', 50,50, 47.5368, 'E', 4, 21, 10.0548 ) ;
        Assertions.assertNotNull( brussels );
        Assertions.assertEquals( 50.84654 , brussels.getLatitude() );
        Assertions.assertEquals( 4.35279 , brussels.getLongitude() );

        final GeoPoint midway = GeoPointUtils.buildGeoPointFromDMS('N', 28,12, 25.981, 'W', 177, 22, 24.575 ) ;
        Assertions.assertNotNull( midway );
        Assertions.assertEquals( 28.20722 , midway.getLatitude() );
        Assertions.assertEquals( -177.37349 , midway.getLongitude() );

        final GeoPoint savoIsland = GeoPointUtils.buildGeoPointFromDMS('S', 9,7, 59.4552, 'E', 159, 48, 38.6424 ) ;
        Assertions.assertNotNull( savoIsland );
        Assertions.assertEquals( -9.13318 , savoIsland.getLatitude() );
        Assertions.assertEquals( 159.81073 , savoIsland.getLongitude() );

        final GeoPoint veryLargeTelescop = GeoPointUtils.buildGeoPointFromDMS('S', 24,37, 38.975, 'W', 70, 24, 15.782 ) ;
        Assertions.assertNotNull( veryLargeTelescop );
        Assertions.assertEquals( -24.62749 , veryLargeTelescop.getLatitude() );
        Assertions.assertEquals( -70.40438 , veryLargeTelescop.getLongitude() );
    }

    @Test
    public void testBuildGeoPointWithErrors() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> GeoPointUtils.buildGeoPointFromDMS('W', 50,50, 47.5368, 'E', 4, 21, 10.0548 ) );
        Assertions.assertThrows(IllegalArgumentException.class, () -> GeoPointUtils.buildGeoPointFromDMS('A', 50,50, 47.5368, 'N', 4, 21, 10.0548 ) );
        Assertions.assertThrows(IllegalArgumentException.class, () -> GeoPointUtils.buildGeoPointFromDMS('N', 92,50, 47.5368, 'E', 4, 21, 10.0548 ) );
        Assertions.assertThrows(IllegalArgumentException.class, () -> GeoPointUtils.buildGeoPointFromDMS('N', 50,65, 47.5368, 'E', 4, 21, 10.0548 ) );
        Assertions.assertThrows(IllegalArgumentException.class, () -> GeoPointUtils.buildGeoPointFromDMS('N', 50,50, 68.2536, 'E', 4, 21, 10.0548 ) );
        Assertions.assertThrows(IllegalArgumentException.class, () -> GeoPointUtils.buildGeoPointFromDMS('N', 50,50, 47.5368, 'N', 4, 21, 10.0548 ) );
        Assertions.assertThrows(IllegalArgumentException.class, () -> GeoPointUtils.buildGeoPointFromDMS('N', 50,50, 47.5368, 'E', -4, 21, 10.0548 ) );
        Assertions.assertThrows(IllegalArgumentException.class, () -> GeoPointUtils.buildGeoPointFromDMS('N', 50,50, 47.5368, 'E', 4, -21, 10.0548 ) );
        Assertions.assertThrows(IllegalArgumentException.class, () -> GeoPointUtils.buildGeoPointFromDMS('N', 50,50, 47.5368, 'E', 4, 21, -10.0548 ) );
    }

}
