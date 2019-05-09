package be.skylark.weather.darkskyclient.entities;

import lombok.Getter;

/**
 * This enum represents a Dark Sky Block that can be excluded from the API call.
 * See <a href="https://darksky.net/dev/docs">DarkSky API</a> for more information
 * @author Skylark.be
 */
public enum DsBlock {

    CURRENTLY("currently"), MINUTELY("minutely"), HOURLY("hourly"), DAILY("daily"),
    ALERTS("alerts"), FLAGS("flags");

    @Getter private String value ;

    DsBlock(String value) {
        this.value = value ;
    }

}
