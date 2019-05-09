package be.skylark.weather.darkskyclient.utils;

import be.skylark.weather.darkskyclient.models.GeoPoint;

/**
 * This helper class offers some utility methods to play with the GeoPoints
 * @see be.skylark.weather.darkskyclient.models.GeoPoint
 * @author Skylark.be
 */
public class GeoPointUtils {

    // Private boundaries used for the DMS conversions
    private static final int MIN_LAT_DEGREES = 0 ;
    private static final int MAX_LAT_DEGREES = 90 ;
    private static final int MIN_LON_DEGREES = 0 ;
    private static final int MAX_LON_DEGREES = 180 ;
    private static final int MIN_MINUTES = 0 ;
    private static final int MAX_MINUTES = 60 ;
    private static final double MIN_SECONDS = 0 ;
    private static final double MAX_SECONDS = 59.99999 ;

    /**
     * This char represents a North latitude in the DMS related methods
     */
    public static final char NORTH = 'N' ;

    /**
     * This char represents a South latitude in the DMS related methods
     */
    public static final char SOUTH = 'S' ;

    /**
     * This char represents an East longitude in the DMS related methods
     */
    public static final char EAST = 'E' ;

    /**
     * This char represents a West longitude in the DMS related methods
     */
    public static final char WEST = 'W' ;

    /**
     * This utility method transforms a set of values representing a DMS coordinate into a valid GeoPoint, or throws
     * an IllegalArgumentException if the GeoPoint cannot be build.
     *
     * @param latitude The latitude ; either N or S char values
     * @param latDegrees The degrees value of the latitude
     * @param latMinutes The minutes value of the latitude
     * @param latSeconds The seconds value of the latitude
     * @param longitude The longitude ; either E or W char values
     * @param lonDegrees The degrees value of the longitude
     * @param lonMinutes The minutes value of the longitude
     * @param lonSeconds The seconds value of the longitude
     * @return A GeoPoint corresponding to the given DMS point
     */
    public static GeoPoint buildGeoPointFromDMS(
            final char latitude, final int latDegrees, final int latMinutes, final double latSeconds,
            final char longitude, final int lonDegrees, final int lonMinutes, final double lonSeconds) {

        if ( NORTH != latitude && SOUTH != latitude ) {
            throw new IllegalArgumentException(String.format("Latitude value must be either '%s' or '%s'", NORTH, SOUTH)) ;
        }
        if ( ! NumberUtils.isBetween(MIN_LAT_DEGREES, MAX_LAT_DEGREES, latDegrees, true) ) {
            throw new IllegalArgumentException(String.format("Latitude degrees value %s must be between %s and %s", latDegrees, MIN_LAT_DEGREES, MAX_LAT_DEGREES)) ;
        }
        if ( ! NumberUtils.isBetween(MIN_MINUTES, MAX_MINUTES, latMinutes, true)  ) {
            throw new IllegalArgumentException(String.format("Latitude minutes value %s must be between %s and %s", latMinutes, MIN_MINUTES, MAX_MINUTES)) ;
        }
        if ( ! NumberUtils.isBetween(MIN_SECONDS, MAX_SECONDS, latSeconds, true) ) {
            throw new IllegalArgumentException(String.format("Latitude seconds value %d must be between %s and %s", latSeconds, MIN_SECONDS, MAX_SECONDS)) ;
        }

        if ( EAST != longitude && WEST != longitude ) {
            throw new IllegalArgumentException(String.format("Longitude value must be either '%s' or '%s'", EAST, WEST)) ;
        }
        if ( ! NumberUtils.isBetween(MIN_LON_DEGREES, MAX_LON_DEGREES, lonDegrees, true) ) {
            throw new IllegalArgumentException(String.format("Longitude degrees value %s must be between %s and %s", lonDegrees, MIN_LAT_DEGREES, MAX_LAT_DEGREES)) ;
        }
        if ( ! NumberUtils.isBetween(MIN_MINUTES, MAX_MINUTES, lonMinutes, true)  ) {
            throw new IllegalArgumentException(String.format("Longitude minutes value %s must be between %s and %s", lonMinutes, MIN_MINUTES, MAX_MINUTES)) ;
        }
        if ( ! NumberUtils.isBetween(MIN_SECONDS, MAX_SECONDS, lonSeconds, true) ) {
            throw new IllegalArgumentException(String.format("Longitude seconds value %d must be between %s and %s", lonSeconds, MIN_SECONDS, MAX_SECONDS)) ;
        }

        double computedLat = NumberUtils.computeDecimalFromDMS(latDegrees, latMinutes, latSeconds) * ( ( NORTH == latitude ) ? 1 : -1 ) ;
        double computedLon = NumberUtils.computeDecimalFromDMS(lonDegrees, lonMinutes, lonSeconds) * ( ( EAST == longitude ) ? 1 : -1 ) ;

        return new GeoPoint( computedLat , computedLon ) ;
    }



}
