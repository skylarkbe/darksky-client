package be.skylark.weather.darkskyclient.entities;

import be.skylark.weather.darkskyclient.entities.converters.DsUnitDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DsFlags implements Serializable {

    /**
     * The presence of this property indicates that the Dark Sky data source supports the given location, but a
     * temporary error (such as a radar station being down for maintenance) has made the data unavailable.
     * optional
     */
    @JsonProperty("darksky-unavailable")
    @Getter @Setter private String darkskyUnavailable ;

    /**
     * The distance to the nearest weather station that contributed data to this response. Note, however, that
     * many other stations may have also been used; this value is primarily for debugging purposes. This property's
     * value is in miles (if US units are selected) or kilometers (if SI units are selected).
     */
    @JsonProperty("nearest-station")
    @Getter @Setter private BigDecimal nearestStation ;

    /**
     * This property contains an array of IDs for each data source utilized in servicing this request.
     * required
     */
    @Getter @Setter private List<String> sources ;

    /**
     * Indicates the units which were used for the data in this request.
     * required
     */
    @JsonDeserialize(using = DsUnitDeserializer.class)
    @Getter @Setter private DsUnit units ;

}
