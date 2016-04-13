package at.begin.infra.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class JpaConverterJson implements AttributeConverter<Object, String> {

    @Override
    public String convertToDatabaseColumn(Object meta) {
        try {
            return Util.OBJECT_MAPPER.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        try {
            return Util.OBJECT_MAPPER.readValue(dbData, Object.class);
        } catch (IOException ex) {
            return null;
        }
    }

}