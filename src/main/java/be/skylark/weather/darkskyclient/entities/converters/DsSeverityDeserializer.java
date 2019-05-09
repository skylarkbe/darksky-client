package be.skylark.weather.darkskyclient.entities.converters;

import be.skylark.weather.darkskyclient.entities.DsSeverity;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class DsSeverityDeserializer extends JsonDeserializer<DsSeverity> {

    @Override
    public DsSeverity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return DsSeverity.findSeverityByValue(jsonParser.getText());
    }

}
