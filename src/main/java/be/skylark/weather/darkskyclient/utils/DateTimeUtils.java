package be.skylark.weather.darkskyclient.utils;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * This class provides utility methods to extract dates and times from the API
 */
public class DateTimeUtils {

    /**
     * This method transforms the given Unix time in a LocalDateTime object
     * @param unixTime The Unix time to transform
     * @param timezone The timezone of the Unix time ; or the System default if null is provided
     * @return The Java LocalDateTime representation of the given Unix time and time zone
     * @throws DateTimeException
     */
    public static LocalDateTime getDateTime( Long unixTime , String timezone ) throws DateTimeException {
        if ( unixTime == null )
            return null ;
        final ZoneId zoneId = ( timezone != null ) ? ZoneId.of( timezone ) : ZoneId.systemDefault() ;
        return Instant.ofEpochSecond( unixTime ).atZone( zoneId ).toLocalDateTime() ;
    }

}
