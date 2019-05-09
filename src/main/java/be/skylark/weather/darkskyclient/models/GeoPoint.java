package be.skylark.weather.darkskyclient.models;

import be.skylark.weather.darkskyclient.utils.NumberUtils;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This model class represents a Latitude / Longitude point, ready to be used in the API call
 * @author Skylark.be
 */
@ToString
@NoArgsConstructor
public class GeoPoint {

    private static final int DOUBLE_PRECISION = NumberUtils.DOUBLE_PRECISION ;

    /**
     * Minimal value for the Latitude in decimal degress
     */
    public static final double MIN_LAT = -90 ;

    /**
     * Maximal value for the Latitude in decimal degress
     */
    public static final double MAX_LAT = 90 ;

    /**
     * Minimal value for the Longitude in decimal degress
     */
    public static final double MIN_LON = -180 ;

    /**
     * Maximal value for the Mongitude in decimal degress
     */
    public static final double MAX_LON = 180;

    /**
     * The latitude of a location (in decimal degrees). Positive is north, negative is south.
     */
    @Getter private double latitude ;

    /**
     * The longitude of a location (in decimal degrees). Positive is east, negative is west.
     */
    @Getter private double longitude ;

    /**
     * Simple constructor for the GeoPoint, that receives it's actual value in parameter ; rounded to 5 decimals.
     * If the object cannot be created because of invalid data, an IllegalArgumentException is thrown.
     * @param latitude The latitude of the GeoPoint to create, expressed in decimal degrees (positive is north, negative is south).
     * @param longitude The longitude of the GeoPoint to create, expressed in decimal degrees (positive is east, negative is west).
     */
    public GeoPoint(final double latitude, final double longitude) {
        if ( latitude < MIN_LAT || latitude > MAX_LAT ) {
            throw new IllegalArgumentException(String.format("The latitude must be comprised between %d and %d", MIN_LAT, MAX_LAT));
        }
        if ( longitude < MIN_LON || longitude > MAX_LON ) {
            throw new IllegalArgumentException(String.format("The longitude must be comprised between %d and %d", MIN_LON, MAX_LON));
        }
        this.latitude = new BigDecimal(latitude).setScale(DOUBLE_PRECISION, RoundingMode.HALF_UP).doubleValue() ;
        this.longitude = new BigDecimal(longitude).setScale(DOUBLE_PRECISION, RoundingMode.HALF_UP).doubleValue() ;
    }

}
