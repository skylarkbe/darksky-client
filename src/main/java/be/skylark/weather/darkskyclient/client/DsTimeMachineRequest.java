package be.skylark.weather.darkskyclient.client;

import be.skylark.weather.darkskyclient.entities.DsBlock;
import be.skylark.weather.darkskyclient.entities.DsLanguage;
import be.skylark.weather.darkskyclient.entities.DsUnit;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
public class DsTimeMachineRequest {

    /**
     * The latitude of a location (in decimal degrees). Positive is north, negative is south.
     * required
     */
    @Getter private BigDecimal latitude ;

    /**
     * The longitude of a location (in decimal degrees). Positive is east, negative is west.
     * required
     */
    @Getter private BigDecimal longitude ;

    /**
     * A UNIX time. The timezone is only used for determining the time of the request; the response will always be relative to the local time zone.
     * required
     */
    @Getter private Long time ;

    /**
     * Exclude some number of data blocks from the API response.
     * This is useful for reducing latency and saving cache space.
     * optional
     */
    @Getter private List<DsBlock> excludeBlocks ;

    /**
     * Return summary properties in the desired language.
     * (Note that units in the summary will be set according to the units parameter, so be sure to set both parameters appropriately.)
     * optional
     */
    @Getter private DsLanguage lang ;

    /**
     * Return weather conditions in the requested units.
     */
    @Getter private DsUnit units ;
}
