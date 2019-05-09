package be.skylark.weather.darkskyclient.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class provides easy to use methods on numbers
 */
public class NumberUtils {

    public static final int DOUBLE_PRECISION = 5 ;
    public static final BigDecimal BD60 = BigDecimal.valueOf(60) ;
    public static final BigDecimal BD3600 = BigDecimal.valueOf(3600) ;

    /**
     * This method checks if a given double is in between two given boundaries. The boolean value indicates if the
     * boundaries are inclusive or exclusive
     * @param lowerBoundary The lower boundary
     * @param higherBoundary The higher boundary
     * @param number The number to check
     * @param isInclusive Indicates if the boundaries are inclusive (true) or exlusive (false)
     * @return The presence of the number to check in the given boundaries
     */
    public static boolean isBetween(final double lowerBoundary, final double higherBoundary, final double number, final boolean isInclusive) {
        return isInclusive ?
                number >= lowerBoundary && number <= higherBoundary :
                number > lowerBoundary && number < higherBoundary ;
    }

    /**
     * This method computes the decimal value a degree-minutes-seconds input
     * @param degrees
     * @param minutes
     * @param seconds
     * @return
     */
    public static double computeDecimalFromDMS(final int degrees, final int minutes, final double seconds) {
        double value = degrees + ( ( ( minutes * 60 ) + seconds ) / 3600 ) ;
        return BigDecimal.valueOf(value).setScale(DOUBLE_PRECISION, RoundingMode.HALF_UP).doubleValue();
    }
}
