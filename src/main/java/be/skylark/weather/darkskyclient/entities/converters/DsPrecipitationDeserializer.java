package be.skylark.weather.darkskyclient.entities.converters;

import be.skylark.weather.darkskyclient.entities.DsPrecipitation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * This class deserializes the String input from the DarkSky API to a DsPrecipitation
 */
public class DsPrecipitationDeserializer extends JsonDeserializer<DsPrecipitation> {

    @Override
    public DsPrecipitation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return DsPrecipitation.findPrecipitationByValue(jsonParser.getText());
    }

}
