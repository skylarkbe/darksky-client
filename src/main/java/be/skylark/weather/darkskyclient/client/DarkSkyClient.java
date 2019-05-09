package be.skylark.weather.darkskyclient.client;

import be.skylark.weather.darkskyclient.entities.DsBlock;
import be.skylark.weather.darkskyclient.entities.DsMeta;
import be.skylark.weather.darkskyclient.errors.DsForecastException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import be.skylark.weather.darkskyclient.entities.DsResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.text.StringSubstitutor;
import org.apache.http.client.utils.URIBuilder;
import org.glassfish.jersey.client.filter.EncodingFilter;
import org.glassfish.jersey.message.GZipEncoder;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * This is the main class of the library that provides the access to the DarkSky API
 */
@NoArgsConstructor
public class DarkSkyClient {

    private static final String API_KEY_PARAM = "apiKey" ;
    private static final String LATITUDE_PARAM = "latitude" ;
    private static final String LONGITUDE_PARAM = "longitude" ;
    private static final String UNIX_TIME_PARAM = "unixTime" ;

    @Getter @Setter private String baseApiUrl ;
    @Getter @Setter private String forecastPath ;
    @Getter @Setter private String timeMachinePath ;
    @Getter @Setter private String apiKey ;

    private Client restClient = ClientBuilder.newClient().register(GZipEncoder.class).register(EncodingFilter.class) ;

    /**
     * Contacts the DarkSky API and ask for a Forecast
     * @param forecastRequest The forecast request
     * @return The DarkSky Forecast
     */
    public DsResponse getForecast(final DsForecastRequest forecastRequest) throws DsForecastException {

        if ( StringUtils.isBlank(this.apiKey)
                || forecastRequest == null
                || forecastRequest.getLatitude() == null
                || forecastRequest.getLongitude() == null ) {
            throw new IllegalArgumentException("Missing parameter for the Forecast request");
        }

        Map<String,String> urlParameters = buildParametersMap(forecastRequest.getLatitude(),forecastRequest.getLongitude(),null ) ;
        StringSubstitutor urlGenerator = new StringSubstitutor( urlParameters ) ;

        try {
            URIBuilder uriBuilder = new URIBuilder(urlGenerator.replace(StringUtils.join(baseApiUrl, forecastPath)));

            if ( forecastRequest.getExcludeBlocks() != null && forecastRequest.getExcludeBlocks().size() > 0 ) {
                String excludeBlocks = forecastRequest.getExcludeBlocks().stream().map(DsBlock::getValue).collect(Collectors.joining(","));
                uriBuilder.addParameter( DarkSkyClientHelper.EXCLUDE_BLOCKS , excludeBlocks );
            }
            if ( forecastRequest.getExtendHourly() != null && forecastRequest.getExtendHourly() ) {
                uriBuilder.addParameter( DarkSkyClientHelper.EXTEND_HOURLY , DarkSkyClientHelper.EXTEND_HOURLY_VALUE );
            }
            if ( forecastRequest.getLang() != null ) {
                uriBuilder.addParameter( DarkSkyClientHelper.LANG , forecastRequest.getLang().getValue() ) ;
            }
            if ( forecastRequest.getUnits() != null ) {
                uriBuilder.addParameter( DarkSkyClientHelper.UNITS , forecastRequest.getUnits().getValue() ) ;
            }
            return performDarkSkyCall(uriBuilder.build().toString()) ;

        } catch ( URISyntaxException e ) {
            throw new DsForecastException("Unable to build the DarkSky API URL", e);
        } catch ( WebApplicationException e ) {
            throw new DsForecastException("Unable to reach the DarkSky API !", e);
        }
    }

    /**
     * Contacts the DarkSky API and ask for a Time Machine report
     * @param timeMachineRequest The time machine request
     * @return The DarkSky Forecast
     */
    public DsResponse getTimeMachine(final DsTimeMachineRequest timeMachineRequest) throws DsForecastException {

        if ( StringUtils.isBlank(this.apiKey)
                || timeMachineRequest == null
                || timeMachineRequest.getLatitude() == null
                || timeMachineRequest.getLongitude() == null
                || timeMachineRequest.getTime() == null ) {
            throw new IllegalArgumentException("Missing parameter for the Time Machine request");
        }

        Map<String,String> urlParameters = buildParametersMap(timeMachineRequest.getLatitude(),timeMachineRequest.getLongitude(),timeMachineRequest.getTime() ) ;
        StringSubstitutor urlGenerator = new StringSubstitutor( urlParameters ) ;

        try {
            URIBuilder uriBuilder = new URIBuilder(urlGenerator.replace(StringUtils.join(baseApiUrl, timeMachinePath)));
            if ( timeMachineRequest.getExcludeBlocks() != null && timeMachineRequest.getExcludeBlocks().size() > 0 ) {
                String excludeBlocks = timeMachineRequest.getExcludeBlocks().stream().map(DsBlock::getValue).collect(Collectors.joining(","));
                uriBuilder.addParameter( DarkSkyClientHelper.EXCLUDE_BLOCKS , excludeBlocks );
            }
            if ( timeMachineRequest.getLang() != null ) {
                uriBuilder.addParameter( DarkSkyClientHelper.LANG , timeMachineRequest.getLang().getValue() ) ;
            }
            if ( timeMachineRequest.getUnits() != null ) {
                uriBuilder.addParameter( DarkSkyClientHelper.UNITS , timeMachineRequest.getUnits().getValue() ) ;
            }
            return performDarkSkyCall(uriBuilder.build().toString()) ;

        } catch ( URISyntaxException e ) {
            throw new DsForecastException("Unable to build the DarkSky API URL", e);
        } catch ( WebApplicationException e ) {
            throw new DsForecastException("Unable to reach the DarkSky API !", e);
        }
    }

    private Map<String,String> buildParametersMap(BigDecimal latitude, BigDecimal longitude, Long unixTime) {
        Map<String,String> urlParameters = new LinkedHashMap<>() ;
        urlParameters.put( API_KEY_PARAM, this.apiKey ) ;
        urlParameters.put( LATITUDE_PARAM, latitude.toString() );
        urlParameters.put( LONGITUDE_PARAM, longitude.toString() );
        if ( unixTime != null ) {
            urlParameters.put( UNIX_TIME_PARAM, unixTime.toString() );
        }
        return urlParameters ;
    }

    private DsResponse performDarkSkyCall(final String uri) throws DsForecastException {
        Response forecast = restClient.target(uri).request(MediaType.APPLICATION_JSON_TYPE).get() ;
        if ( forecast != null && forecast.getStatus() == Response.Status.OK.getStatusCode() ) {
            DsMeta meta = DsMeta.builder()
                    .cacheControl(forecast.getHeaderString(DsMeta.HEADER_CACHE_CONTROL))
                    .apiCalls(NumberUtils.toInt(forecast.getHeaderString(DsMeta.HEADER_API_CALLS),NumberUtils.INTEGER_ZERO))
                    .responseTime(forecast.getHeaderString(DsMeta.HEADER_RESPONSE_TIME))
                    .build();
            DsResponse dsForecast = forecast.readEntity(DsResponse.class) ;
            dsForecast.setMetaData( meta );
            return dsForecast ;
        }
        if ( forecast != null )
            throw new DsForecastException(String.format("Unable to perform the DarkSky API call ; HTTP code returned is %d", forecast.getStatus())) ;
        throw new DsForecastException("Unable to reach the DarkSky API ; no answer received") ;
    }

}
