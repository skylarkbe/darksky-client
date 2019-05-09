package be.skylark.weather.darkskyclient.errors;

public class DsForecastException extends Exception {

    public DsForecastException ( String message ) {
        super(message);
    }

    public DsForecastException ( String message, Exception origin ) {
        super(message, origin);
    }

}
