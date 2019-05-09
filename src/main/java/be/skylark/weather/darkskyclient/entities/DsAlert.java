package be.skylark.weather.darkskyclient.entities;

import be.skylark.weather.darkskyclient.entities.converters.DsSeverityDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The alerts array contains objects representing the severe weather warnings issued for the requested location
 * by a governmental authority (please see our data sources page for a list of sources).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DsAlert {

    /**
     * A detailed description of the alert.
     * required
     */
    @Getter @Setter private String description ;

    /**
     * The UNIX time at which the alert will expire.
     * required
     */
    @Getter @Setter private Long expires ;

    /**
     * An array of strings representing the names of the regions covered by this weather alert.
     * required
     */
    @Getter @Setter private List<String> regions ;

    /**
     * The severity of the weather alert. Will take one of the following values:
     * <ul>
     *     <li>"advisory" (an individual should be aware of potentially severe weather),</li>
     *     <li>"watch" (an individual should prepare for potentially severe weather),</li>
     *     <li>"warning" (an individual should take immediate action to protect themselves and others from potentially severe weather).</li>
     * </ul>
     * required
     */
    @JsonDeserialize(using = DsSeverityDeserializer.class)
    @Getter @Setter private DsSeverity severity ;

    /**
     * The UNIX time at which the alert was issued.
     * required
     */
    @Getter @Setter private Long time ;

    /**
     * A brief description of the alert.
     * required
     */
    @Getter @Setter private String title ;

    /**
     * An HTTP(S) URI that one may refer to for detailed information about the alert.
     * required
     */
    @Getter @Setter private String uri ;

}
