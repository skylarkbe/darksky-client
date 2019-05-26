package be.skylark.weather.darkskyclient.entities;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

/**
 * The severity of the weather alert. Will take one of the following values:
 * <ul>
 *     <li>"advisory" (an individual should be aware of potentially severe weather),</li>
 *     <li>"watch" (an individual should prepare for potentially severe weather),</li>
 *     <li>"warning" (an individual should take immediate action to protect themselves and others from potentially severe weather).</li>
 * </ul>
 * required
 */
public enum DsSeverity implements Serializable {

    ADVISORY("advisory"), WATCH("watch"), WARNING("warning"), UNKNOWN("unknown" );

    @Getter
    private String value ;

    DsSeverity(String value) {
        this.value = value ;
    }

    public static DsSeverity findSeverityByValue(final String value) {
        Optional<DsSeverity> optionalSeverity = Arrays.stream( DsSeverity.values() )
                .filter( severity -> severity.getValue().equals(value) )
                .findFirst() ;
        if ( optionalSeverity.isPresent() ) {
            return optionalSeverity.get() ;
        }
        return UNKNOWN ;
    }
}
