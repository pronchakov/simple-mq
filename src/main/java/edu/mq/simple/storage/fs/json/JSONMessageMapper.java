package edu.mq.simple.storage.fs.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mq.simple.entity.SimpleMQMessage;

import java.io.IOException;

public class JSONMessageMapper {
    private final ObjectMapper mapper = new ObjectMapper();

    public JSONMessageMapper() {
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public SimpleMQMessage transformMessage(String message) {
        try {
            return mapper.readValue(message, SimpleMQMessage.class);
        } catch (IOException e) {
            throw new RuntimeException(e); // TODO:
        }
    }

    public String transformMessage(SimpleMQMessage message) throws CannotTransformMessageToJSONException {
        try {
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new CannotTransformMessageToJSONException("Error transforming message to JSON: " + e.getMessage(), e);
        }
    }
}
