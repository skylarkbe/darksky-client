package be.skylark.weather.darkskyclient.entities;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * This class is mapping the request metadata in the returned object ; so that the implementing client may use them
 * accordingly
 */
@Builder
public class DsMeta implements Serializable {

    public static final String HEADER_CACHE_CONTROL = "cache-control" ;
    public static final String HEADER_RESPONSE_TIME = "x-response-time" ;
    public static final String HEADER_API_CALLS = "x-forecast-api-calls" ;

    /**
     * Represents the number of API calls performed during the day
     * Maps the x-forecast-api-calls response header.
     */
    @Getter private int apiCalls ;

    /**
     * Represents the maximum validity time of the request, in seconds.
     * Maps the Cache-Control:max-age=value response header.
     */
    @Getter private String cacheControl ;

    /**
     * Represents the response time of the DarkSkyAPI, as in the x-response-time header
     */
    @Getter private String responseTime ;

}
