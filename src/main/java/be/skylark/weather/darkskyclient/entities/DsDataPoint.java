package be.skylark.weather.darkskyclient.entities;

import be.skylark.weather.darkskyclient.entities.converters.DsIconDeserializer;
import be.skylark.weather.darkskyclient.entities.converters.DsPrecipitationDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DsDataPoint {

    /**
     * The apparent (or “feels like”) temperature.
     * Optional, only on hourly
     */
    @Getter @Setter public BigDecimal apparentTemperature;

    /**
     * The daytime high apparent temperature.
     * optional, only on daily
     */
    @Getter @Setter public BigDecimal apparentTemperatureHigh ;

    /**
     * The UNIX time representing when the daytime high apparent temperature occurs.
     * optional, only on daily
     */
    @Getter @Setter public Long apparentTemperatureHighTime ;

    /**
     * The overnight low apparent temperature.
     * optional, only on daily
     */
    @Getter @Setter public BigDecimal apparentTemperatureLow ;

    /**
     * The UNIX time representing when the overnight low apparent temperature occurs.
     * optional, only on daily
     */
    @Getter @Setter public Long apparentTemperatureLowTime ;

    /**
     * The maximum apparent temperature during a given date.
     * optional, only on daily
     */
    @Getter @Setter public BigDecimal apparentTemperatureMax ;

    /**
     * The UNIX time representing when the maximum apparent temperature during a given date occurs.
     * optional, only on daily
     */
    @Getter @Setter public Long apparentTemperatureMaxTime ;

    /**
     * The minimum apparent temperature during a given date.
     * optional, only on daily
     */
    @Getter @Setter public BigDecimal apparentTemperatureMin ;

    /**
     * The UNIX time representing when the minimum apparent temperature during a given date occurs.
     * optional, only on daily
     */
    @Getter @Setter public Long apparentTemperatureMinTime ;

    /**
     * The percentage of sky occluded by clouds, between 0 and 1, inclusive.
     * optional
     */
    @Getter @Setter public BigDecimal cloudCover ;

    /**
     * The dew point in degrees.
     * optional
     */
    @Getter @Setter public BigDecimal dewPoint ;

    /**
     * The relative humidity, between 0 and 1, inclusive.
     * optional
     */
    @Getter @Setter public BigDecimal humidity ;

    /**
     * A machine-readable text summary of this data point, suitable for selecting an icon for display.
     * optional
     */
    @JsonDeserialize(using = DsIconDeserializer.class)
    @Getter @Setter public DsIcon icon ;

    /**
     * The fractional part of the lunation number during the given day: a value of 0 corresponds to a new moon,
     * 0.25 to a first quarter moon, 0.5 to a full moon, and 0.75 to a last quarter moon.
     * (The ranges in between these represent waxing crescent, waxing gibbous, waning gibbous, and waning crescent
     * moons, respectively.)
     * optional, only on daily
     */
    @Getter @Setter public BigDecimal moonPhase ;

    /**
     * The approximate direction of the nearest storm in degrees, with true north at 0° and progressing clockwise.
     * (If nearestStormDistance is zero, then this value will not be defined.)
     * optional, only on currently
     */
    @Getter @Setter public BigDecimal nearestStormBearing ;

    /**
     * The approximate distance to the nearest storm in miles.
     * (A storm distance of 0 doesn’t necessarily refer to a storm at the requested location, but rather a storm in
     * the vicinity of that location.)
     * optional, only on currently
     */
    @Getter @Setter public BigDecimal nearestStormDistance ;

    /**
     * The columnar density of total atmospheric ozone at the given time in Dobson units.
     * optional
     */
    @Getter @Setter public BigDecimal ozone ;

    /**
     * The amount of snowfall accumulation expected to occur, in inches.
     * (If no snowfall is expected, this property will not be defined.)
     * optional, only on hourly and daily
     */
    @Getter @Setter public BigDecimal precipAccumulation ;

    /**
     * The intensity (in inches of liquid water per hour) of precipitation occurring at the given time.
     * This value is conditional on probability (that is, assuming any precipitation occurs at all).
     * optional
     */
    @Getter @Setter public BigDecimal precipIntensity ;

    /**
     * The standard deviation of the distribution of precipIntensity. (We only return this property when the full
     * distribution, and not merely the expected mean, can be estimated with accuracy.)
     * optional
     */
    @Getter @Setter public BigDecimal precipIntensityError ;

    /**
     * The maximum value of precipIntensity during a given day.
     * optional, only on daily
     */
    @Getter @Setter public BigDecimal precipIntensityMax ;

    /**
     * The UNIX time of when precipIntensityMax occurs during a given day.
     * optional, only on daily
     */
    @Getter @Setter public Long precipIntensityMaxTime ;

    /**
     * The probability of precipitation occurring, between 0 and 1, inclusive.
     * optional
     */
    @Getter @Setter public BigDecimal precipProbability ;

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
     * optional
     */
    @JsonDeserialize(using = DsPrecipitationDeserializer.class)
    @Getter @Setter public DsPrecipitation precipType ;

    /**
     * The sea-level air pressure in millibars.
     * optional
     */
    @Getter @Setter public BigDecimal pressure ;

    /**
     * A human-readable text summary of this data point.
     * (This property has millions of possible values, so don’t use it for automated purposes: use the icon property, instead!)
     * optional
     */
    @Getter @Setter public String summary ;

    /**
     * The UNIX time of when the sun will rise during a given day.
     * optional, only on daily
     */
    @Getter @Setter public Long sunriseTime ;

    /**
     * The UNIX time of when the sun will set during a given day.
     * optional, only on daily
     */
    @Getter @Setter public Long sunsetTime ;

    /**
     * The air temperature in degrees Fahrenheit.
     * optional, only on hourly
     */
    @Getter @Setter public BigDecimal temperature ;

    /**
     * The daytime high temperature.
     * optional, only on daily
     */
    @Getter @Setter public BigDecimal temperatureHigh ;

    /**
     * The UNIX time representing when the daytime high temperature occurs.
     * optional, only on daily
     */
    @Getter @Setter public Long temperatureHighTime ;

    /**
     * The overnight low temperature.
     * optional, only on daily
     */
    @Getter @Setter public BigDecimal temperatureLow ;

    /**
     * The UNIX time representing when the overnight low temperature occurs.
     * optional, only on daily
     */
    @Getter @Setter public Long temperatureLowTime ;

    /**
     * The maximum temperature during a given date.
     * optional, only on daily
     */
    @Getter @Setter public BigDecimal temperatureMax ;

    /**
     * The UNIX time representing when the maximum temperature during a given date occurs.
     * optional, only on daily
     */
    @Getter @Setter public Long temperatureMaxTime ;

    /**
     * The minimum temperature during a given date.
     * optional, only on daily
     */
    @Getter @Setter public BigDecimal temperatureMin ;

    /**
     * The UNIX time representing when the minimum temperature during a given date occurs.
     * optional, only on daily
     */
    @Getter @Setter public Long temperatureMinTime ;

    /**
     * The UNIX time at which this data point begins. minutely data point are always aligned to the top of the
     * minute, hourly data point objects to the top of the hour, and daily data point objects to midnight of
     * the day, all according to the local time zone.
     * required
     */
    @Getter @Setter public Long time ;

    /**
     * The UV index.
     * optional
     */
    @Getter @Setter public Long uvIndex ;

    /**
     * The UNIX time of when the maximum uvIndex occurs during a given day.
     * optional, only on daily
     */
    @Getter @Setter public Long uvIndexTime ;

    /**
     * The average visibility in miles, capped at 10 miles.
     * optional
     */
    @Getter @Setter public BigDecimal visibility ;

    /**
     * The direction that the wind is coming from in degrees, with true north at 0° and progressing clockwise.
     * (If windSpeed is zero, then this value will not be defined.)
     * optional
     */
    @Getter @Setter public Long windBearing  ;

    /**
     * The wind gust speed in miles per hour.
     * optional
     */
    @Getter @Setter public BigDecimal windGust ;

    /**
     * The time at which the maximum wind gust speed occurs during the day.
     * optional, only on daily
     */
    @Getter @Setter public Long windGustTime ;

    /**
     * The wind speed in miles per hour.
     * optional
     */
    @Getter @Setter public BigDecimal windSpeed ;
}
