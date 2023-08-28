package edu.mq.simple.storage.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mq.simple.entity.UniversalMessage;

import java.io.IOException;

public class JSONMessageMapper {
    private final ObjectMapper mapper = new ObjectMapper();

    public JSONMessageMapper() {
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public UniversalMessage transformMessage(byte[] message) {
        try {
            return mapper.readValue(message, UniversalMessage.class);
        } catch (IOException e) {
            throw new RuntimeException(e); // TODO:
        }
    }

    public String transformMessage(UniversalMessage message) throws CannotTransformMessageToJSONException {
        try {
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new CannotTransformMessageToJSONException("Error transforming message to JSON: " + e.getMessage(), e);
        }
    }
}
