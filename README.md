# DarkSky Java Client

> Weather forecasts using the DarkSky public API

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://github.com/skylarkbe/darksky-client/blob/master/LICENSE)
[![Sonatype Nexus (Releases)](https://img.shields.io/nexus/r/https/oss.sonatype.org/be.skylark.weather/darksky-client.svg)](https://oss.sonatype.org/content/groups/public/be/skylark/weather/darksky-client/)
[![GitHub tag (latest by date)](https://img.shields.io/github/tag-date/skylarkbe/darksky-client.svg)](https://github.com/skylarkbe/darksky-client/releases/latest)
[![Build Status](https://travis-ci.com/skylarkbe/darksky-client.svg?branch=master)](https://travis-ci.com/skylarkbe/darksky-client)


This project implements a REST client interface for the [Dark Sky API](https://darksky.net/). While keeping in mind that this client could be used in a [Spring](https://spring.io/) context as a bean, it may also be used independently.

To use this library, you must create an account on the [Dark Sky API Developer portal](https://darksky.net/dev), and generate a key for the API.

Both the _Forecast_ and _TimeMachine_ requests are supported.

## Usage

### Client

The Forecast and TimeMachine calls rely on the `DarkSkyClient`, which may be created accordingly :

```
DarkSkyClient darkSkyClient = new DarkSkyClient() ;
darkSkyClient.setApiKey( YOUR_API_KEY );
darkSkyClient.setBaseApiUrl( "https://api.darksky.net" );
darkSkyClient.setForecastPath( "/forecast/${apiKey}/${latitude},${longitude}" );
darkSkyClient.setTimeMachinePath( "/forecast/${apiKey}/${latitude},${longitude},${unixTime}" );
``` 

### Forecast

Firstly, you need to create an instance of `DarkSkyClient`, and populate the obligatory properties. Then, build a `DsForecastRequest` :

```
final DsForecastRequest forecastRequest = DsForecastRequest.builder()
    .latitude(BigDecimal.valueOf("50.84654"))
    .longitude(BigDecimal.valueOf("4.35279"))
    .excludeBlocks(Arrays.asList(DsBlock.ALERTS,DsBlock.MINUTELY))
    .extendHourly(Boolean.FALSE)
    .lang(DsLanguage.EN)
    .units(DsUnit.SI).build() ;
```

And call the API :

```
DsResponse forecast = darkSkyClient.getForecast( forecastRequest ) ;
```

### Time Machine

Firstly, you need to create an instance of `DarkSkyClient`, and populate the obligatory properties. Then, build a `DsTimeMachineRequest` :

```
final DsTimeMachineRequest timeMachineRequest = DsTimeMachineRequest.builder()
    .latitude(BigDecimal.valueOf("50.84654"))
    .longitude(BigDecimal.valueOf("4.35279"))
    .time(LocalDateTime.now().minusDays(5).atZone(ZoneId.of("Europe/Brussels")).toEpochSecond())
    .excludeBlocks(Arrays.asList(DsBlock.ALERTS,DsBlock.MINUTELY,DsBlock.HOURLY))
    .lang(DsLanguage.FR)
    .units(DsUnit.SI).build() ;
```

And finally, call the API :

```
DsResponse timeMachine = darkSkyClient.getTimeMachine( forecastRequest ) ;
```

## Attribution

[![Powered by Dark Sky](https://darksky.net/dev/img/attribution/poweredby-oneline.png)](https://darksky.net/poweredby/)