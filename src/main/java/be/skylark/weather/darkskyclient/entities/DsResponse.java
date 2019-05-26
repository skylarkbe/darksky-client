package be.skylark.weather.darkskyclient.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DsResponse implements Serializable {

    /**
     * The requested latitude.
     */
    @Getter @Setter private BigDecimal latitude;

    /**
     * The requested longitude.
     */
    @Getter @Setter private BigDecimal longitude;

    /**
     * The IANA timezone name for the requested location. This is used for text summaries and for determining when
     * hourly and daily data block objects begin.
     */
    @Getter @Setter private String timezone;

    /**
     * The current timezone offset in hours. (Use of this property will almost certainly result in Daylight
     * Saving Time bugs. Please use timezone, instead.)
     */
    @Deprecated
    @Getter @Setter private int offset;

    /**
     * A data point containing the current weather conditions at the requested location.
     */
    @Getter @Setter private DsDataPoint currently ;

    /**
     * A data block containing the weather conditions minute-by-minute for the next hour.
     * optional
     */
    @Getter @Setter private DsDataBlock minutely ;

    /**
     * A data block containing the weather conditions hour-by-hour for the next two days.
     * optional
     */
    @Getter @Setter private DsDataBlock hourly ;

    /**
     * A data block containing the weather conditions day-by-day for the next week.
     * optional
     */
    @Getter @Setter private DsDataBlock daily ;

    /**
     * An alerts array, which, if present, contains any severe weather alerts pertinent to the requested location.
     * optional
     */
    @Getter @Setter private List<DsAlert> alerts ;

    /**
     * A flags object containing miscellaneous metadata about the request.
     */
    @Getter @Setter private DsFlags flags ;

    /**
     * Extra info for the client implementing the API
     */
    @Getter @Setter private DsMeta metaData ;

}
