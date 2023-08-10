package edu.mq.simple.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mq.simple.entity.UniversalMessage;

public class MessageToJsonMapper {
    private ObjectMapper mapper = new ObjectMapper();

    public MessageToJsonMapper() {
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public String transformMessage(UniversalMessage message) throws CannotTransformMessageToJSONException {
        try {
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new CannotTransformMessageToJSONException("Error transforming message to JSON: " + e.getMessage(), e);
        }
    }
}
