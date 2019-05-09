package be.skylark.weather.darkskyclient.entities;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * The type of precipitation occurring at the given time. If defined, this property will have one of
 * the following values:
 * <ul>
 *     <li>rain,</li>
 *     <li>snow,</li>
 *     <li>sleet (which refers to each of freezing rain, ice pellets, and “wintery mix”).</li>
 * </ul>
 * (If precipIntensity is zero, then this property will not be defined. Additionally, due to the lack of data in
 * our sources, historical precipType information is usually estimated, rather than observed.)
 */
public enum DsPrecipitation {

    RAIN("rain"), SNOW("snow"), SLEET("sleet"), UNKNOWN("unknown") ;

    @Getter
    private String value ;

    DsPrecipitation(String value) {
        this.value = value ;
    }

    public static DsPrecipitation findPrecipitationByValue(final String value) {
        Optional<DsPrecipitation> optionalPrecipitation = Arrays.stream( DsPrecipitation.values() )
                .filter( precipitation -> precipitation.getValue().equals(value) )
                .findFirst() ;
        if ( optionalPrecipitation.isPresent() ) {
            return optionalPrecipitation.get() ;
        }
        return UNKNOWN ;
    }
}
