package be.skylark.weather.darkskyclient.entities;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

/**
 * A machine-readable text summary of this data point, suitable for selecting an icon for display.
 * If defined, this property will have one of the following values:
 * <ul>
 *     <li>clear-day,</li>
 *     <li>clear-night,</li>
 *     <li>rain,</li>
 *     <li>snow,</li>
 *     <li>sleet,</li>
 *     <li>wind,</li>
 *     <li>fog,</li>
 *     <li>cloudy,</li>
 *     <li>partly-cloudy-day,</li>
 *     <li>partly-cloudy-night.</li>
 * </ul>
 * (Developers should ensure that a sensible default is defined, as additional values, such as hail, thunderstorm,
 * or tornado, may be defined in the future.)
 */
public enum DsIcon implements Serializable {

    CLEAR_DAY("clear-day"), CLEAR_NIGHT("clear-night"), RAIN("rain"), SNOW("snow"),
    SLEET("sleet"), WIND("wind"), FOG("fog"), CLOUDY("cloudy"),
    PARTLY_CLOUDY_DAY("partly-cloudy-day"), PARTLY_CLOUDY_NIGHT("partly-cloudy-night"),
    UNKNOWN("unknown" );

    @Getter
    private String value ;

    DsIcon(String value) {
        this.value = value ;
    }

    public static DsIcon findIconByValue(final String value) {
        Optional<DsIcon> optionalIcon = Arrays.stream( DsIcon.values() )
                .filter( icon -> icon.getValue().equals(value) )
                .findFirst() ;
        if ( optionalIcon.isPresent() ) {
            return optionalIcon.get() ;
        }
        return UNKNOWN ;
    }

}
