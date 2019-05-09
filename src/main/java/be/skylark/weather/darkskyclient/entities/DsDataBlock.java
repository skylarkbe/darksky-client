package be.skylark.weather.darkskyclient.entities;

import be.skylark.weather.darkskyclient.entities.converters.DsIconDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *  A data block object represents the various weather phenomena occurring over a period of time.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DsDataBlock {

    /**
     * A human-readable summary of this data block.
     * optional
     */
    @Getter @Setter private String summary ;

    /**
     * A machine-readable text summary of this data point, suitable for selecting an icon for display.
     * optional
     */
    @JsonDeserialize(using = DsIconDeserializer.class)
    @Getter @Setter public DsIcon icon ;

    /**
     * An array of data points, ordered by time, which together describe the weather conditions at the requested location over time.
     * required
     */
    @Getter @Setter private List<DsDataPoint> data ;
}
