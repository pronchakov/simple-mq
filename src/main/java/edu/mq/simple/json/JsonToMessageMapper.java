package edu.mq.simple.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mq.simple.entity.UniversalMessage;

import java.io.IOException;

public class JsonToMessageMapper {
    private ObjectMapper mapper = new ObjectMapper();

    public UniversalMessage transformMessage(byte[] message) {
        try {
            return mapper.readValue(message, UniversalMessage.class);
        } catch (IOException e) {
            throw new RuntimeException(e); // TODO:
        }
    }
}
