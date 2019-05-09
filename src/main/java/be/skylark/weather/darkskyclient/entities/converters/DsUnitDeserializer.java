package be.skylark.weather.darkskyclient.entities.converters;

import be.skylark.weather.darkskyclient.entities.DsUnit;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class DsUnitDeserializer extends JsonDeserializer<DsUnit> {

    @Override
    public DsUnit deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return DsUnit.findUnitByValue(jsonParser.getText());
    }

}