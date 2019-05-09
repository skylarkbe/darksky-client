package be.skylark.weather.darkskyclient.client;

import be.skylark.weather.darkskyclient.entities.DsBlock;
import be.skylark.weather.darkskyclient.entities.DsLanguage;
import be.skylark.weather.darkskyclient.entities.DsResponse;
import be.skylark.weather.darkskyclient.entities.DsUnit;
import be.skylark.weather.darkskyclient.errors.DsForecastException;
import be.skylark.weather.darkskyclient.models.GeoPoint;
import be.skylark.weather.darkskyclient.utils.GeoPointUtils;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

public class DarkSkyClientIntegrationTest {

    private static String apiKey ;
    private static String baseApiUrl ;
    private static String forecastPath ;
    private static String timeMachinePath ;
    private static DarkSkyClient darkSkyClient ;

    @BeforeAll
    public static void setup() {
        apiKey = System.getProperty("apiKey") ;
        baseApiUrl = System.getProperty("baseApiUrl") ;
        forecastPath = System.getProperty("forecastPath") ;
        timeMachinePath = System.getProperty("timeMachinePath") ;

        darkSkyClient = new DarkSkyClient() ;
        darkSkyClient.setApiKey( apiKey );
        darkSkyClient.setBaseApiUrl( baseApiUrl );
        darkSkyClient.setForecastPath( forecastPath );
        darkSkyClient.setTimeMachinePath( timeMachinePath );
    }

    @Test
    public void testForecastCall () throws DsForecastException  {
        Assumptions.assumeTrue( apiKey != null );
        Assumptions.assumeTrue( baseApiUrl != null );
        Assumptions.assumeTrue( forecastPath != null );

        final GeoPoint brussels = GeoPointUtils.buildGeoPointFromDMS('N', 50,50, 47.5368, 'E', 4, 21, 10.0548 ) ;
        final DsForecastRequest forecastRequest = DsForecastRequest.builder()
                .latitude(BigDecimal.valueOf(brussels.getLatitude()))
                .longitude(BigDecimal.valueOf(brussels.getLongitude()))
                .excludeBlocks(Arrays.asList(DsBlock.ALERTS,DsBlock.MINUTELY))
                .extendHourly(Boolean.FALSE)
                .lang(DsLanguage.FR)
                .units(DsUnit.SI).build() ;

        DsResponse brusselsForecast = darkSkyClient.getForecast( forecastRequest ) ;

        Assertions.assertNotNull( brusselsForecast );
        Assertions.assertEquals( BigDecimal.valueOf(50.84654) , brusselsForecast.getLatitude() );
        Assertions.assertEquals( BigDecimal.valueOf(4.35279) , brusselsForecast.getLongitude() );
        Assertions.assertNotNull( brusselsForecast.getCurrently() );
        Assertions.assertNotNull( brusselsForecast.getCurrently().getTime() );
    }

    @Test
    public void testTimeMachineCall() throws DsForecastException {
        Assumptions.assumeTrue( apiKey != null );
        Assumptions.assumeTrue( baseApiUrl != null );
        Assumptions.assumeTrue( timeMachinePath != null );

        final GeoPoint brussels = GeoPointUtils.buildGeoPointFromDMS('N', 50,50, 47.5368, 'E', 4, 21, 10.0548 ) ;
        final DsTimeMachineRequest timeMachineRequest = DsTimeMachineRequest.builder()
                .latitude(BigDecimal.valueOf(brussels.getLatitude()))
                .longitude(BigDecimal.valueOf(brussels.getLongitude()))
                .time(LocalDateTime.now().minusDays(5).atZone(ZoneId.of("Europe/Brussels")).toEpochSecond())
                .excludeBlocks(Arrays.asList(DsBlock.ALERTS,DsBlock.MINUTELY,DsBlock.HOURLY))
                .lang(DsLanguage.FR)
                .units(DsUnit.SI).build() ;

        DsResponse brusselsTimeMachine = darkSkyClient.getTimeMachine( timeMachineRequest ) ;

        Assertions.assertNotNull( brusselsTimeMachine );
        Assertions.assertEquals( BigDecimal.valueOf(50.84654) , brusselsTimeMachine.getLatitude() );
        Assertions.assertEquals( BigDecimal.valueOf(4.35279) , brusselsTimeMachine.getLongitude() );
    }

}
