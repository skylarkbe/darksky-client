package be.skylark.weather.darkskyclient.entities;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

public enum DsUnit implements Serializable {

    /**
     * Automatically select units based on geographic location
     */
    AUTO("auto"),
    /**
     * Same as si, except that windSpeed and windGust are in kilometers per hour
     */
    CA("ca"),
    /**
     * Same as si, except that nearestStormDistance and visibility are in miles, and windSpeed and windGust in miles per hour
     */
    UK2("uk2"),
    /**
     * Imperial units (the default)
     */
    US("us"),
    /**
     * SI units
     */
    SI("si");

    @Getter private String value ;

    DsUnit(String value) {
        this.value = value ;
    }

    public static DsUnit findUnitByValue(final String value) {
        Optional<DsUnit> optionalUnit = Arrays.stream( DsUnit.values() )
                .filter( unit -> unit.getValue().equals(value) )
                .findFirst() ;
        if ( optionalUnit.isPresent() ) {
            return optionalUnit.get() ;
        }
        return US ;
    }

}
